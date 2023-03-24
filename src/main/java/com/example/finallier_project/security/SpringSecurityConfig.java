package com.example.finallier_project.security;

import com.example.finallier_project.profiles.ProfileNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Profile(ProfileNames.USERS_IN_MEMORY)
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        InMemoryUserDetailsManager menager = new InMemoryUserDetailsManager();
        User.UserBuilder userBuilder = User.builder();

        UserDetails user = userBuilder
                .username("username")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();

        menager.createUser(user);
        return menager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()

                .mvcMatchers("/resources/**","/signup","/about").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .mvcMatchers("/h2-console","/h2-console/**").permitAll()
                .mvcMatchers("/index","/","/register","/validate").permitAll()
                .anyRequest()
                .authenticated();
        http.headers().frameOptions().sameOrigin();
        http
                .formLogin()
                .loginPage("/login")
                .permitAll();
        http.exceptionHandling().accessDeniedPage("/error403");
        http.logout().permitAll();

        return http.build();
    }


    @Secured("ROLE_ADMIN")
    public void metoda1(){
        System.out.println("Metoda1");
    }
}

package com.example.finallier_project.service_implementation;

import com.example.finallier_project.models.Rola;
import com.example.finallier_project.models.Uzytkownik;
import com.example.finallier_project.profiles.ProfileNames;
import com.example.finallier_project.repositories.RolaRepository;
import com.example.finallier_project.repositories.UzytkownikRepository;
import com.example.finallier_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
@Profile(ProfileNames.USERS_IN_DATABASE)
public class UserServiceImpl implements UserService {

    @Autowired
    UzytkownikRepository uzytkownikRepository;
    @Autowired
    RolaRepository rolaRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = uzytkownikRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return convertToUserDetails(user);
    }

    private UserDetails convertToUserDetails(Uzytkownik uzytkownik){
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Rola rola : uzytkownik.getRole()){
            grantedAuthorities.add(new SimpleGrantedAuthority(rola.getType().toString()));
        }
        return new User(uzytkownik.getUsername(), uzytkownik.getPassword(),grantedAuthorities);
    }
    @Transactional
    public void validate(Uzytkownik uzytkownik){
        var user = uzytkownikRepository.findByUsername(uzytkownik.getUsername());
        if(user == null && (uzytkownik.getPassword() != null
                && uzytkownik.getNr_tel() != null
                && uzytkownik.getImie() != null
                && uzytkownik.getNazwisko() != null
                && uzytkownik.getEmail() != null)){
            uzytkownik.setPassword(passwordEncoder.encode(uzytkownik.getPassword()));
            uzytkownik.setRole(new HashSet<>(Arrays.asList(rolaRepository.getReferenceById(1l))));
            uzytkownikRepository.save(uzytkownik);
            System.out.println("Dodano do bazy danych");
        }
    }

    @Override
    public Uzytkownik getByUsername(String username) {
        return uzytkownikRepository.findByUsername(username);
    }

    @Override
    public List<Uzytkownik> getAllUsers() {
        return uzytkownikRepository.findAll();
    }

    @Override
    @Transactional
    public Uzytkownik findById(long id) {
        return uzytkownikRepository.findById(id);
    }

    @Override
    @Transactional
    public void changeEnabled(long id) {
        var user = uzytkownikRepository.findById(id);
        if(user.isEnabled())
            user.setEnabled(false);
        else
            user.setEnabled(true);
    }

    @Override
    @Transactional
    public void changeRole(long id) {
        var user = uzytkownikRepository.findById(id);
        Set<Rola> role = new HashSet<>();
        if(user.getRole().contains(rolaRepository.getReferenceById(1l)) && user.getRole().contains(rolaRepository.getReferenceById(2l))){
            role.add(rolaRepository.getReferenceById(1l));
            role.add(rolaRepository.getReferenceById(2l));
            user.setRole(role);
        }
        else if(user.getRole().contains(rolaRepository.getReferenceById(1l))) {
            role.add(rolaRepository.getReferenceById(2l));
            user.setRole(role);
        }
        else{
            role.add(rolaRepository.getReferenceById(1l));
            user.setRole(role);
        }
    }
}

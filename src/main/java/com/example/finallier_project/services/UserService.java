package com.example.finallier_project.services;

import com.example.finallier_project.models.Uzytkownik;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userDetailsService")
public interface UserService extends UserDetailsService {
    public void validate(Uzytkownik uzytkownik);
    public Uzytkownik getByUsername(String username);
    public List<Uzytkownik> getAllUsers();
    @Transactional
    public Uzytkownik findById(long id);
    @Transactional
    public void changeEnabled(long id);
    @Transactional
    public void changeRole(long id);
}

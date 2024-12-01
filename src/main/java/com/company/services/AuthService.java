package com.company.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.models.entities.Akun;
import com.company.models.entities.Karyawan;
import com.company.models.entities.enums.JabatanLevel;
import com.company.models.entities.enums.UserRole;
import com.company.models.repository.AkunRepo;
import com.company.models.repository.KaryawanRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthService implements UserDetailsService {

    @Autowired
    private AkunRepo akunRepo;

    @Autowired
    private KaryawanRepo karyawanRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return akunRepo.findByEmail(email)
            .orElseThrow(() ->
            new UsernameNotFoundException(
                String.format("user with email '%s' not found", email)));
    }
    
    public Akun registerAkun(Akun user) {
        boolean userExists = akunRepo.findByEmail(user.getEmail()).isPresent();
        if(userExists) {
            throw new RuntimeException(
                String.format("user with email '%s' not found", user.getEmail()));
        }

        Karyawan karyawan = karyawanRepo.findByEmail(user.getEmail());
        if(karyawan == null) {
            throw new RuntimeException(
                String.format("Email '%s' is not associated with any Karyawan", user.getEmail()));
        }
    
        user.setRole(karyawan.getJabatan().getLevel() == JabatanLevel.PRESIDENT 
            ? UserRole.MANAGER 
            : UserRole.KARYAWAN);
    
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setKaryawan(karyawan);
    
        return akunRepo.save(user);
    }

}

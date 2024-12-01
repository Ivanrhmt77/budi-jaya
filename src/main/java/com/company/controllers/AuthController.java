package com.company.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.AkunData;
import com.company.dto.ResponseData;
import com.company.models.entities.Akun;
import com.company.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<Akun>> register(@RequestBody AkunData akunData) {
        ResponseData<Akun> response = new ResponseData<>();
        Akun akun = modelMapper.map(akunData, Akun.class);
        response.setPayload(authService.registerAkun(akun));
        response.setStatus(true);
        response.getMessages().add("User Saved!");
        return ResponseEntity.ok(response);
    }

}

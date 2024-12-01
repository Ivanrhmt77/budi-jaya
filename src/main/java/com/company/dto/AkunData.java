package com.company.dto;

import com.company.models.entities.Karyawan;
import com.company.models.entities.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AkunData {
    private String email;
    private String password;
    private UserRole role;
    private Karyawan karyawan;
}

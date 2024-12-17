package com.example.module2.service;

import com.example.module2.entity.Society;
import com.example.module2.exception.RegistrationException;
import com.example.module2.model.AdminData;

import java.util.List;

public interface SocietyService {
    public String societyRegistration(AdminData adminData, String jwt) throws RegistrationException;
    public List<Society> getAllSocieties();
    public Society getSocietyByName(String name) throws RegistrationException;
    public Society getSocietyById(Long id);
    public Society getAdminDetails(String jwt);
}
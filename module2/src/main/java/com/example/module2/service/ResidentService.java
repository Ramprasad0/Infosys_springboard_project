package com.example.module2.service;

import com.example.module2.entity.Resident;
import com.example.module2.exception.RegistrationException;
import com.example.module2.model.ResidentData;
import com.example.module2.model.ResidentDetails;

import java.util.List;

public interface ResidentService {
    public String residentRegistration(ResidentData residentData, String jwt) throws RegistrationException;
    public List<Resident> getResidents();
    public Resident getResident(String jwt);
    public ResidentDetails getResidentProfile(String jwt);
}
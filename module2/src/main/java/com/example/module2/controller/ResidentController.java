package com.example.module2.controller;

import com.example.module2.entity.Resident;
import com.example.module2.entity.Flat;
import com.example.module2.exception.RegistrationException;
import com.example.module2.model.ResidentData;
import com.example.module2.model.ResidentDetails;
import com.example.module2.repository.ResidentRepository;
import com.example.module2.repository.FlatRepository;
import com.example.module2.service.FlatService;
import com.example.module2.service.ResidentService;
import com.example.module2.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {
    @Autowired
    ResidentService residentService;
    @Autowired
    FlatService flatService;
    @Autowired
    SocietyService societyService;
    @PostMapping("/resident-register")
    public String residentRegistration(@RequestHeader("Authorization") String jwt, @RequestBody ResidentData residentData) throws RegistrationException {
        System.out.println("Received JWT: " + jwt);
        System.out.println("Admin Details DTO: " + residentData);
        return residentService.residentRegistration(residentData,jwt);
    }
    @GetMapping("/residents")
    public List<Resident> getResidents(){
        return residentService.getResidents();
    }

    @GetMapping("/getResidentProfile")
    public ResidentDetails getResidentProfile(@RequestHeader("Authorization") String jwt){
        return residentService.getResidentProfile(jwt);
    }

    @GetMapping("/getResidentByJWT")
    public Resident getResidentByJWT(@RequestHeader("Authorization") String jwt){
        return residentService.getResident(jwt);
    }
}
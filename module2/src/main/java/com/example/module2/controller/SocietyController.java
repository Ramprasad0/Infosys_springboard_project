package com.example.module2.controller;

import com.example.module2.entity.Society;
import com.example.module2.exception.RegistrationException;
import com.example.module2.model.AdminData;
import com.example.module2.repository.SocietyRepository;
import com.example.module2.service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/societies")
public class SocietyController {

    @Autowired
    SocietyService societyService;
    @PostMapping("/society-register")
    public String residentRegistration(@RequestHeader("Authorization") String jwt, @RequestBody AdminData adminData) throws RegistrationException {
        System.out.println("Received JWT: " + jwt);
        System.out.println("Admin Details : " + adminData);
        return societyService.societyRegistration(adminData,jwt);

    }

    @GetMapping("/societies")
    public List<Society> getAllSocieties(){
        return societyService.getAllSocieties();
    }

    @GetMapping("/get-admin")
    public Society getAdminDetails(@RequestHeader("Authorization") String jwt){
        return societyService.getAdminDetails(jwt);
    }
}

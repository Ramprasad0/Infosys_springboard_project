package com.example.module2.service;

import com.example.module2.entity.Flat;
import com.example.module2.entity.Resident;
import com.example.module2.entity.Society;
import com.example.module2.exception.RegistrationException;
import com.example.module2.model.ResidentData;
import com.example.module2.model.ResidentDetails;
import com.example.module2.repository.FlatRepository;
import com.example.module2.repository.ResidentRepository;
import com.example.module2.repository.SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ResidentServiceImplementation implements ResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private FlatService flatService;

    @Autowired
    private SocietyService societyService;

    @Autowired
    private SocietyRepository societyRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${authentication.service.url}")
    private String authenticationServiceUrl;

    /**
     * Fetches email from JWT token using the authentication service.
     *
     * @param jwt The JWT token.
     * @return The email address.
     */
    private String getEmailFromJWT(String jwt) {
        String url = authenticationServiceUrl + "/get-email";

        // Create HTTP Headers and set Authorization
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);

        // Create HTTP Entity with headers
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // Make a GET request and extract the response body
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    @Override
    public String residentRegistration(ResidentData residentData, String jwt) throws RegistrationException {
        // Fetch Flat by flat number
        Flat flat = flatService.getFlatByFlatNo(residentData.getFlatNo());
        if (flat == null) {
            throw new RegistrationException("Flat not found");
        }

        // Fetch email from JWT token
        String email = getEmailFromJWT(jwt);

        // Fetch Society by name
        Society society = societyService.getSocietyByName(residentData.getSocietyName());
        if (society == null) {
            throw new RegistrationException("Society not found");
        }

        // Create and populate Resident entity
        Resident newResident = new Resident();
        newResident.setName(residentData.getName());
        newResident.setPhoneNo(residentData.getPhoneNo());
        newResident.setFlatNo(residentData.getFlatNo());
        newResident.setPostal(residentData.getPostal());
        newResident.setEmail(email);
        newResident.setSocietyId(society.getSocietyId());
        newResident.setFlatId(flat.getFlatId());
        newResident.setRole("Resident");

        // Save Resident to repository
        Resident savedResident = residentRepository.save(newResident);
        if (savedResident.getResidentId() == null) {
            throw new RegistrationException("Unable to save the resident");
        }

        // Mark the flat as occupied and save changes
        flatRepository.save(flat);

        return "Registration Successful";
    }

    @Override
    public List<Resident> getResidents() {
        return residentRepository.findAll();
    }

    @Override
    public Resident getResident(String jwt) {
        String email = getEmailFromJWT(jwt);
        return residentRepository.findByEmail(email);
    }

    @Override
    public ResidentDetails getResidentProfile(String jwt) {
        Resident resident = getResident(jwt);
        if (resident == null) {
            throw new IllegalArgumentException("Resident not found");
        }

        // Create and populate ResidentDetails DTO
        ResidentDetails residentDetails = new ResidentDetails();
        residentDetails.setName(resident.getName());
        residentDetails.setPhoneNo(resident.getPhoneNo());
        residentDetails.setPostal(resident.getPostal());
        residentDetails.setFlatNo(resident.getFlatNo());

        Society society = societyService.getSocietyById(resident.getSocietyId());
        if (society != null) {
            residentDetails.setSocietyName(society.getSocietyName());
        }

        residentDetails.setEmail(resident.getEmail());
        return residentDetails;
    }
}

package com.example.module2.service;

import com.example.module2.entity.Society;
import com.example.module2.exception.RegistrationException;
import com.example.module2.model.AdminData;
import com.example.module2.repository.SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class SocietyServiceImplementation implements SocietyService {

    @Autowired
    SocietyRepository societyRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${authentication.service.url}")
    private String authenticationServiceUrl;

    private String getEmailFromJWT(String jwt) {
        String url = authenticationServiceUrl + "/get-email";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }

    @Override

    public String societyRegistration(AdminData adminData, String jwt) throws RegistrationException {
        Society newSociety = new Society();
        newSociety.setName(adminData.getName());
        newSociety.setPhoneNo(adminData.getPhoneNo());
        newSociety.setSocietyName(adminData.getSocietyName());
        newSociety.setSocietyAddress(adminData.getSocietyAddress());
        newSociety.setDistrict(adminData.getDistrict());
        newSociety.setPostal(adminData.getPostal());
        String email = getEmailFromJWT(jwt);
        newSociety.setEmail(email);
        newSociety.setCity(adminData.getCity());
        societyRepository.save(newSociety);
        return "Registration Successful";
    }

    @Override
    public List<Society> getAllSocieties() {
        return societyRepository.findAll();
    }

    @Override
    public Society getSocietyByName(String name) throws RegistrationException {
        Society society = societyRepository.findBySocietyName(name);
        if (society != null) {
            return society;
        }
        throw new RegistrationException("Society Not Found");
    }

    @Override
    public Society getSocietyById(Long id) {
        Optional<Society> society = societyRepository.findById(id);
        return society.orElse(null);
    }

    @Override
    public Society getAdminDetails(String jwt) {
        String email = getEmailFromJWT(jwt);
        return societyRepository.findByEmail(email);
    }
}

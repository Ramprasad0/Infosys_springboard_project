package com.example.module2.service;


import com.example.module2.entity.Flat;
import com.example.module2.exception.RegistrationException;

import java.util.List;

public interface FlatService {
    Flat addFlat(Flat flat);
    Flat getFlatByFlatNo(String flatNo) throws RegistrationException;
    List<Flat> getAllFlats();
}
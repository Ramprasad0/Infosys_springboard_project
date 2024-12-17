package com.example.module2.controller;

import com.example.module2.entity.Flat;
import com.example.module2.entity.Society;
import com.example.module2.model.FlatData;
import com.example.module2.repository.FlatRepository;
import com.example.module2.repository.SocietyRepository;
import com.example.module2.service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flats")
public class FlatController {

    @Autowired
    FlatService flatService;
    @PostMapping("/addFlat")
    public Flat addFlat(@RequestBody FlatData flatData){
        Flat flat=new Flat();
        flat.setFlatNo(flatData.getFlatNo());
        flat.setSocietyId(flatData.getSocietyId());
        System.out.println("Flat values"+flat);
        return flatService.addFlat(flat);
    }

    @GetMapping("/getAllFlats")
    public List<Flat> getAllFlats(){
        return flatService.getAllFlats();
    }
}
package com.example.module2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminData {
    private String name;
    private String phoneNo;
    private String postal;
    private String societyName;
    private String societyAddress;
    private String city;
    private String district;
}
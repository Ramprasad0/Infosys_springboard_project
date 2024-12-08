package com.example.module2.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResidentData {
    private String name;
    private String phoneNo;
    private String postal;
    private String societyName;
    private String flatNo;
}
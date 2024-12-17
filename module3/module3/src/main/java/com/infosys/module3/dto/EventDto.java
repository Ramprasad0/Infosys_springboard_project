package com.infosys.module3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private String eventName;
    private Date eventDate;
    private String eventDetails;
    private String eventImage;
}

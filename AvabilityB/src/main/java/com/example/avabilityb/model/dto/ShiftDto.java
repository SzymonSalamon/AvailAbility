package com.example.avabilityb.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ShiftDto {
    private  long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String title;

}

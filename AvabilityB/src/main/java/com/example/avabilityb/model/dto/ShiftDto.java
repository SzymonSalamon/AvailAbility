package com.example.avabilityb.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@Builder

public class ShiftDto {
    private Long shiftId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String title;
    private Long calendarId;
    private Long shiftStatusId;
}

package com.example.avabilityb.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ShiftCreationDto {
    private String title;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
}


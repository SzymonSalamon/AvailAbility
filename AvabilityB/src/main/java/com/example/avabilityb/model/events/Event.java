package com.example.avabilityb.model.events;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.example.avabilityb.model.enums.*;

@Getter
@Setter
@Builder



public class Event {
    String eventId;
    String eventOwnerId;

    LocalDate StartDate;
    LocalDate EndDate;
    EventStatus eventStatus;

}

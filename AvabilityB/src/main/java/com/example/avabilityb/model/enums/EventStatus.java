package com.example.avabilityb.model.enums;

public enum EventStatus {
    ACCEPTED("Accepted"),
    REFUSE("Refused"),
    WAITING("Wait for Respons"),
    DELETED("Deleted");
    private String eventStatus;
    EventStatus(String eventstatus){
        this.eventStatus = eventstatus;
    }
    public String getEventStatus(){
        return this.eventStatus;
    }
}

package com.example.avabilityb.model.enums;


public enum EventStatus {
    ACCEPTED("Accepted"),
    REFUSE("Refused"),
    WAITINGEMPLOYEE("Wait for Response From Manager"),
    WAITINGMANAGER("Wait for Response From Manager"),
    DELETED("Deleted");
    private String eventStatus;
    EventStatus(String eventstatus){
        this.eventStatus = eventstatus;
    }
    public String getEventStatus(){
        return this.eventStatus;
    }
}

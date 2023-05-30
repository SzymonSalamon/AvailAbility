package com.example.avabilityb.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "Shifts")
public class ShiftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id")
    private Integer shiftId;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private CalendarEntity calendar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_status")
    private ShiftStatusEntity shiftStatus;

}

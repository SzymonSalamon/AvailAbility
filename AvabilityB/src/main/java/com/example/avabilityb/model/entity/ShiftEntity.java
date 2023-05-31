package com.example.avabilityb.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "shifts")
public class ShiftEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shift_id")
    private Long shiftId;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name="title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private CalendarEntity calendar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_status")
    private ShiftStatusEntity shiftStatus;

}

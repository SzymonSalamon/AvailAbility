package com.example.avabilityb.service;

import com.example.avabilityb.model.dto.ShiftCreationDto;
import com.example.avabilityb.model.entity.ShiftEntity;
import com.example.avabilityb.repository.CalendarRepository;
import com.example.avabilityb.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final CalendarRepository calendarRepository;
    private final UserService userService;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository, UserService userService, CalendarRepository calendarRepository) {
        this.shiftRepository = shiftRepository;
        this.userService = userService;
        this.calendarRepository = calendarRepository;
    }

    public void saveShifts(List<ShiftCreationDto> shifts) {
        Long userId = userService.getCurrentUserId();
        var optionalCalendarEntity = calendarRepository.findByUserId(userId);
        if (optionalCalendarEntity.isEmpty()) {
            throw new IllegalStateException("Calendar of userId " + userId + " cannot be found");
        }
        var shiftEntities = shifts.stream()
                .map(this::convertToShiftEntity)
                .collect(Collectors.toList());
        shiftEntities.forEach(c -> c.setCalendar(optionalCalendarEntity.get()));
        shiftRepository.saveAll(shiftEntities);
    }

    private ShiftEntity convertToShiftEntity(ShiftCreationDto shiftCreationDto) {
        ShiftEntity shiftEntity = new ShiftEntity();
        shiftEntity.setTitle(shiftCreationDto.getTitle());
        //shiftEntity.setCalendar(shiftCreationDto.getCalendar());
        shiftEntity.setStartTime(shiftCreationDto.getStartTime());
        shiftEntity.setEndTime(shiftCreationDto.getEndTime());
        shiftEntity.setTitle(shiftCreationDto.getTitle());
        return shiftEntity;
    }
}
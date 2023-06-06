package com.example.avabilityb.service;

import com.example.avabilityb.model.dto.ShiftCreationDto;
import com.example.avabilityb.model.dto.ShiftDto;
import com.example.avabilityb.model.entity.ShiftEntity;
import com.example.avabilityb.model.entity.ShiftStatusEntity;
import com.example.avabilityb.model.entity.UserEntity;
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
        List<ShiftEntity> newShifts = shiftEntities.stream()
                .filter(shift -> shift.getShiftId() == -1 && !shift.getTitle().equals("TO_DELETE"))
                .collect(Collectors.toList());
        List<ShiftEntity> shiftsToDelete = shiftEntities.stream()
                .filter(shift -> shift.getShiftId() != -1 && shift.getTitle().equals("TO_DELETE"))
                .collect(Collectors.toList());
        shiftRepository.saveAll(newShifts);
        shiftRepository.deleteAll(shiftsToDelete);
    }
    public void saveShiftsForUsername(List<ShiftCreationDto> shifts, String mail) {
        UserEntity user = userService.getUserByMail(mail);
        Long userId = user.getId();
        var optionalCalendarEntity = calendarRepository.findByUserId(userId);
        if (optionalCalendarEntity.isEmpty()) {
            throw new IllegalStateException("Calendar of userId " + userId + " cannot be found");
        }
        var shiftEntities = shifts.stream()
                .map(this::convertToShiftEntity)
                .collect(Collectors.toList());
        shiftEntities.forEach(c -> c.setCalendar(optionalCalendarEntity.get()));
        List<ShiftEntity> newShifts = shiftEntities.stream()
                .filter(shift -> shift.getShiftId() == -1 && !shift.getTitle().equals("TO_DELETE"))
                .collect(Collectors.toList());
        List<ShiftEntity> shiftsToDelete = shiftEntities.stream()
                .filter(shift -> shift.getShiftId() != -1 && shift.getTitle().equals("TO_DELETE"))
                .collect(Collectors.toList());
        shiftRepository.saveAll(newShifts);
        shiftRepository.deleteAll(shiftsToDelete);
    }

    private ShiftDto convertToShiftDto(ShiftEntity shiftEntity) {
        ShiftDto shiftDto = new ShiftDto();
        shiftDto.setId(shiftEntity.getShiftId());
        shiftDto.setTitle(shiftEntity.getTitle());
        shiftDto.setStart(shiftEntity.getStartTime());
        shiftDto.setEnd(shiftEntity.getEndTime());
        return shiftDto;
    }
    public List<ShiftDto> getShifts() {
        Long userId = userService.getCurrentUserId();
        List<ShiftEntity> shiftEntities = shiftRepository.findAllByEmployeeId(userId);
        return shiftEntities.stream()
                .map(this::convertToShiftDto)
                .collect(Collectors.toList());
    }
    public List<ShiftDto> getShiftsForUsername(String mail) {
        UserEntity user = userService.getUserByMail(mail);
        List<ShiftEntity> shiftEntities = shiftRepository.findAllByEmployeeId(user.getId());
        return shiftEntities.stream()
                .map(this::convertToShiftDto)
                .collect(Collectors.toList());
    }


    private ShiftEntity convertToShiftEntity(ShiftCreationDto shiftCreationDto) {
        ShiftStatusEntity shiftStatus = new ShiftStatusEntity();
        shiftStatus.setId(1);
        ShiftEntity shiftEntity = new ShiftEntity();
        shiftEntity.setTitle(shiftCreationDto.getTitle());
        shiftEntity.setShiftId(shiftCreationDto.getId());
        //shiftEntity.setCalendar(shiftCreationDto.getCalendar());
        shiftEntity.setStartTime(shiftCreationDto.getStart());
        shiftEntity.setEndTime(shiftCreationDto.getEnd());
        shiftEntity.setTitle(shiftCreationDto.getTitle());
        shiftEntity.setShiftStatus(shiftStatus);
        return shiftEntity;
    }
}
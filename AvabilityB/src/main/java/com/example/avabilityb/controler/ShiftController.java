package com.example.avabilityb.controler;

import com.example.avabilityb.model.dto.ShiftCreationDto;
import com.example.avabilityb.model.dto.ShiftDto;
import com.example.avabilityb.service.ShiftService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShiftController {

    private final ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }
    @ApiOperation("Add shifts")
    @PostMapping("/addshifts")
    public ResponseEntity<String> saveShifts(@RequestBody List<ShiftCreationDto> shifts) {
        shiftService.saveShifts(shifts);
        return new ResponseEntity<>("Shifts saved successfully", HttpStatus.OK);
    }
    @ApiOperation("Return Shifts")
    @GetMapping("/shifts")
    public List<ShiftDto> getShiftsForCurrentUser() {
        List<ShiftDto> shifts = shiftService.getShifts();
        return shifts;
    }
    @ApiOperation("Return Shifts for user")
    @GetMapping("/shifts/{mail}")
    public List<ShiftDto> getShiftsForCurrentUser(@PathVariable String mail) {
        List<ShiftDto> shifts = shiftService.getShiftsForUsername(mail);
        return shifts;
    }
    @ApiOperation("Add shifts for user")
    @PostMapping("/addshifts/{mail}")
    public ResponseEntity<String> saveShifts(@RequestBody List<ShiftCreationDto> shifts,@PathVariable String mail) {
        shiftService.saveShiftsForUsername(shifts,mail);
        return new ResponseEntity<>("Shifts saved successfully", HttpStatus.OK);
    }
}
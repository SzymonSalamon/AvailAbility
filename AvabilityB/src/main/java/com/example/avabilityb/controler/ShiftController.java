package com.example.avabilityb.controler;

import com.example.avabilityb.model.dto.ShiftCreationDto;
import com.example.avabilityb.service.ShiftService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
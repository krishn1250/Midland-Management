package com.school.midland.userservice.controller;// In package com.school.midland.userservice.controller
import com.school.midland.userservice.dto.TimetableSlotRequestDTO;
import com.school.midland.userservice.service.TimetableSlotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timetable-slots")
@RequiredArgsConstructor
public class TimetableSlotController {

    private final TimetableSlotService timetableSlotService;

    @PostMapping
    public ResponseEntity<Void> createTimetableSlot(@Valid @RequestBody TimetableSlotRequestDTO request) {
        timetableSlotService.createTimetableSlot(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    // You would also add GET, PUT, DELETE endpoints here later
}
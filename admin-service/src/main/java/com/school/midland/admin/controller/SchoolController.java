package com.school.midland.admin.controller;

import com.school.midland.admin.dtos.school.SchoolDto;
import com.school.midland.admin.service.school.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/midland/admin/schools")
public class SchoolController {

    private final SchoolService schoolService;


    @PostMapping("/create")
    public ResponseEntity<SchoolDto> createSchool(@RequestBody SchoolDto schoolDto) {
        return ResponseEntity.ok(schoolService.createSchool(schoolDto));
    }


    @GetMapping("/all")
    public ResponseEntity<List<SchoolDto>> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAllSchools());
    }


    @GetMapping("/get/{schoolCode}")
    public ResponseEntity<SchoolDto> getSchoolByCode(@PathVariable String schoolCode) {
        return ResponseEntity.ok(schoolService.getSchoolBySchoolCode(schoolCode));
    }

    @GetMapping("/get/{city}")
    public ResponseEntity<List<SchoolDto>> getSchoolByCity(@PathVariable String city){
        return ResponseEntity.ok(schoolService.getSchoolByCity(city));
    }

    @GetMapping("/get/{country}")
    public ResponseEntity<List<SchoolDto>> getSchoolByCountry(@PathVariable String country){
        return ResponseEntity.ok(schoolService.getSchoolByCountry(country));
    }

    @GetMapping("/get/{state}")
    public ResponseEntity<List<SchoolDto>> getSchoolByState(@PathVariable String state){
        return  ResponseEntity.ok(schoolService.getSchoolByState(state));
    }

    @GetMapping("/get/school/")
    public ResponseEntity<List<SchoolDto>> getSchoolByLocation(@RequestParam("country") String country
            ,@RequestParam("state") String state, @RequestParam("city") String city){
        return ResponseEntity.ok(schoolService.getSchoolByLocation(country,state,city));
    }


    @PutMapping("/{schoolUid}/update")
    public ResponseEntity<SchoolDto> updateSchool(@PathVariable String schoolCode,
                                                  @RequestBody SchoolDto schoolDto) {
        return ResponseEntity.ok(schoolService.updateSchool(schoolCode, schoolDto));
    }


    @DeleteMapping("/{schoolUid}/deactivate")
    public ResponseEntity<String> deactivateSchool(@PathVariable UUID schoolUid) {
        schoolService.deactivateSchool(schoolUid);
        return ResponseEntity.ok("School deactivated successfully");
    }
}
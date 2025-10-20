package com.school.midland.userservice.mappers;

import com.school.midland.commonlib.dtos.AttendanceDto;
import com.school.midland.userservice.dto.MarkAttendanceRequest;
import com.school.midland.userservice.dto.MarkAttendanceResponse;
import com.school.midland.userservice.dto.StudentAttendanceEntry;
import com.school.midland.userservice.models.Attendance;

import java.util.ArrayList;
import java.util.List;

public class AttendanceMapper {

    public static AttendanceDto toDto(Attendance attendance) {
        return AttendanceDto.builder()
                .id(attendance.getId())
                .studentUid(attendance.getStudentUid())
                .admissionNumber(attendance.getAdmissionNumber())
                .gradeLevel(attendance.getGradeLevel())
                .section(attendance.getSection())
                .academicYear(attendance.getAcademicYear())
                .attendanceDate(attendance.getAttendanceDate())
                .status(attendance.getStatus().name())
                .periodNumber(attendance.getPeriodNumber())
                .subjectCode(attendance.getSubjectCode())
                .subjectName(attendance.getSubjectName())
                .teacherCode(attendance.getTeacherCode())
                .remarks(attendance.getRemarks())
                .createdBy(attendance.getCreatedBy())
                .updatedBy(attendance.getUpdatedBy())
                .createdAt(attendance.getCreatedAt().toLocalDate())
                .updatedAt(attendance.getUpdatedAt().toLocalDate())
                .build();
    }
    public static MarkAttendanceResponse markAttendanceResponse(List<Attendance> attendanceList){
          MarkAttendanceResponse markAttendanceResponse=new MarkAttendanceResponse();
          List<StudentAttendanceEntry>entries=new ArrayList<>();
          if(attendanceList.get(0)!=null){
              markAttendanceResponse.setAttendanceDate(attendanceList.get(0).getAttendanceDate());
              markAttendanceResponse.setAcademicYear(attendanceList.get(0).getAcademicYear());
              markAttendanceResponse.setSection(attendanceList.get(0).getSection());
              markAttendanceResponse.setPeriodNumber(attendanceList.get(0).getPeriodNumber());
              markAttendanceResponse.setGradeLevel(attendanceList.get(0).getGradeLevel());
              markAttendanceResponse.setCreatedBy(attendanceList.get(0).getCreatedBy());
              markAttendanceResponse.setSubjectName(attendanceList.get(0).getSubjectName());
              markAttendanceResponse.setTeacherCode(attendanceList.get(0).getTeacherCode());

          }

          for(Attendance attendance:attendanceList){
              StudentAttendanceEntry studentAttendanceEntry=new StudentAttendanceEntry();
//              studentAttendanceEntry.setStudentUid(attendance.getStudentUid());
              studentAttendanceEntry.setStatus(attendance.getStatus().name());
              studentAttendanceEntry.setAdmissionNumber(attendance.getAdmissionNumber());
              studentAttendanceEntry.setRemarks(attendance.getRemarks());
              entries.add(studentAttendanceEntry);
          }
          markAttendanceResponse.setAttendanceList(entries);
          return  markAttendanceResponse;
    }
    public static Attendance toEntity(AttendanceDto dto) {
        return Attendance.builder()
                .id(dto.getId())
                .studentUid(dto.getStudentUid())
                .admissionNumber(dto.getAdmissionNumber())
                .gradeLevel(dto.getGradeLevel())
                .section(dto.getSection())
                .academicYear(dto.getAcademicYear())
                .attendanceDate(dto.getAttendanceDate())
                .status(Attendance.AttendanceStatus.valueOf(dto.getStatus()))
                .periodNumber(dto.getPeriodNumber())
                .subjectCode(dto.getSubjectCode())
                .subjectName(dto.getSubjectName())
                .teacherCode(dto.getTeacherCode())
                .remarks(dto.getRemarks())
                .createdBy(dto.getCreatedBy())
                .updatedBy(dto.getUpdatedBy())
                .build();
    }
}
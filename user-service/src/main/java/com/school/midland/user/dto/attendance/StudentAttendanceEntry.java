    package com.school.midland.user.dto.attendance;

    import lombok.Data;

    @Data
    public  class StudentAttendanceEntry {
        private String  email;
        private String admissionNumber;
    //    private String rollNo;
    //    private String fullName;
        private String status;
        private String remarks;
    }
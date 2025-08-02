package com.school.midland.commonlib.constants;

import java.util.Locale;

public enum DayOfWeek {
    SUNDAY("SUNDAY", "SUN", "SU"),
    MONDAY("MONDAY", "MON", "MO"),
    TUESDAY("TUESDAY", "TUE", "TU"),
    WEDNESDAY("WEDNESDAY", "WED", "WE"),
    THURSDAY("Thursday", "Thu", "Th"),
    FRIDAY("FRIDAY", "FRI", "FR"),
    SATURDAY("SATURDAY", "SAT", "SA");

    private final String fullName;
    private final String abbreviation;
    private final String shortestName;

    DayOfWeek(String fullName, String abbreviation, String shortestName) {
        this.fullName = fullName;
        this.abbreviation = abbreviation;
        this.shortestName = shortestName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getShortestName() {
        return shortestName;
    }


    public static DayOfWeek fromString(String dayString) {
        if (dayString == null || dayString.trim().isEmpty()) {
            return null;
        }
        String normalizedString = dayString.trim().toUpperCase();
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.fullName.toUpperCase().equals(normalizedString) ||
                    day.abbreviation.toUpperCase().equals(normalizedString) ||
                    day.shortestName.toUpperCase().equals(normalizedString)) {
                return day;
            }
        }
        return null; // No matching day found
    }


}
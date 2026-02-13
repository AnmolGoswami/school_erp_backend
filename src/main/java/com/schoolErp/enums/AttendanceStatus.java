package com.schoolErp.enums;

public enum AttendanceStatus {
    PRESENT,          // P
    ABSENT,           // A
    LATE,             // L
    HALF_DAY,         // HD (morning or afternoon)
    ON_LEAVE,         // Leave approved
    HOLIDAY,          // Auto-filled for non-working days
    WEEKEND,          // Auto
    OTHER             // Custom
}

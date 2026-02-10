package com.schoolErp.enums;

public enum LeadStatus {
    NEW_LEAD,               // Just captured
    ENQUIRY,                // Basic info collected
    COUNSELING_SCHEDULED,
    COUNSELING_DONE,
    FOLLOW_UP,
    TEST_SCHEDULED,
    TEST_GIVEN,
    OFFER_SENT,
    OFFER_ACCEPTED,
    ADMISSION_COMPLETED,    // → Convert to Student
    REJECTED,
    LOST,
    CONVERTED               // Final - linked to Student entity
}

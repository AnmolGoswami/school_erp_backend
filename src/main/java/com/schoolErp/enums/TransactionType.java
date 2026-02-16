package com.schoolErp.enums;

public enum TransactionType {

    // Inward
    PURCHASE,
    RETURN_FROM_USER,
    ADJUSTMENT_POSITIVE,
    TRANSFER_IN,

    // Outward
    ISSUE_TO_USER,
    ISSUE_TO_STUDENT,
    ADJUSTMENT_NEGATIVE,
    TRANSFER_OUT,
    DAMAGE,
    WRITE_OFF
}

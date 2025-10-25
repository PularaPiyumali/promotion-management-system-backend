package com.example.promotion_management_system.util.exception;

public class PromotionManagementSystemException extends RuntimeException {
    private final int status;

    public PromotionManagementSystemException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
package org.example.entity;

public enum EmployeeStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    TERMINATED("Terminated"),
    ON_LEAVE("On Leave");
    
    private final String displayName;
    
    EmployeeStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
} 
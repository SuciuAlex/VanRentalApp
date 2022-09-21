package com.example.VanRentalApp.config;

public enum ApplicationSecurityUserPermissionConfig {
    READ("user:read"),
    UPDATE("operator:update"),
    WRITE("admin:write"),
    DELETE("admin:delete");


    private final String permission; // this corresponds to the values left of the user permissions

    ApplicationSecurityUserPermissionConfig(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

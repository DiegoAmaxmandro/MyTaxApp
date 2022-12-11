package com.project.mytaxapp.mytaxapp.models;


//This is the enum "Role" that is used as status of the user, so, though this role the user gets access to the accountants or user views.

public enum Role {
    USER("User"),
    ACCOUNTANT("Accountant");

    private final String value;

    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

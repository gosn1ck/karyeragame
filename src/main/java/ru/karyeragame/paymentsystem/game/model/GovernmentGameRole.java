package ru.karyeragame.paymentsystem.game.model;

public enum GovernmentGameRole {

    CHAIRMAN_OF_THE_GOVERNMENT("Chairman of the Government"),
    TAX_INSPECTOR("Tax Inspector"),
    POLICE("Police"),
    INSTITUTE("Institute"),
    MEDICAL("Medical (Service of Household)"),
    LICENSE_INSPECTOR("License Inspector"),
    LABOR_INSPECTOR("Labor Inspector"),
    JOB_EXCHANGE("Job Exchange"),
    FOREIGN_MINISTER("Foreign Minister"),
    CENTRAL_BANK("Central Bank");

    private final String description;

    GovernmentGameRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

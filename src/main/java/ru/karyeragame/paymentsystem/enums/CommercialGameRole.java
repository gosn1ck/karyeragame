package ru.karyeragame.paymentsystem.enums;

public enum CommercialGameRole {

    FARM("Farm"),
    MINING("Mining"),
    MACHINE_FACTORY("Machine Factory"),
    TEXTILE_FACTORY("Textile Factory"),
    SHOPPING_CENTER("Shopping Center"),
    FOREIGN_TRADE("Foreign Trade"),
    COMMERCIAL_BANK("Commercial Bank");

    private final String description;

    CommercialGameRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

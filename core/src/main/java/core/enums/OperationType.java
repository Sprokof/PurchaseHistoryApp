package core.enums;

import core.exceptions.UnknownOperationException;

public enum OperationType {
    TRANSFERS("transfers"),
    MARKETPLACES("marketplaces"),
    SUPERMARKETS("supermarkets"),
    TRANSPORTS("transports"),
    SUBSCRIPTIONS("subscriptions"),
    COMMISSION("commission"),
    CAFE_AND_RESTAURANTS("cafe_and_restaurants"),
    OTHERS("others");
    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    public static OperationType ignoreCaseValueOf(String value) throws UnknownOperationException {
        for(OperationType type : OperationType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new UnknownOperationException("Operation type " + value + " is not exist");
    }

    public String getValue() {
        return value;
    }
}

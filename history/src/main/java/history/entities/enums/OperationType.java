package history.entities.enums;

public enum OperationType {
    TRANSFERS("transfers"),
    MARKETPLACES("marketplaces"),
    SUPERMARKETS("supermarkets"),
    TRANSPORTS("transports"),
    SUBSCRIPTIONS("subscriptions"),
    COMMISSION("commission"),
    CAFE_AND_RESTAURANTS("cafe_and_restaurants"),
    OTHERS("others"),
    UNKNOWN("unknown");
    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    public static OperationType ignoreCaseValueOf(String value) {
        for(OperationType type : OperationType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return UNKNOWN;
    }

    public String getValue() {
        return value;
    }
}

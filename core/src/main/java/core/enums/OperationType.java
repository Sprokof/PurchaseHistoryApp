package core.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.exceptions.UnknownOperationException;

public enum OperationType {
    @JsonProperty("transfers")
    TRANSFERS("transfers"),
    @JsonProperty("marketplaces")
    MARKETPLACES("marketplaces"),
    @JsonProperty("supermarkets")
    SUPERMARKETS("supermarkets"),
    @JsonProperty("transports")
    TRANSPORTS("transports"),
    @JsonProperty("subscriptions")
    SUBSCRIPTIONS("subscriptions"),
    @JsonProperty("commission")
    COMMISSION("commission"),
    @JsonProperty("cafe_and_restaurants")
    CAFE_AND_RESTAURANTS("cafe_and_restaurants"),
    @JsonProperty("others")
    OTHERS("others");
    private final String value;

    OperationType(String value) {
        this.value = value;
    }

    public static OperationType ignoreCaseValueOf(String value) throws UnknownOperationException {
        for(OperationType type : OperationType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new UnknownOperationException("operation type " + value + " not exist");
    }

    public String getValue() {
        return value;
    }
}

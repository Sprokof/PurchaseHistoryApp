module core {
    exports core.util;
    exports core.json;
    exports core.dto;
    exports core.exceptions;
    exports core.enums;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires jackson.datatype.hibernate5;
    requires lombok;
}
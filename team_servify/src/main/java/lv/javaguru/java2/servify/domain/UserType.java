package lv.javaguru.java2.servify.domain;

public enum UserType {

    CUSTOMER, //can make orders and look historical data
    MANAGER, //manage application, prices, users e.t.c.
    SYSTEM, // ???
    ANONYMOUS //by default can make order without save history

}

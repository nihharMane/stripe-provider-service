package com.stripeIntegration.payments.constants;

public class Constant {
    public static final String CANCEL_URL = "cancel_url";
    public static final String Create_Session_Mode = "mode";
    public static final String SUCCESS_URL = "success_url";
    public static final String PAYMENT_MODE = "payment";
    public static final String LINE_ITEMS_QUANTITY = "quantity";
    public static final String LINE_ITEMS_PRICE_DATA_CURRENCY = "currency";
    public static final String LINE_ITEMS_PRICE_DATA_UNIT_AMOUNT = "unit_amount";
    public static final String LINE_ITEMS_PRICE_DATA_PRODUCT_NAME = "name";
    public static final String LINE_ITEMS_BRACKET_FORMAT = "line_items[%d][%s]";
    public static final String PRICE_DATA_BRACKET_FORMAT = "line_items[%d][price_data][%s]";
    public static final String PRODUCT_DATA_BRACKET_FORMAT = "line_items[%d][price_data][product_data][%s]";

    private Constant() {
       //private constructor to prevent instantiation
    }
}

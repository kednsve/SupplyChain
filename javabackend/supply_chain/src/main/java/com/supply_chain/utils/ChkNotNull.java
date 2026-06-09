package com.supply_chain.utils;

public class ChkNotNull {
    public static boolean check(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof String) {
            return !((String) object).isBlank();
        }
        return true;
    }
}

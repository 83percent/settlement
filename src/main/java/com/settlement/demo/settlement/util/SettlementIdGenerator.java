package com.settlement.demo.settlement.util;

import java.util.UUID;

public class SettlementIdGenerator {

    static final String PREFIX = "SETTLEMENT-";

    public static String generate() {
        return PREFIX + UUID.randomUUID();
    }
}

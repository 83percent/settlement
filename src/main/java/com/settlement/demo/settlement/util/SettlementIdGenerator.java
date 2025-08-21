package com.settlement.demo.settlement.util;

public class SettlementIdGenerator {

    static final String PREFIX = "SETTLEMENT-";

    public static String generate() {
        // TODO : 정산 아이디 생성을 위한 로직 구현
        return PREFIX + System.currentTimeMillis();
    }
}

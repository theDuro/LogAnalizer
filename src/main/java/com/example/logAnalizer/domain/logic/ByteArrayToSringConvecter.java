package com.example.logAnalizer.domain.logic;

import java.nio.charset.StandardCharsets;

public class ByteArrayToSringConvecter {
    public static String convert(byte[] encodedBytes){
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }
}

package com.example.logAnalizer.service;

import java.util.Date;

public class DataConvecter {
    private static final int ADD_TO_DATE =2;
    public static Date convert(Date date){
        return new Date(date.getYear(),date.getMonth(),date.getDay()+ADD_TO_DATE);
    }
}

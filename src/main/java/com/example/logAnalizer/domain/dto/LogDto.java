package com.example.logAnalizer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Getter
@AllArgsConstructor
@Component
@RequiredArgsConstructor
@Builder
public class LogDto {
    private Long id;
    private String log;
    private Date logDate;
    private String logClass;

    public LogDto(String log, Date logDate, String logClass) {//todo delte metchods bc off
        this.log = log;
        this.logDate = logDate;
        this.logClass = logClass;
    }

//    public static LogDto getFromLog(String log) {//factory metchod
//       Date logDate = extractDateFromLog(log);
//       String logClass = getClassNameFromLog(log);
//        return new LogDto(log,logDate,logClass);
//    }
//    private static Date extractDateFromLog(String log) {
//        String[] logParts = log.split(" ", 2);
//        String logDateStr = logParts[0];
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//
//        try {
//            return dateFormat.parse(logDateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    private static String getClassNameFromLog(String log){
//        return Arrays.stream(log.split("\\."))
//                .filter(LogDto::startsWithUppercase)
//                .findFirst()
//                .get();
//    }
//    private static boolean startsWithUppercase(String input) {
//        return input != null && !input.isEmpty() && Character.isUpperCase(input.charAt(0));
//    }


}

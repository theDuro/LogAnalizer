package com.example.logAnalizer.domain.logic;

import com.example.logAnalizer.domain.dto.LogDto;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LogCreator {
    public static List<LogDto> getLogs(String logContent){
        List<String> splitedStrings = parseLogs(logContent);
        return splitedStrings.stream()
                .map(LogCreator::getFromLog)
                .toList();

    }
    public static List<String> parseLogs(String logContent) {
        List<String> logs = new ArrayList<>();
        String[] lines = logContent.split("\n");
        StringBuilder logEntry = new StringBuilder();
        for (String line : lines) {
            if (startsWithNumber(line)) {
                if (logEntry.length() > 0) {
                    logs.add(logEntry.toString().trim());
                    logEntry.setLength(0);
                }
            }
            logEntry.append(line).append("\n");
        }
        if (logEntry.length() > 0) {
            logs.add(logEntry.toString().trim());
        }
        return logs;
    }
    private static LogDto getFromLog(String log) {
        Date logDate = extractDateFromLog(log);
        String logClass = getClassNameFromLog(log);
        return new LogDto(log,logDate,logClass);
    }
    private static boolean startsWithNumber(String line) {
        return line.matches("^\\d.*");
    }

    private static Date extractDateFromLog(String log) {
        String[] logParts = log.split(" ", 2);
        String logDateStr = logParts[0];
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            return dateFormat.parse(logDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getClassNameFromLog(String log){
        String className=  Arrays.stream(log.split("\\."))
                .filter(LogCreator::startsWithUppercase)
                .findFirst()
                .get();

        if (className.length() > 1000) {
            className = className.substring(0, 1000);
        }
        return removeDifferences(className);
    }
    private static boolean startsWithUppercase(String input) {
        return input != null && !input.isEmpty() && Character.isUpperCase(input.charAt(0));
    }

    private static String removeDifferences(String log){
        String replacedString = log.replaceAll("\\(.*?\\)", "(XXX)");
        replacedString = replacedString.replaceAll("\\[.*?]", "[XXX]");
        return replacedString;
    }

}



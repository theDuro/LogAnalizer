package com.example.logAnalizer.logic;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class LogSeparator {
    public  List<String> parseLogs(String logContent) {
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
    private  boolean startsWithNumber(String line) {
        return line.matches("^\\d.*");
    }
}

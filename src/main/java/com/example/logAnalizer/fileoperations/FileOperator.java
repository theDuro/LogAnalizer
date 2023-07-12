package com.example.logAnalizer.fileoperations;
import com.example.logAnalizer.model.LogDto;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
@Component
public class FileOperator {
    public  String readFileToString(String filePath) throws IOException {
        byte[] encodedBytes = Files.readAllBytes(Path.of(filePath));
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }
    private static void saveToFile(Map<String , List<LogDto>> gruppedLogs){//todo need check


    }


}


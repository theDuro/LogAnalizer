package com.example.logAnalizer.domain.logic;
import com.example.logAnalizer.domain.dto.LogDto;
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
        return  ByteArrayToSringConvecter.convert(encodedBytes);
    }

}


package com.example.logAnalizer.contlorel;

import com.example.logAnalizer.model.LogDto;
import com.example.logAnalizer.service.LogsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LogControler {
    private final LogsService logsService;
    public LogControler(LogsService logsService) {
        this.logsService = logsService;
    }
    @CrossOrigin
    @GetMapping(value = "/organizedLogs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String , List<LogDto>>> getOrganizedLogs() {
        try {
            Map<String , List<LogDto>> organizedLogs = logsService.logOperations();
            return new ResponseEntity<>(organizedLogs, HttpStatus.OK);
        } catch (Exception e){
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

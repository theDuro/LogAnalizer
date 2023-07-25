package com.example.logAnalizer.contlorel;

import com.example.logAnalizer.domain.dto.LogDto;
import com.example.logAnalizer.domain.dto.LogStructureDto;
import com.example.logAnalizer.domain.enity.Log;
import com.example.logAnalizer.domain.enity.LogStructure;
import com.example.logAnalizer.domain.mapper.LogMapMapper;
import com.example.logAnalizer.domain.mapper.LogStructureMapper;
import com.example.logAnalizer.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class LogControler {
    private final LogsService logsService;
    @Autowired
    public LogControler(LogsService logsService) {
        this.logsService = logsService;
    }
    @GetMapping(value = "/organizedLogs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String , List<LogDto>>> getOrganizedLogs() {
        try {
            Map<String , List<LogDto>> organizedLogs = logsService.logOperations();
            return new ResponseEntity<>(organizedLogs, HttpStatus.OK);
        } catch (Exception e){
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/load-data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String , List<LogDto>>> loadData() {
        try {
            Map<String , List<LogDto>> organizedLogs = logsService.logOperations();
           List<LogStructureDto> logStructureDtos= LogMapMapper.toLogStructDto(organizedLogs);
//           List<LogStructure> logStructures = LogStructureMapper.toLogStructures(logStructureDtos);
           logsService.saveAllLogStructure(logStructureDtos);
            return new ResponseEntity<>(organizedLogs, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value = "/Logs")
    public ResponseEntity<List<LogDto>> getAllLogs() {
        List<LogDto> logsDto = logsService.getAllLogs();
        return new ResponseEntity<>(logsDto, HttpStatus.OK);
    }
    @GetMapping(value = "/Log/{id}")
    public ResponseEntity<LogDto> getLogById(@PathVariable long id) {
        LogDto logDto = logsService.getLogById(id);
        return new ResponseEntity<>(logDto, HttpStatus.OK);
    }
    @GetMapping(value = "/LogStructures")
    public ResponseEntity<List<LogStructureDto>> getAllLogStructures() {
        List<LogStructureDto> logsStructureDto = logsService.getAllLogStructure();
        return new ResponseEntity<>(logsStructureDto, HttpStatus.OK);
    }
    @GetMapping(value = "/LogStructure/{id}")
    public ResponseEntity<LogStructureDto> getLogStructureById(@PathVariable long id) {
        LogStructureDto logStructureDto =  logsService.getLogStructureById(id);
        return new ResponseEntity<>(logStructureDto, HttpStatus.OK);
    }
    @GetMapping(value = "/LogDatefromStructure/{id}")
    public ResponseEntity<List<LogDto>> getbyDateAndid(@PathVariable("id") long id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        List<LogDto> logsDto = logsService.getLogsByIdAndDate(id, date);
        return new ResponseEntity<>(logsDto, HttpStatus.OK);
    }
    @GetMapping(value = "/LogDate")
    public ResponseEntity<List<LogDto>> getbyDate(@RequestBody Date date) {
        List<LogDto> logsDto =logsService.getLogsByDate(date);
        return new ResponseEntity<>(logsDto, HttpStatus.OK);
    }

    @GetMapping(value = "/dates")
    public ResponseEntity<List<Date>> getDate() {
        List<Date> dates =logsService.getAlDate();
        return new ResponseEntity<>(dates, HttpStatus.OK);
    }
    @GetMapping(value = "/dates/{id}")
    public ResponseEntity<List<Date>> getDateBYId(@PathVariable("id")long id) {
        List<Date> dates =logsService.getDateByStructId(id);
        return new ResponseEntity<>(dates, HttpStatus.OK);
    }
    @GetMapping(value = "/error_name")
    public ResponseEntity<List<String>> getAllClasses() {
        List<String> errors = logsService.getClassesNames();
        return new ResponseEntity<>(errors, HttpStatus.OK);
    }
    @GetMapping(value = "/dates_error/{error}")
    public ResponseEntity<List<Date>> getDateBYId(@PathVariable("error")String error) {
        List<Date> dates =logsService.getDateByStructErrorName(error);
        return new ResponseEntity<>(dates, HttpStatus.OK);
    }
    /////////////////////////////////////////////
    @PostMapping("/binary")
    public ResponseEntity<List<LogStructure>> saveFromBinaryArray(@RequestBody byte[] bytes) {
        List<LogStructure> savedLogStructures = logsService.saveFromBinaryArray(bytes);
        return ResponseEntity.ok(savedLogStructures);
    }
    @PostMapping("/single")
    public ResponseEntity<Log> saveLog(@RequestBody LogDto logDto) {
        Log savedLog = logsService.saveLog(logDto);
        return ResponseEntity.ok(savedLog);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Log>> saveAllLogs(@RequestBody List<LogDto> logs) {
        List<Log> savedLogs = logsService.saveAllLogs(logs);
        return ResponseEntity.ok(savedLogs);
    }

    @PostMapping("/structures")
    public ResponseEntity<LogStructure> saveLogStructure(@RequestBody LogStructureDto logStructureDto) {
        LogStructure savedLogStructure = logsService.saveLogStructure(logStructureDto);
        return ResponseEntity.ok(savedLogStructure);
    }






}

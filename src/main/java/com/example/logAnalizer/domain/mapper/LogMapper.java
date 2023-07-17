package com.example.logAnalizer.domain.mapper;

import com.example.logAnalizer.domain.dto.LogDto;
import com.example.logAnalizer.domain.enity.Log;

import java.util.List;
import java.util.Optional;

public class LogMapper {
    public static Log logDtoToLog(LogDto logDto){
        return Log.builder()
                .id(logDto.getId())
                .log(logDto.getLog())
                .logClass(logDto.getLogClass())
                .logDate(logDto.getLogDate())
                .build();
    }
    public static LogDto lodToLogDto(Log log){
        return LogDto.builder()
                .id(log.getId())
                .logDate(log.getLogDate())
                .logClass(log.getLogClass())
                .log(log.getLog())
                .build();
    }
    public static List<Log> toLogList(List<LogDto> logDtoList){
        return logDtoList.stream()
                .map(LogMapper::logDtoToLog)
                .toList();

    }
    public static List<LogDto> toLogDtoList(List<Log> logList){
        return logList.stream()
                .map(LogMapper::lodToLogDto)
                .toList();
    }

}

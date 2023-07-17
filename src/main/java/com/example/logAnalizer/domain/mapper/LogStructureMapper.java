package com.example.logAnalizer.domain.mapper;

import com.example.logAnalizer.domain.dto.LogDto;
import com.example.logAnalizer.domain.dto.LogStructureDto;
import com.example.logAnalizer.domain.enity.Log;
import com.example.logAnalizer.domain.enity.LogStructure;

import java.util.List;

public  class LogStructureMapper {
   public static LogStructure toLogStructure(LogStructureDto logStructureDto){
        List<Log> logList = LogMapper.toLogList(logStructureDto.getLogs());
       LogStructure logStructure = LogStructure.builder()
               .logs(logList)
               .id(logStructureDto.getId())
               .logClass(logStructureDto.getLogClass())
               .build();

       for (Log log : logList) {
           log.setLogStructure(logStructure);
       }
       return logStructure;
    }
   public static LogStructureDto toLogStructureDto(LogStructure logStructure) {
        List<LogDto> logDtoList =LogMapper.toLogDtoList(logStructure.getLogs());
        return LogStructureDto.builder()
                .logs(logDtoList)
                .logClass(logStructure.getLogClass())
                .id(logStructure.getId())
                .build();
    }
    public static List<LogStructureDto> toLogStructureDtos(List<LogStructure> logStructures){
       return logStructures.stream()
               .map(LogStructureMapper::toLogStructureDto)
               .toList();
    }
    public static List<LogStructure> toLogStructures(List<LogStructureDto> logStructureDtos){
       return logStructureDtos.stream()
               .map(LogStructureMapper::toLogStructure)
               .toList();
    }
}

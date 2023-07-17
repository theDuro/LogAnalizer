package com.example.logAnalizer.domain.mapper;

import com.example.logAnalizer.domain.dto.LogDto;
import com.example.logAnalizer.domain.dto.LogStructureDto;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LogMapMapper {
    public static List<LogStructureDto> toLogStructDto(Map<String, List<LogDto>> logMap) {
        List<LogStructureDto> logStructureDtos = new LinkedList<>();
        for (Map.Entry<String, List<LogDto>> entry : logMap.entrySet()) {
            String key = entry.getKey();
            List<LogDto> value = entry.getValue();
            logStructureDtos.add(
                    LogStructureDto.builder()
                            .logClass(key)
                            .logs(value)
                    .build()
            );
        }
        return  logStructureDtos;
    }
}

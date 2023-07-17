package com.example.logAnalizer.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class LogStructureDto {
    private Long id;
    private String logClass;
    private List<LogDto> logs;

}

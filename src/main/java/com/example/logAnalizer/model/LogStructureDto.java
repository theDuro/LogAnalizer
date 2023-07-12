package com.example.logAnalizer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
@Builder
public class LogStructureDto {
    private long id;
    private String className;

}

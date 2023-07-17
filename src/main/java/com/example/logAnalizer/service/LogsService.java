package com.example.logAnalizer.service;

import com.example.logAnalizer.domain.dto.LogStructureDto;
import com.example.logAnalizer.domain.enity.LogStructure;
import com.example.logAnalizer.domain.logic.ByteArrayToSringConvecter;
import com.example.logAnalizer.domain.mapper.LogMapMapper;
import com.example.logAnalizer.domain.mapper.LogStructureMapper;
import com.example.logAnalizer.domain.mapper.LogMapper;
import com.example.logAnalizer.domain.enity.Log;
import com.example.logAnalizer.domain.logic.FileOperator;
import com.example.logAnalizer.domain.logic.LogGruper;
import com.example.logAnalizer.domain.logic.LogCreator;
import com.example.logAnalizer.domain.dto.LogDto;
import com.example.logAnalizer.domain.dto.NoDateInLogException;
import com.example.logAnalizer.domain.repository.LogRepository;
import com.example.logAnalizer.domain.repository.LogStructureRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class LogsService {
//    @Value("${reading-file}")
    private final  String READ_FILE ="GcmWebServices_Trace.txt";
    private final LogGruper logGruper;
    private final FileOperator fileOperator;
    private final LogRepository logRepository;
    private final LogStructureRepository logStructureRepository;
    @Autowired
    public LogsService(LogGruper logGruper
            , FileOperator fileOperator
            , LogRepository logRepository
            , LogStructureRepository logStructureRepository
    ) {
        this.logGruper = logGruper;
        this.fileOperator = fileOperator;
        this.logRepository = logRepository;
        this.logStructureRepository = logStructureRepository;
    }
//////READ-------------------------------------------------------------------------------------------

    public Map<String ,List<LogDto>> logOperations() throws IOException, NoDateInLogException {
        String  allLogs = fileOperator.readFileToString(READ_FILE);
        return logGruper.grupLogsHoldMap(allLogs);
    }
    public  List<LogDto> getAllLogs()  {
        List<Log> logList = logRepository.findAll();
        return LogMapper.toLogDtoList(logList);
    }
    public List<LogDto> getLogsByDate(Date date){
        List<Log> logList = logRepository.findByLogDate(date);
        return LogMapper.toLogDtoList(logList);
    }

    public List<LogStructureDto> getAllLogStructure(){
        List<LogStructure> logStructures = logStructureRepository.findAll();
        return LogStructureMapper.toLogStructureDtos(logStructures);
    }

    public LogDto getLogById(Long id){
        Optional<Log> optionalLog = logRepository.findById(id);
        return  LogMapper.lodToLogDto(optionalLog.get());

    }

    public LogStructureDto getLogStructureById(long id){
        Optional<LogStructure> optionalLogStructure = logStructureRepository.findById(id);
        return  LogStructureMapper.toLogStructureDto(optionalLogStructure.get());
    }

    public List<LogDto> getLogsByIdAndDate(long id,Date date){
        Date actualDate = DataConvecter.convert(date);
        List<Log> logList = logRepository.findByLogStructureIdAndLogDate(id,actualDate);

        return LogMapper.toLogDtoList(logList);
    }


/////SAVE-------------------------------------------------------------------------------------------------------------
    public Log saveLog(LogDto logDto){
       return logRepository.save(LogMapper.logDtoToLog(logDto));
}
    public List<Log> saveAllLogs(List<LogDto> logs){
        return  logRepository.saveAll(LogMapper.toLogList(logs));
    }

    public LogStructure saveLogStructure(LogStructureDto logStructure){
        return logStructureRepository.save(LogStructureMapper.toLogStructure(logStructure));
    }

    public List<LogStructure> saveAllLogStructure(List<LogStructureDto> logStructureDtos){
        return  logStructureRepository.saveAll(LogStructureMapper.toLogStructures(logStructureDtos));
    }

    public List<LogStructure> saveFromBinaryArray(byte[] bytes){
        String stringFromBin = ByteArrayToSringConvecter.convert(bytes);
        Map<String ,List<LogDto>> log = logGruper.grupLogsHoldMap(stringFromBin);
        List<LogStructureDto> logStructureDtos = LogMapMapper.toLogStructDto(log);
        return  LogStructureMapper.toLogStructures(logStructureDtos);


    }






}

package com.example.logAnalizer.service;




import com.example.logAnalizer.fileoperations.FileOperator;
import com.example.logAnalizer.logic.LogGruper;
import com.example.logAnalizer.logic.LogSeparator;
import com.example.logAnalizer.model.LogDto;
import com.example.logAnalizer.model.NoDateInLogException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class LogsService {
//    @Value("${reading-file}")
//    private  String READ_FILE ="GcmWebServices_Trace.txt";
    private final LogGruper logGruper;
    private final LogSeparator logSeparator;
    private final FileOperator fileOperator;
    @Autowired
    public LogsService(LogGruper logGruper, LogSeparator logSeparator, FileOperator fileOperator) {
        this.logGruper = logGruper;
        this.logSeparator = logSeparator;
        this.fileOperator = fileOperator;
    }


    private Map<String ,List<LogDto>> gruppedLogsHolder = new HashMap<>();
    public Map<String ,List<LogDto>> logOperations() throws IOException, NoDateInLogException {
        List<String> logs;
        String  READ_FILE ="GcmWebServices_Trace.txt";
        String   allLogs = fileOperator.readFileToString(READ_FILE);
        logs = logSeparator.parseLogs(allLogs);
        gruppedLogsHolder = logGruper.grupLogsHolder(logs);
        return gruppedLogsHolder;
    }
    public Map<String ,List<LogDto>> getOrganizedLogs() throws IOException, NoDateInLogException {
        String READ_FILE ="GcmWebServices_Trace.txt";
        String allLogs = fileOperator.readFileToString(READ_FILE);
        List<String> logs =logSeparator.parseLogs(allLogs);
        gruppedLogsHolder = logGruper.grupLogsHolder(logs);
        return gruppedLogsHolder;

    }





}

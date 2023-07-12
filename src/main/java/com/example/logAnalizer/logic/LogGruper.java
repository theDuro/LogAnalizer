package com.example.logAnalizer.logic;




import com.example.logAnalizer.model.LogDto;
import com.example.logAnalizer.model.NoDateInLogException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
@Component
public class LogGruper {
    public Map<String ,List<LogDto>> grupLogsHolder(List<String> logs)throws NoDateInLogException {
        return logs.stream()
                .map(this::removeDifferences)
                .map(LogDto::getFromLog)
                .collect(groupingBy(LogDto::getLogClass));
    }
   private String removeDifferences(String log){
     String replacedString = log.replaceAll("\\(.*?\\)", "(XXX)");
     replacedString = replacedString.replaceAll("\\[.*?]", "[XXX]");
      return replacedString;
   }
}

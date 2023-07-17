package com.example.logAnalizer.domain.logic;
import com.example.logAnalizer.domain.dto.LogDto;
import com.example.logAnalizer.domain.dto.NoDateInLogException;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
@Component
public class LogGruper {

   private String removeDifferences(String log){
     String replacedString = log.replaceAll("\\(.*?\\)", "(XXX)");
     replacedString = replacedString.replaceAll("\\[.*?]", "[XXX]");
      return replacedString;
   }
    public Map<String ,List<LogDto>> grupLogsHoldMap(String fileText) {
        return LogCreator.getLogs(fileText).stream()
                .collect(groupingBy(LogDto::getLogClass));
    }
}

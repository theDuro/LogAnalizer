package com.example.logAnalizer.domain.enity;

import com.example.logAnalizer.domain.repository.LogRepository;
import com.example.logAnalizer.infrastructure.StaticContextAccessor;
import lombok.*;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;
import java.util.List;
@Builder
@Entity
@Getter
@Table(name = "log_structure")
@NoArgsConstructor
@AllArgsConstructor
public class LogStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "log_class", length = 1000, columnDefinition="Text", unique = true)
    private String logClass;
    @OneToMany(mappedBy = "logStructure",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Log> logs;

    @PostLoad
    public void postLoad() {
        LogRepository logRepository = StaticContextAccessor.getBean(LogRepository.class);
        logs = logRepository.findByLogStructureId(id);
    }

}

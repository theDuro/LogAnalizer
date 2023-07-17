package com.example.logAnalizer.domain.enity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "log", length = 20000,columnDefinition="Text")
    private String log;
    @Column(name="log_date")
    private Date logDate;
    @Column(name = "log_class", length = 1000, columnDefinition="Text")
    private String logClass;
    @ManyToOne
    @JoinColumn(name = "log_structure_id",referencedColumnName = "id")
    @Setter
    private LogStructure logStructure;
//
//    @JoinColumn(name = "log_structure_id")
//    private Long logStructureId;




}

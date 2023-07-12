package com.example.logAnalizer.enity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "log")
    private String log;
    @Column(name="log_date",unique = true)
    private Date logDate;
    @Column(name="log_Class")
    private String logClass;




}

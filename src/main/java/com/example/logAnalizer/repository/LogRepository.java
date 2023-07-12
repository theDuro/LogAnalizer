package com.example.logAnalizer.repository;

import com.example.logAnalizer.enity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {

}

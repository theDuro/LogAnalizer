package com.example.logAnalizer.domain.repository;

import com.example.logAnalizer.domain.enity.LogStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface LogStructureRepository extends JpaRepository<LogStructure,Long> {
    @Query("SELECT DISTINCT s.logClass from LogStructure s")
    List<String> getLogClasses();
    @Query("SELECT s.id from LogStructure s WHERE s.logClass = :errorName")
    long getIdFromErrorName(@Param("errorName") String errorName);

}

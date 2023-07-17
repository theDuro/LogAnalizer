package com.example.logAnalizer.domain.repository;

import com.example.logAnalizer.domain.enity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    void deleteById(@Param("id") Long id);
    @Query("SELECT l FROM Log l WHERE l.logStructure.id = :structureId AND l.logDate = :date")
    List<Log> findByLogStructureIdAndLogDate(@Param("structureId") Long structureId, @Param("date") Date logDate );

    @Query("SELECT l FROM Log l WHERE l.logStructure.id = :structureId")
    List<Log> findByLogStructureId(@Param("structureId") Long structureId);
    @Query("SELECT l FROM Log l WHERE l.logDate = :date ")
    List<Log> findByLogDate(@Param("date") Date logDate);
    Log findById(long id);












}



package com.example.logAnalizer.domain.repository;

import com.example.logAnalizer.domain.enity.LogStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LogStructureRepository extends JpaRepository<LogStructure,Long> {

}

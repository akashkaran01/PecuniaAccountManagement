package com.capgemini.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.project.entity.SequenceGenerator;

@Repository
public interface SequenceGeneratorDao extends JpaRepository<SequenceGenerator, Integer> {

}

package com.capgemini.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.project.entity.Cheque;
@Repository
public interface ChequeEntityDao extends JpaRepository<Cheque, String> {

}

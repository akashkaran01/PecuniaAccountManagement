package com.capgemini.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.project.entity.Account;

@Repository
public interface AccountEntityDao extends JpaRepository<Account, String>{

}

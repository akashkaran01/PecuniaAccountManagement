package com.capgemini.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.project.entity.Customer;

@Repository
public interface CustomerEntityDao extends JpaRepository<Customer, String>{

}

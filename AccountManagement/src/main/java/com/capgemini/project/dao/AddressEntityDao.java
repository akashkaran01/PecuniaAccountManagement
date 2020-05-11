package com.capgemini.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.project.entity.Address;

@Repository
public interface AddressEntityDao extends JpaRepository<Address, String> {

}

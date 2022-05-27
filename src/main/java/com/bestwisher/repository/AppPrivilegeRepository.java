package com.bestwisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bestwisher.model.AppPrivilege;
@Repository
public interface AppPrivilegeRepository extends JpaRepository<AppPrivilege, Long> {

	
}

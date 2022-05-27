package com.bestwisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bestwisher.model.AppUserProfileWork;

@Repository
public interface AppUserProfileWorkRepository extends JpaRepository<AppUserProfileWork, Long> {

}

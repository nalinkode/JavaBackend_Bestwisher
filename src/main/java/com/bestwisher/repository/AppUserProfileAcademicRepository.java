package com.bestwisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bestwisher.model.AppUserProfileAcademic;

@Repository
public interface AppUserProfileAcademicRepository extends JpaRepository<AppUserProfileAcademic, Long> {

}

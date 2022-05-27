package com.bestwisher.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bestwisher.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	@Query(value = "select * from user_birthday a where a.dob = CURRENT_DATE", nativeQuery = true)
	List<AppUser> findUserByTodayDate();

	@Query(value = "select * from user_birthday a where a.dob != CURRENT_DATE", nativeQuery = true)
	List<AppUser> findUserByNoBirthday();

	Optional<AppUser> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value = "select * from app.app_user u where u.id!=:userId", nativeQuery = true)
	List<AppUser> fetchAllExceptUserId(@Param("userId") Long userId);

}

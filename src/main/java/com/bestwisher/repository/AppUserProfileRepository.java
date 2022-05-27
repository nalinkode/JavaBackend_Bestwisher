package com.bestwisher.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bestwisher.model.AppUserProfile;

@Repository
public interface AppUserProfileRepository extends JpaRepository<AppUserProfile, Long> {

	@Query(value = "SELECT * FROM app.app_user_profile p WHERE p.user_id=:userId", nativeQuery = true)
	Optional<AppUserProfile> findByUserId(@Param("userId") Long userId);

}

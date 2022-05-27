package com.bestwisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bestwisher.model.AppUserProfileImages;

@Repository
public interface AppUserProfilePhotoRepository extends JpaRepository<AppUserProfileImages, Long>  {

	@Query(value = "SELECT * FROM app.app_user_profile_images p WHERE p.id=:photoId AND p.user_profile_id=:profileId", nativeQuery = true)
	AppUserProfileImages findAllById(@Param("photoId") Long photoId, @Param("profileId") Long profileId);

}

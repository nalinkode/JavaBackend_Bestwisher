package com.bestwisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bestwisher.model.AppUserCoverImages;

@Repository
public interface AppUserCoverPhotoRepository extends JpaRepository<AppUserCoverImages, Long> {

	@Query(value = "SELECT * FROM app.app_user_cover_images c WHERE c.id=:photoId AND c.user_profile_id=:profileId", nativeQuery = true)
	AppUserCoverImages findAllById(@Param("photoId") Long photoId, @Param("profileId") Long profileId);

}

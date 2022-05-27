package com.bestwisher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bestwisher.model.AppUserPost;

@Repository
public interface AppPostRepository extends JpaRepository<AppUserPost, Long> {

	@Query(value = "SELECT * FROM app.app_posts p WHERE p.user_id=:userId", nativeQuery = true)
	List<AppUserPost> findAllByUserId(@Param("userId") Long userId);

}

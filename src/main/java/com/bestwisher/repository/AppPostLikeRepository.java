package com.bestwisher.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bestwisher.model.AppPostLike;

@Repository
public interface AppPostLikeRepository extends JpaRepository<AppPostLike, Long> {

	@Query(value = "SELECT * FROM app.app_post_like p WHERE p.user_id=:userId AND p.post_id=:postId", nativeQuery = true)
	Optional<AppPostLike> findByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);

	@Query(value = "SELECT COUNT(*) FROM app.app_post_like p WHERE p.post_id=:postId", nativeQuery = true)
	List<AppPostLike> findLikeByPostId(@Param("postId") Long postId);

}

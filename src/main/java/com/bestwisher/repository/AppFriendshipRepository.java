package com.bestwisher.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bestwisher.model.AppFriendship;

@Repository
public interface AppFriendshipRepository extends JpaRepository<AppFriendship, Long> {

	@Query(value = "SELECT * FROM app.app_friendship f WHERE f.request_to_user=:userId AND f.is_request_accepted=false", nativeQuery = true)
	Optional<List<AppFriendship>> findRequestedUser(@Param("userId") Long userId);
	
	@Query(value = "SELECT * FROM app.app_friendship f WHERE f.request_from_user=:userId AND f.is_request_accepted=false", nativeQuery = true)
	Optional<List<AppFriendship>> findPendingRequestedUser(@Param("userId") Long userId);

	@Query(value = "SELECT * FROM app.app_friendship f WHERE f.request_from_user=:requestFromUser AND f.request_to_user=:requestToUser OR f.request_from_user=:requestToUser AND f.request_to_user=:requestFromUser", nativeQuery = true)
	Optional<AppFriendship> existsByRequestFromUserIdAndRequestToUserId(@Param("requestFromUser") Long requestFromUser,
			@Param("requestToUser") Long requestToUser);

	@Query(value = "SELECT * FROM app.app_friendship f WHERE (f.is_request_accepted=:flag AND f.is_friend=:flag ) AND (f.request_from_user=:userId OR f.request_to_user=:userId)", nativeQuery = true)
	Optional<List<AppFriendship>> findFriendsByUserId(@Param("userId") Long userId, @Param("flag") boolean flag);


}

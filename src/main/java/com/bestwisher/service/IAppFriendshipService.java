package com.bestwisher.service;

import java.util.List;

import com.bestwisher.dto.AppFriendshipDto;
import com.bestwisher.dto.AppUserProfileDto;
import com.bestwisher.model.AppFriendship;

public interface IAppFriendshipService {

	AppFriendship addFriend(AppFriendshipDto friendshipDto) throws Exception;

	List<AppUserProfileDto> fetchFriendRequest(Long userId) throws Exception;

	AppFriendship confirmedFriendshipRequest(AppFriendshipDto friendshipDto) throws Exception;

	void deleteFriendRequest(Long friendshipId) throws Exception;

	List<AppUserProfileDto> fetchPendingFriendRequest(Long userId) throws Exception;

	List<AppUserProfileDto> fetchUsersFriends(Long userId) throws Exception;

}

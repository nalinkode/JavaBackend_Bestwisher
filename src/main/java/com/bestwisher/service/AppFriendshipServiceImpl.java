package com.bestwisher.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestwisher.dto.AppFriendshipDto;
import com.bestwisher.dto.AppUserProfileDto;
import com.bestwisher.model.AppFriendship;
import com.bestwisher.model.AppUser;
import com.bestwisher.repository.AppFriendshipRepository;
import com.bestwisher.repository.AppUserRepository;

@Service
public class AppFriendshipServiceImpl implements IAppFriendshipService {

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private AppFriendshipRepository friendshipRepo;

	@Override
	public AppFriendship addFriend(AppFriendshipDto friendshipDto) throws Exception {

		Optional<AppFriendship> friendshipExists = friendshipRepo.existsByRequestFromUserIdAndRequestToUserId(friendshipDto.getRequestFromUser(),
				friendshipDto.getRequestToUser());

		if (friendshipExists.isPresent()) {
			throw new Exception("Friend Request are pending or already had friendship");
		}

		if (friendshipDto.getRequestFromUser() == null) {
			throw new Exception("User profile id not exists");
		}

		if (friendshipDto.getRequestToUser() == null) {
			throw new Exception("Friend id not exists");
		}

		boolean mainUser = userRepository.existsById(friendshipDto.getRequestFromUser());
		boolean friendUser = userRepository.existsById(friendshipDto.getRequestToUser());
		if (!mainUser && !friendUser) {
			throw new Exception("user id not exists in our db");
		}
		AppFriendship friendship = new AppFriendship();
		friendship.setRequestFromUser(friendshipDto.getRequestFromUser());
		friendship.setRequestToUser(friendshipDto.getRequestToUser());
		friendship.setRequestAccepted((false));
		friendship.setFriend(false);
		friendship.setCreatedDate(new Date());
		friendship.setUpdatedDate(new Date());
		friendshipRepo.save(friendship);
		return friendship;
	}

	@Override
	public List<AppUserProfileDto> fetchFriendRequest(Long userId) throws Exception {

		boolean user = userRepository.existsById(userId);
		if (!user) {
			throw new Exception("user id not exists in our db");
		}

		Optional<List<AppFriendship>> requestedUser = friendshipRepo.findRequestedUser(userId);
		List<AppUserProfileDto> requestedUserResponse = new ArrayList<AppUserProfileDto>();
		if (requestedUser.isPresent()) {
			for (AppFriendship friendRequest : requestedUser.get()) {
				Optional<AppUser> friendRequestedUser = userRepository.findById(friendRequest.getRequestFromUser());
				if (friendRequestedUser.isPresent()) {
					AppUser userFromDb = friendRequestedUser.get();

					AppFriendshipDto userFriendshipResponse = new AppFriendshipDto();
					// This is Friendship table id
					userFriendshipResponse.setFriendshipId(friendRequest.getId());
					// This is actual user who log in user id.
					userFriendshipResponse.setRequestFromUser(userFromDb.getId());
					// This is friend requested user id
					userFriendshipResponse.setRequestToUser(userId);
					userFriendshipResponse.setProfileImage(userFromDb.getUserProfile().getCurrentProfileImage());
					userFriendshipResponse.setUserName(userFromDb.getFirstName()+" "+ userFromDb.getLastName());
					userFriendshipResponse.setCurrentDesignation(userFromDb.getUserProfile().getCurrentDesignation());
					userFriendshipResponse.setCurrentCity(userFromDb.getUserProfile().getCurrentCity());
					userFriendshipResponse.setHomeCity(userFromDb.getUserProfile().getHomeCity());
					userFriendshipResponse.setRequestAccepted(friendRequest.isRequestAccepted());
					userFriendshipResponse.setFriend(friendRequest.isFriend());
					requestedUserResponse.add(userFriendshipResponse);
				}
			}
		}
		return requestedUserResponse;
	}

	@Override
	public AppFriendship confirmedFriendshipRequest(AppFriendshipDto friendshipDto) throws Exception {
		if (friendshipDto.getFriendshipId() == null) {
			throw new Exception("Friend Request not exists");
		}
		AppFriendship friendshipConfirmed = null;
		Optional<AppFriendship> confirmedRequest = friendshipRepo.findById(friendshipDto.getFriendshipId());
		if (confirmedRequest.isPresent()) {
			friendshipConfirmed = confirmedRequest.get();
			friendshipConfirmed.setRequestAccepted(true);
			friendshipConfirmed.setFriend(true);
			friendshipConfirmed.setUpdatedDate(new Date());
			friendshipConfirmed.setUpdatedDate(new Date());
			friendshipRepo.save(friendshipConfirmed);
			return friendshipConfirmed;
		}
		return null;
	}

	
	@Override
	public void deleteFriendRequest(Long friendshipId) throws Exception {
		if (friendshipId == null) {
			throw new Exception("Friend Request not exists");
		}
		AppFriendship friendshipConfirmed = null;
		Optional<AppFriendship> confirmedRequest = friendshipRepo.findById(friendshipId);
		if (confirmedRequest.isPresent()) {
			friendshipRepo.delete(confirmedRequest.get());
		}
	}

	@Override
	public List<AppUserProfileDto> fetchPendingFriendRequest(Long userId) throws Exception {
		boolean user = userRepository.existsById(userId);
		if (!user) {
			throw new Exception("user id not exists in our db");
		}
		Optional<List<AppFriendship>> requestedUser = friendshipRepo.findPendingRequestedUser(userId);
		List<AppUserProfileDto> requestedUserResponse = new ArrayList<AppUserProfileDto>();
		if (requestedUser.isPresent()) {
			for (AppFriendship friendRequest : requestedUser.get()) {
				Optional<AppUser> pendingRequest = userRepository.findById(friendRequest.getRequestToUser());
				if (pendingRequest.isPresent()) {
					AppUser userFromDb = pendingRequest.get();

					AppFriendshipDto userFriendshipResponse = new AppFriendshipDto();
					// This is Friendship table id
					userFriendshipResponse.setFriendshipId(friendRequest.getId());
					// This is actual user who log in user id.
					userFriendshipResponse.setRequestFromUser(userId);
					// This is friend requested user id
					userFriendshipResponse.setRequestToUser(userFromDb.getId());
					userFriendshipResponse.setProfileImage(userFromDb.getUserProfile().getCurrentProfileImage());
					userFriendshipResponse.setUserName(userFromDb.getFirstName()+" "+ userFromDb.getLastName());
					userFriendshipResponse.setCurrentDesignation(userFromDb.getUserProfile().getCurrentDesignation());
					userFriendshipResponse.setCurrentCity(userFromDb.getUserProfile().getCurrentCity());
					userFriendshipResponse.setHomeCity(userFromDb.getUserProfile().getHomeCity());
					requestedUserResponse.add(userFriendshipResponse);
				}
			}
		}
		return requestedUserResponse;
	}

	@Override
	public List<AppUserProfileDto> fetchUsersFriends(Long userId) throws Exception {
		boolean user = userRepository.existsById(userId);
		if (!user) {
			throw new Exception("user id not exists in our db");
		}
		Optional<List<AppFriendship>> friendsByUserId = friendshipRepo.findFriendsByUserId(userId, true);
		List<AppUserProfileDto> requestedUserResponse = new ArrayList<AppUserProfileDto>();
		if (friendsByUserId.isPresent()) {
			for (AppFriendship friend : friendsByUserId.get()) {
				Optional<AppUser> userFriend =null;
				if(friend.getRequestFromUser() != userId) {
					userFriend = userRepository.findById(friend.getRequestFromUser());
				}
				else {
				    userFriend = userRepository.findById(friend.getRequestToUser());
				}
				if (userFriend.isPresent()) {
					AppUser userFromDb = userFriend.get();
					AppFriendshipDto userFriendshipResponse = new AppFriendshipDto();
					// This is Friendship table id
					userFriendshipResponse.setFriendshipId(friend.getId());
					// This is actual user who log in user id.
					userFriendshipResponse.setRequestFromUser(userId);
					// This is friend requested user id
					userFriendshipResponse.setRequestToUser(userFromDb.getId());
					userFriendshipResponse.setProfileImage(userFromDb.getUserProfile().getCurrentProfileImage());
					userFriendshipResponse.setUserName(userFromDb.getFirstName()+" "+ userFromDb.getLastName());
					userFriendshipResponse.setCurrentDesignation(userFromDb.getUserProfile().getCurrentDesignation());
					userFriendshipResponse.setCurrentCity(userFromDb.getUserProfile().getCurrentCity());
					userFriendshipResponse.setHomeCity(userFromDb.getUserProfile().getHomeCity());
					userFriendshipResponse.setFriend(friend.isFriend());
					userFriendshipResponse.setRequestAccepted(friend.isRequestAccepted());
					requestedUserResponse.add(userFriendshipResponse);
				}
			}
		}
		return requestedUserResponse;
	}
}

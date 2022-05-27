package com.bestwisher.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.bestwisher.dto.AppFriendshipDto;
import com.bestwisher.dto.AppUserProfileDto;

public interface IAppUserProfileService {

	void createUserProfile(AppUserProfileDto userProfileFromUi, MultipartFile profileImage, MultipartFile coverImage) throws IOException;

	AppUserProfileDto getUserProfile(Long userId);

	List<AppFriendshipDto> getAllUserProfile(Long userId);

	void updateUserProfileImages(AppUserProfileDto userProfileFromUi, MultipartFile profileImage,
			MultipartFile coverImage) throws IOException;

}

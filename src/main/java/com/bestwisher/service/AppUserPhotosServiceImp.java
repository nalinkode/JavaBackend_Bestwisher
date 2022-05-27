package com.bestwisher.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bestwisher.dto.AppUserPhotosDto;
import com.bestwisher.model.AppUser;
import com.bestwisher.model.AppUserCoverImages;
import com.bestwisher.model.AppUserProfile;
import com.bestwisher.model.AppUserProfileImages;
import com.bestwisher.repository.AppUserCoverPhotoRepository;
import com.bestwisher.repository.AppUserProfilePhotoRepository;
import com.bestwisher.repository.AppUserProfileRepository;
import com.bestwisher.repository.AppUserRepository;

@Service
public class AppUserPhotosServiceImp implements IAppUserPhotosService {

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private AppUserProfileRepository userProfileRepository;

	@Autowired
	private AppUserProfilePhotoRepository userProfilePhotoRepository;

	@Autowired
	private AppUserCoverPhotoRepository userCoverPhotoRepository;

	@Override
	public List<AppUserPhotosDto> getAllUserProfilePhotos(Long userId) throws IOException {
		if (userId == null) {
			throw new IOException("User Id not available");
		}

		Optional<AppUser> user = userRepository.findById(userId);
		AppUserProfile profile = userProfileRepository.getById(user.get().getUserProfile().getId());
		List<AppUserProfileImages> listProfileImages = profile.getUserProfileImages();
		List<AppUserPhotosDto> response = new ArrayList<AppUserPhotosDto>();
		for (AppUserProfileImages p : listProfileImages) {
			AppUserPhotosDto pDto = new AppUserPhotosDto();
			pDto.setPhotoId(p.getId());
			pDto.setProfileId(profile.getId());
			pDto.setProfileImage(p.isProfileImage());
			pDto.setUserId(userId);
			pDto.setImageUrl(p.getProfileImage());
			pDto.setType("profile");
			response.add(pDto);
		}
		return response;
	}

	@Override
	public List<AppUserPhotosDto> getAllUserCoverPhotos(Long userId) throws IOException {
		if (userId == null) {
			throw new IOException("User Id not available");
		}

		Optional<AppUser> user = userRepository.findById(userId);
		AppUserProfile profile = userProfileRepository.getById(user.get().getUserProfile().getId());
		List<AppUserCoverImages> listCoverImages = profile.getUserCoverImages();
		List<AppUserPhotosDto> response = new ArrayList<AppUserPhotosDto>();
		for (AppUserCoverImages c : listCoverImages) {
			AppUserPhotosDto pDto = new AppUserPhotosDto();
			pDto.setPhotoId(c.getId());
			pDto.setProfileId(profile.getId());
			pDto.setProfileImage(c.isCoverImage());
			pDto.setUserId(userId);
			pDto.setImageUrl(c.getCoverImage());
			pDto.setType("cover");
			response.add(pDto);
		}
		return response;
	}

	@Override
	public void updatePhoto(AppUserPhotosDto photoDto) throws IOException {
		if (photoDto.getUserId() == null) {
			throw new IOException("User Id not available");
		}

		Optional<AppUser> user = userRepository.findById(photoDto.getUserId());
		AppUserProfile profile = userProfileRepository.getById(user.get().getUserProfile().getId());
		if (photoDto.getType().equalsIgnoreCase("profile")) {
			profile.setCurrentProfileImage(photoDto.getImageUrl());
			List<AppUserProfileImages> listProfileImages = profile.getUserProfileImages();
			for (AppUserProfileImages p : listProfileImages) {
				if (p.getId() == photoDto.getPhotoId()) {
					p.setProfileImage(true);
				} else {
					p.setProfileImage(false);
				}
			}
		} else if (photoDto.getType().equalsIgnoreCase("cover")) {
			profile.setCurrentCoverImage(photoDto.getImageUrl());
			List<AppUserCoverImages> listCoverImages = profile.getUserCoverImages();
			for (AppUserCoverImages c : listCoverImages) {
				if (c.getId() == photoDto.getPhotoId()) {
					c.setCoverImage(true);
				} else {
					c.setCoverImage(false);
				}
			}

		}
		userProfileRepository.save(profile);
	}

	@Override
	public void deletePhoto(String type, Long profileId, Long photoId) throws IOException {
		if (profileId == null) {
			throw new IOException("profile Id not available");
		}
		if (photoId == null) {
			throw new IOException("photo Id not available");
		}

		if (type.equalsIgnoreCase("profile")) {
			AppUserProfileImages pImg = userProfilePhotoRepository.findAllById(photoId, profileId);
			boolean result = Files.deleteIfExists(Paths.get(pImg.getFileName()));
			if (result) {
				System.out.println("File is deleted!");
				userProfilePhotoRepository.delete(pImg);
				if (pImg.isProfileImage()) {
					String defaultProfileImage = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/image/profile/file/defaultProfile.jpg").toUriString();
					AppUserProfile profile = userProfileRepository.getById(profileId);
					profile.setCurrentProfileImage(defaultProfileImage);
					userProfileRepository.save(profile);
				}
			} else {
				System.out.println("Sorry, unable to delete the file.");
			}
		} 
		else if (type.equalsIgnoreCase("cover")) {
			AppUserCoverImages cImg = userCoverPhotoRepository.findAllById(photoId, profileId);
			boolean result = Files.deleteIfExists(Paths.get(cImg.getFileName()));
			if (result) {
				System.out.println("File is deleted!");
				userCoverPhotoRepository.delete(cImg);
				if (cImg.isCoverImage()) {
					String defaultCoverImage = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/image/profile/file/defaultCoverImage.jpg").toUriString();
					AppUserProfile profile = userProfileRepository.getById(profileId);
					profile.setCurrentCoverImage(defaultCoverImage);
					userProfileRepository.save(profile);
				}
			} else {
				System.out.println("Sorry, unable to delete the file.");
			}
		}
	}
}

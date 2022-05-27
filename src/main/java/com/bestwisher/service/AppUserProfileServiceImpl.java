package com.bestwisher.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bestwisher.dto.AppFriendshipDto;
import com.bestwisher.dto.AppUserProfileAcademicDto;
import com.bestwisher.dto.AppUserProfileDto;
import com.bestwisher.dto.AppUserProfileWorkDto;
import com.bestwisher.model.AppFriendship;
import com.bestwisher.model.AppUser;
import com.bestwisher.model.AppUserCoverImages;
import com.bestwisher.model.AppUserProfile;
import com.bestwisher.model.AppUserProfileAcademic;
import com.bestwisher.model.AppUserProfileImages;
import com.bestwisher.model.AppUserProfileWork;
import com.bestwisher.repository.AppFriendshipRepository;
import com.bestwisher.repository.AppUserProfileRepository;
import com.bestwisher.repository.AppUserRepository;

@Service
public class AppUserProfileServiceImpl implements IAppUserProfileService {

	@Autowired
	private AppUserProfileRepository userProfileRepository;

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private AppFriendshipRepository friendshipRepo;

	String directoryPath = "user_profile";
	private final Path root = Paths.get(directoryPath);

	public void checkIsDirectoryPresent(Path root) {
		try {
			if (!Files.isDirectory(root)) {
				Files.createDirectory(root);
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public void createUserProfile(AppUserProfileDto userProfileFromUi, MultipartFile profileImage,
			MultipartFile coverImage) throws IOException {

		if (userProfileFromUi.getUserId() == null) {
			throw new IOException("User Id not available");
		}

		try {
			Optional<AppUser> user = userRepository.findById(userProfileFromUi.getUserId());
			AppUserProfile profile = userProfileRepository.getById(user.get().getUserProfile().getId());

			if (profile.getCurrentProfileImage() == null) {
				
				String defaultProfileImage = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/image/profile/file/defaultProfile.jpg").toUriString();			
				
				profile.setCurrentProfileImage(defaultProfileImage);
			}
			if (profile.getCurrentCoverImage() == null) {
				String defaultCoverImage = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/image/profile/file/defaultCoverImage.jpg").toUriString();
			
				profile.setCurrentCoverImage(defaultCoverImage);
			}

			profile.setUpdatedDate(new Date());
			profile.setHomeCity(userProfileFromUi.getHomeCity());
			profile.setCurrentCity(userProfileFromUi.getCurrentCity());

			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			String currentYear = String.valueOf(calendar.get(Calendar.YEAR));

			if (userProfileFromUi.getWorkPlace() != null) {
				Set<AppUserProfileWorkDto> currentDesignation = userProfileFromUi.getWorkPlace();
				for (AppUserProfileWorkDto workProfile : currentDesignation) {
					if (workProfile.getToDate().equalsIgnoreCase(currentYear)) {
						profile.setCurrentDesignation(workProfile.getDesignation());
					}
				}
			}

			if (userProfileFromUi.getAcademic() != null || userProfileFromUi.getWorkPlace() != null) {
				Set<AppUserProfileAcademic> setEntityAcademic = new HashSet<AppUserProfileAcademic>();
				for (AppUserProfileAcademicDto academic : userProfileFromUi.getAcademic()) {
					AppUserProfileAcademic academicEntity = new AppUserProfileAcademic();
					academicEntity.setSchoolName(academic.getSchoolName());
					academicEntity.setFromDate(academic.getFromDate());
					academicEntity.setToDate(academic.getToDate());
					academicEntity.setUserProfile(profile);
					academicEntity.setHide(false);
					setEntityAcademic.add(academicEntity);
				}

				Set<AppUserProfileWork> setEntityProfileWork = new HashSet<AppUserProfileWork>();

				for (AppUserProfileWorkDto work : userProfileFromUi.getWorkPlace()) {
					AppUserProfileWork workEntity = new AppUserProfileWork();
					workEntity.setWorkPlaceName(work.getWorkPlaceName());
					workEntity.setFromDate(work.getFromDate());
					workEntity.setToDate(work.getToDate());
					workEntity.setUserProfile(profile);
					workEntity.setHide(false);
					workEntity.setDesignation(work.getDesignation());
					setEntityProfileWork.add(workEntity);
				}

				profile.setAcademic(setEntityAcademic);
				profile.setWorkPlace(setEntityProfileWork);
			}

			userProfileRepository.save(profile);

		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}

	}

	@Override
	public AppUserProfileDto getUserProfile(Long userId) {
		Optional<AppUser> user = userRepository.findById(userId);
		AppUserProfileDto profileResponse = null;
		if (user.isPresent()) {
			Optional<AppUserProfile> optionalProfile = userProfileRepository.findByUserId(userId);
			if (optionalProfile.isPresent()) {
				AppUserProfile profile = optionalProfile.get();
				profileResponse = this.mapEntityToDto(profile);
			}
		} else {
			throw new RuntimeException("User id not exists ");
		}
		return profileResponse;
	}

	@Override
	public List<AppFriendshipDto> getAllUserProfile(Long userId) {
		List<AppUser> users = userRepository.fetchAllExceptUserId(userId);
		List<AppFriendship> friends = friendshipRepo.findAll();
		List<AppFriendshipDto> responseList = new ArrayList<AppFriendshipDto>();
		List<Long> listUserId = new ArrayList<Long>();
		
		// Filters
		for (AppFriendship friend : friends) {
			if (userId == friend.getRequestFromUser()) {
				if (friend.getRequestToUser() != userId) {
					listUserId.add(friend.getRequestToUser());
				}
			}
			if (userId == friend.getRequestToUser()) {
				if (friend.getRequestFromUser() != userId) {
					listUserId.add(friend.getRequestFromUser());
				}
			}
			
			if(userId == friend.getRequestFromUser() && friend.isFriend()) {
				listUserId.add(friend.getRequestFromUser());
			}
			
			if(userId == friend.getRequestToUser() && friend.isFriend()) {
				listUserId.add(friend.getRequestToUser());
			}
		}

		List<AppUser> removeUserList = new ArrayList<AppUser>();
		for (Long id : listUserId) {
			for (AppUser u : users) {
				if (u.getId().equals(id)) {
					removeUserList.add(u);
				}
			}
		}
		
		users.removeAll(removeUserList);
		
		// ==============

		if (users.size() > 0) {
			users.stream().forEach((u) -> responseList.add(this.mapEntityToDto(u.getUserProfile())));
		}
		
		return responseList;
	}

	public AppFriendshipDto mapEntityToDto(AppUserProfile profile) {
		AppFriendshipDto profileResponse = new AppFriendshipDto();
		if (profile != null) {
			Set<AppUserProfileWorkDto> setEntityProfileWork = new HashSet<AppUserProfileWorkDto>();
			for (AppUserProfileWork work : profile.getWorkPlace()) {
				AppUserProfileWorkDto workDto = new AppUserProfileWorkDto();
				workDto.setId(work.getId());
				workDto.setWorkPlaceName(work.getWorkPlaceName());
				workDto.setFromDate(work.getFromDate());
				workDto.setToDate(work.getToDate());
				workDto.setHide(work.isHide());
				workDto.setDesignation(work.getDesignation());
				setEntityProfileWork.add(workDto);
			}

			profileResponse.setWorkPlace(setEntityProfileWork);

			Set<AppUserProfileAcademicDto> setDtoProfileAcademic = new HashSet<AppUserProfileAcademicDto>();
			for (AppUserProfileAcademic academic : profile.getAcademic()) {
				AppUserProfileAcademicDto academicDto = new AppUserProfileAcademicDto();
				academicDto.setId(academic.getId());
				academicDto.setSchoolName(academic.getSchoolName());
				academicDto.setFromDate(academic.getFromDate());
				academicDto.setToDate(academic.getToDate());
				academicDto.setHide(academic.isHide());
				setDtoProfileAcademic.add(academicDto);
			}
			
			profileResponse.setAcademic(setDtoProfileAcademic);
			profileResponse.setCurrentCity(profile.getCurrentCity());
			profileResponse.setCurrentDesignation(profile.getCurrentDesignation());
			profileResponse.setHomeCity(profile.getHomeCity());
			profileResponse.setId(profile.getId());
			profileResponse.setUserId(profile.getUser().getId());
			profileResponse.setUserName(profile.getUser().getFirstName() + " " + profile.getUser().getLastName());
			profileResponse.setProfileImage(profile.getCurrentProfileImage());
			profileResponse.setCoverImage(profile.getCurrentCoverImage());
		}
		return profileResponse;
	}

	@Override
	public void updateUserProfileImages(AppUserProfileDto userProfileFromUi, MultipartFile profileImage,
			MultipartFile coverImage) throws IOException {
		if (userProfileFromUi.getUserId() == null) {
			throw new IOException("User Id not available");
		}

		try {
			Optional<AppUser> user = userRepository.findById(userProfileFromUi.getUserId());
			AppUserProfile profile = userProfileRepository.getById(user.get().getUserProfile().getId());
			Map<String, MultipartFile> mapProfileImage = new HashMap<String, MultipartFile>();
			mapProfileImage.put("profileImage", profileImage);
			mapProfileImage.put("coverImage", coverImage);

			this.checkIsDirectoryPresent(root);

			for (String key : mapProfileImage.keySet()) {

				MultipartFile file = mapProfileImage.get(key);

				if (file != null) {
					LocalDate now = LocalDate.now();
					UUID uniqueKey = UUID.randomUUID();

					String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
					if (originalFileName.contains("..")) {
						throw new Exception("Sorry! Filename contains invalid path sequence " + originalFileName);
					}
					String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
					String newFileName = userProfileFromUi.getUserId() + now.toString() + uniqueKey.toString()
							+ fileExtension;
					Files.copy(file.getInputStream(), root.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);

					// Create a URL
					String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/image/profile/file/").path(newFileName).toUriString();
					
					String absoluteFilePath = Paths.get(directoryPath).toAbsolutePath().resolve(newFileName).toString();

					if (key.equalsIgnoreCase("profileImage") && imageUrl != null) {
						List<AppUserProfileImages> pImages = profile.getUserProfileImages();
						for (AppUserProfileImages pIm : pImages) {
							pIm.setProfileImage(false);
						}
						
						AppUserProfileImages profileImageFromUI = new AppUserProfileImages();
						profileImageFromUI.setProfileImage(true);
						profileImageFromUI.setProfileImage(imageUrl);
						profileImageFromUI.setUserProfile(profile);
						profileImageFromUI.setDefault(false);
						profileImageFromUI.setFileName(absoluteFilePath);
						
						pImages.add(profileImageFromUI);
						profile.setUserProfileImages(pImages);
						
						profile.setCurrentProfileImage(imageUrl);
					}

					if (key.equalsIgnoreCase("coverImage") && imageUrl != null) {
						List<AppUserCoverImages> cImages = profile.getUserCoverImages();
						for (AppUserCoverImages cIm : cImages) {
							cIm.setCoverImage(false);
						}
						AppUserCoverImages coverImageFromUI = new AppUserCoverImages();
						coverImageFromUI.setCoverImage(true);
						coverImageFromUI.setCoverImage(imageUrl);
						coverImageFromUI.setUserProfile(profile);
						coverImageFromUI.setDefault(false);
						coverImageFromUI.setFileName(absoluteFilePath);
						cImages.add(coverImageFromUI);
						profile.setUserCoverImages(cImages);
						profile.setCurrentCoverImage(imageUrl);
					}
				}
			}
			profile.setUpdatedDate(new Date());
			userProfileRepository.save(profile);

		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}

	}
}
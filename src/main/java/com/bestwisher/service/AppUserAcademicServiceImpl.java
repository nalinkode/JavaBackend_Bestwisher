package com.bestwisher.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestwisher.dto.AppUserProfileAcademicDto;
import com.bestwisher.model.AppUser;
import com.bestwisher.model.AppUserProfile;
import com.bestwisher.model.AppUserProfileAcademic;
import com.bestwisher.repository.AppUserProfileAcademicRepository;
import com.bestwisher.repository.AppUserProfileRepository;
import com.bestwisher.repository.AppUserRepository;

@Service
public class AppUserAcademicServiceImpl implements IAppUserAcademicService {

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private AppUserProfileRepository userProfileRepository;

	@Autowired
	private AppUserProfileAcademicRepository academicRepository;

	@Override
	public void createAcademic(AppUserProfileAcademicDto academicDto) throws IOException {
		if (academicDto.getUserId() == null) {
			throw new IOException("User Id not available");
		}

		Optional<AppUser> user = userRepository.findById(academicDto.getUserId());
		AppUserProfile profile = userProfileRepository.getById(user.get().getUserProfile().getId());

		AppUserProfileAcademic academicEntity = new AppUserProfileAcademic();
		academicEntity.setSchoolName(academicDto.getSchoolName());
		academicEntity.setFromDate(academicDto.getFromDate());
		academicEntity.setToDate(academicDto.getToDate());
		academicEntity.setUserProfile(profile);
		academicEntity.setHide(false);
		academicRepository.save(academicEntity);
	}

	@Override
	public void updateAcademic(AppUserProfileAcademicDto academicDto) throws IOException {
		if (academicDto.getId() == null) {
			throw new IOException("Academic Id not available");
		}

		Optional<AppUserProfileAcademic> academic = academicRepository.findById(academicDto.getId());
		if (academic.isPresent()) {
			AppUserProfileAcademic academicEntity = academic.get();
			academicEntity.setSchoolName(academicDto.getSchoolName());
			academicEntity.setFromDate(academicDto.getFromDate());
			academicEntity.setToDate(academicDto.getToDate());
			academicEntity.setHide(academicDto.isHide());
			academicRepository.save(academicEntity);
		}

	}

	@Override
	public void deleteAcademic(Long academicId) throws IOException {
		if (academicId == null) {
			throw new IOException("Academic Id not available");
		}
		Optional<AppUserProfileAcademic> academic = academicRepository.findById(academicId);
		if (academic.isPresent()) {
			academicRepository.delete(academic.get());
		}

	}

	@Override
	public List<AppUserProfileAcademicDto> getAllAcademic(Long userId) throws IOException {
		if (userId == null) {
			throw new IOException("User Id not available");
		}

		List<AppUserProfileAcademicDto> listResponse = new ArrayList<AppUserProfileAcademicDto>();
		Optional<AppUser> user = userRepository.findById(userId);
		AppUserProfile profile = userProfileRepository.getById(user.get().getUserProfile().getId());
		for (AppUserProfileAcademic academic : profile.getAcademic()) {
			AppUserProfileAcademicDto academicEntity = new AppUserProfileAcademicDto();
			academicEntity.setId(academic.getId());
			academicEntity.setSchoolName(academic.getSchoolName());
			academicEntity.setFromDate(academic.getFromDate());
			academicEntity.setToDate(academic.getToDate());
			academicEntity.setHide(false);
			listResponse.add(academicEntity);
		}
		return listResponse;
	}

}

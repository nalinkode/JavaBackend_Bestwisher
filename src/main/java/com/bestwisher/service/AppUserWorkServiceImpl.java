package com.bestwisher.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bestwisher.dto.AppUserProfileWorkDto;
import com.bestwisher.model.AppUser;
import com.bestwisher.model.AppUserProfile;
import com.bestwisher.model.AppUserProfileWork;
import com.bestwisher.repository.AppUserProfileRepository;
import com.bestwisher.repository.AppUserProfileWorkRepository;
import com.bestwisher.repository.AppUserRepository;

@Service
public class AppUserWorkServiceImpl implements IAppUserWorkService {

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private AppUserProfileRepository userProfileRepository;

	@Autowired
	private AppUserProfileWorkRepository workRepository;

	@Override
	public void createWork(AppUserProfileWorkDto workDto) throws IOException {
		if (workDto.getUserId() == null) {
			throw new IOException("User Id not available");
		}

		Optional<AppUser> user = userRepository.findById(workDto.getUserId());
		AppUserProfile profile = userProfileRepository.getById(user.get().getUserProfile().getId());

		if (workDto.isCurrentDesignation()) {
			profile.setCurrentDesignation(workDto.getDesignation());
		}

		Set<AppUserProfileWork> setEntityProfileWork = new HashSet<AppUserProfileWork>();

		AppUserProfileWork workEntity = new AppUserProfileWork();
		workEntity.setWorkPlaceName(workDto.getWorkPlaceName());
		workEntity.setFromDate(workDto.getFromDate());
		workEntity.setToDate(workDto.getToDate());
		workEntity.setUserProfile(profile);
		workEntity.setHide(workDto.isHide());
		workEntity.setDesignation(workDto.getDesignation());
		setEntityProfileWork.add(workEntity);

		profile.setWorkPlace(setEntityProfileWork);

		userProfileRepository.save(profile);
	}

	@Override
	public void updateWork(AppUserProfileWorkDto workDto) throws IOException {
		if (workDto.getId() == null) {
			throw new IOException("Work Id not available");
		}

		Optional<AppUserProfileWork> workOptional = workRepository.findById(workDto.getId());
		if (workOptional.isPresent()) {
			AppUserProfileWork workEnt = workOptional.get();
			AppUserProfile prof = workEnt.getUserProfile();
			workEnt.setWorkPlaceName(workDto.getWorkPlaceName());
			workEnt.setFromDate(workDto.getFromDate());
			workEnt.setToDate(workDto.getToDate());
			workEnt.setHide(workDto.isHide());
			workEnt.setDesignation(workDto.getDesignation());
			if (workDto.isCurrentDesignation()) {
				prof.setCurrentDesignation(workDto.getDesignation());
			}
			workEnt.setUserProfile(prof);

			workRepository.save(workEnt);

		}

	}

	@Override
	public void deleteWork(Long workId) throws IOException {
		if (workId == null) {
			throw new IOException("Work Id not available");
		}
		Optional<AppUserProfileWork> workOptional = workRepository.findById(workId);
		if (workOptional.isPresent()) {
			workRepository.delete(workOptional.get());
		}
	}

	@Override
	public List<AppUserProfileWorkDto> getAllWork(Long userId) throws IOException {
		if (userId == null) {
			throw new IOException("User Id not available");
		}

		Optional<AppUser> user = userRepository.findById(userId);
		AppUserProfile profile = userProfileRepository.getById(user.get().getUserProfile().getId());
		List<AppUserProfileWorkDto> responseList = new ArrayList<AppUserProfileWorkDto>();
		for (AppUserProfileWork work : profile.getWorkPlace()) {
			AppUserProfileWorkDto workDto = new AppUserProfileWorkDto();
			workDto.setId(work.getId());
			workDto.setWorkPlaceName(work.getWorkPlaceName());
			workDto.setFromDate(work.getFromDate());
			workDto.setToDate(work.getToDate());
			workDto.setHide(work.isHide());
			workDto.setDesignation(work.getDesignation());
			responseList.add(workDto);
		}
		return responseList;
	}

}

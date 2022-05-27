package com.bestwisher.service;

import java.io.IOException;
import java.util.List;

import com.bestwisher.dto.AppUserProfileAcademicDto;

public interface IAppUserAcademicService {

	void createAcademic(AppUserProfileAcademicDto academicDto) throws IOException;

	void updateAcademic(AppUserProfileAcademicDto academicDto) throws IOException;

	void deleteAcademic(Long academicId) throws IOException;

	List<AppUserProfileAcademicDto> getAllAcademic(Long userId) throws IOException;

}

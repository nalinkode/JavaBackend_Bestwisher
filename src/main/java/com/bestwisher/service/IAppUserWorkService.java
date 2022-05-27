package com.bestwisher.service;

import java.io.IOException;
import java.util.List;

import com.bestwisher.dto.AppUserProfileWorkDto;

public interface IAppUserWorkService {

	void createWork(AppUserProfileWorkDto workDto) throws IOException;

	void deleteWork(Long workId) throws IOException;

	void updateWork(AppUserProfileWorkDto workDto) throws IOException;

	List<AppUserProfileWorkDto> getAllWork(Long userId) throws IOException;


}

package com.bestwisher.service;

import java.io.IOException;
import java.util.List;

import com.bestwisher.dto.AppUserPhotosDto;

public interface IAppUserPhotosService {

	List<AppUserPhotosDto> getAllUserProfilePhotos(Long userId) throws IOException;

	List<AppUserPhotosDto> getAllUserCoverPhotos(Long userId) throws IOException;

	void updatePhoto(AppUserPhotosDto photoDto) throws IOException;

	void deletePhoto(String type, Long profileId, Long photoId) throws IOException;
}

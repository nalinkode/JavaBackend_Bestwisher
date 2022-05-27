package com.bestwisher.service;

import org.springframework.core.io.Resource;

public interface IAppImageService {

	Resource getImageByFileName(String fileName, String directoryPath);

}

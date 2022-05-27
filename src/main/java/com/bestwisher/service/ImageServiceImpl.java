package com.bestwisher.service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements IAppImageService {

	@Override
	public Resource getImageByFileName(String fileName, String directoryPath) {
		Path path = Paths.get(directoryPath).toAbsolutePath().resolve(fileName);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		if (resource.exists() && resource.isReadable()) {
			return resource;
		}
		return null;
	}

}

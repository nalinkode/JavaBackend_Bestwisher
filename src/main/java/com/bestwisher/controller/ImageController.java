package com.bestwisher.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bestwisher.service.IAppImageService;

@RestController
@RequestMapping("/api/image")
public class ImageController {

	@Autowired
	private IAppImageService imageService;

	// Hit API to get images on UI
	@GetMapping(value = "/profile/file/{fileName}")
	public ResponseEntity<Resource> fetchProfileByFileName(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = null;
		String mimeType = null;
		String directoryPath = "user_profile";
		try {
			resource = imageService.getImageByFileName(fileName, directoryPath);
			if (resource.exists()) {
				mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
				return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
						.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename())
						.body(resource);
			}
		} catch (Exception e) {

		}
		return null;
	}

	@GetMapping(value = "/post/file/{fileName}")
	public ResponseEntity<Resource> fetchPostByFileName(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = null;
		String mimeType = null;
		String directoryPath = "uploads";
		try {
			resource = imageService.getImageByFileName(fileName, directoryPath);
			mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {

		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename()).body(resource);
	}

}

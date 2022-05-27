package com.bestwisher.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bestwisher.dto.AppPostDto;
import com.bestwisher.dto.AppPostLikeDto;
import com.bestwisher.model.AppPostLike;
import com.bestwisher.model.AppUser;
import com.bestwisher.model.AppUserPost;
import com.bestwisher.repository.AppPostLikeRepository;
import com.bestwisher.repository.AppPostRepository;
import com.bestwisher.repository.AppUserRepository;

@Service
public class AppPostServiceImpl implements IAppPostService {

	String directoryPath = "uploads";
	private final Path root = Paths.get(directoryPath);

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private AppPostRepository postRepository;

	@Autowired
	private AppPostLikeRepository postLikeRepository;
	
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
	public List<AppPostDto> getPostByUserId(Long userId) {
		List<AppUserPost> post = postRepository.findAllByUserId(userId);
		List<AppPostDto> listPostDto = new ArrayList<AppPostDto>();
		if (!post.isEmpty()) {
			post.stream().forEach((c) -> listPostDto.add(this.mapEntityToDto(c)));
		}
		return listPostDto;
	}

	@Override
	public List<AppPostDto> getAllPost() {
		List<AppUserPost> appUserPost = postRepository.findAll();
		List<AppPostDto> listPostDto = new ArrayList<AppPostDto>();
		appUserPost.stream().forEach((c) -> {
			listPostDto.add(this.mapEntityToDto(c));
		});
		return listPostDto;
	}

	@Override
	public void likeForPost(AppPostLikeDto postLikeDto) throws Exception {
		Optional<AppUserPost> post = postRepository.findById(postLikeDto.getPostId());
		Optional<AppUser> user = userRepository.findById(postLikeDto.getUserId());

		if (!post.isPresent()) {
			throw new Exception("Post with id not exists");
		}

		if (!user.isPresent()) {
			throw new Exception("User with id not exists");
		}

		Optional<AppPostLike> postLike = postLikeRepository.findByUserIdAndPostId(postLikeDto.getUserId(),
				postLikeDto.getPostId());
		if (!postLike.isPresent()) {
			AppPostLike doPostLike = new AppPostLike();
			doPostLike.setLike(true);
			doPostLike.setUserId(postLikeDto.getUserId());
			doPostLike.setPostId(postLikeDto.getPostId());
			doPostLike.setPostLikedDate(new Date());
			postLikeRepository.save(doPostLike);
		} else {
			throw new Exception("Post liked already");
		}
	}

	@Override
	public List<AppPostLike> getAllLikeByPostId(Long postId) throws Exception {
		Optional<AppUserPost> post = postRepository.findById(postId);
		if (!post.isPresent()) {
			throw new Exception("Post with id not exists");
		}
		List<AppPostLike> likeCount = postLikeRepository.findLikeByPostId(postId);
		return likeCount;
	}

	@Override
	public void createPost(AppPostDto postFromUI, MultipartFile file) throws IOException {
		if (postFromUI.getUserId() == null) {
			throw new IOException("User Id not available");
		}
		try {
			this.checkIsDirectoryPresent(root);
			LocalDate now = LocalDate.now();
			UUID uniqueKey = UUID.randomUUID();
			AppUserPost post = new AppUserPost();

			if (file != null) {
				String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
				if (originalFileName.contains("..")) {
					throw new Exception("Sorry! Filename contains invalid path sequence " + originalFileName);
				}
				String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				String newFileName = postFromUI.getUserId() + now.toString() + uniqueKey.toString() + fileExtension;
				Files.copy(file.getInputStream(), root.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);

				// Create a URL
				String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/image/post/file/")
						.path(newFileName).toUriString();
				post.setImageUrl(imageUrl);
			}
			// Savings post
			if (postFromUI.getText() != null) {
				post.setText(postFromUI.getText());
			}
			if (postFromUI.getFeeling() != null) {
				post.setFeeling(postFromUI.getFeeling());
			}
			if (postFromUI.getLocation() != null) {
				post.setLocation(postFromUI.getLocation());

			}
			if (postFromUI.getTag() != null) {
				post.setTag(postFromUI.getTag());
			}
			post.setCreatedDate(new Date());
			post.setUpdatedDate(new Date());
			Optional<AppUser> user = userRepository.findById(postFromUI.getUserId());
			post.setUser(user.get());
			if (postFromUI.getText() != null || !post.getImageUrl().isEmpty()) {
				postRepository.save(post);
			}
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}

	}

	private AppPostDto mapEntityToDto(AppUserPost eachPost) {
		AppPostDto postDto = new AppPostDto();
		postDto.setId(eachPost.getId());
		postDto.setImageUrl(eachPost.getImageUrl());
		postDto.setText(eachPost.getText());
		postDto.setCreatedDate(eachPost.getCreatedDate());
		postDto.setUpdatedDate(eachPost.getUpdatedDate());
		postDto.setUserName(eachPost.getUser().getFirstName() + " " + eachPost.getUser().getLastName());
		postDto.setFeeling(eachPost.getFeeling());
		postDto.setLocation(eachPost.getLocation());
		postDto.setTag(eachPost.getTag());
		postDto.setUserId(eachPost.getUser().getId());

		if (eachPost.getUser() != null && eachPost.getUser().getUserProfile() != null) {
			String profileUserImage = eachPost.getUser().getUserProfile().getCurrentProfileImage();
			postDto.setUserProfileImg(profileUserImage);
		}
		return postDto;
	}
}

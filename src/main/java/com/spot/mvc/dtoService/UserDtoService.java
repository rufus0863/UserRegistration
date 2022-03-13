package com.spot.mvc.dtoService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spot.mvc.dto.UserDTO;
import com.spot.mvc.repository.UserJpaRepository;

@Service
public class UserDtoService {

	public static final Logger logger = LoggerFactory.getLogger(UserDtoService.class);

	private UserJpaRepository userJpaRepository;

	@Autowired
	public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	public List<UserDTO> findAllUsers() {
		logger.info("Fetching all users");

		List<UserDTO> users = userJpaRepository.findAll();
		if (users.isEmpty()) {
			return null;
		}
		return users;
	}

	public UserDTO findById(Long id) {
		logger.info("Fetching User with id {}", id);

		UserDTO user = userJpaRepository.findById(id);
		if (user == null) {
			return null;
		}
		return user;
	}

	public UserDTO findUserByEmail(String email) {
		UserDTO user = userJpaRepository.searchUserByEmail(email);
		if (user == null) {
			return null;
		}
		return user;
	}

	public UserDTO findByName(String name) {
		UserDTO user = userJpaRepository.findByName(name);
		if (user == null) {
			return null;
		} else {
			return user;
		}
	}

	public UserDTO addUser(UserDTO user) {
		logger.info("Creating User : {}", user);
		UserDTO currentUser = userJpaRepository.searchUserByEmail(user.getEmail());
		if (currentUser != null) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return null;
		} else {
			userJpaRepository.save(user);
			return user;
		}
	}

	public UserDTO updateUser(UserDTO user) {
		logger.info("Updating User with id {}", user.getId());

		UserDTO currentUser = userJpaRepository.findById(user.getId());
		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", user.getId());
			return null;
		}
		currentUser.setName(user.getName());
		currentUser.setAddress(user.getAddress());
		currentUser.setEmail(user.getEmail());
		currentUser.setPiva(user.getPiva());
		currentUser.setCfisc(user.getCfisc());
		currentUser.setTel(user.getTel());

		userJpaRepository.saveAndFlush(currentUser);
		return user;
	}

}

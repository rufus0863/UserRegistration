package com.spot.mvc.rest.user;

import com.spot.mvc.dto.UserDTO;
import com.spot.mvc.dtoService.UserDtoService;
import com.spot.mvc.exception.CustomErrorType;
import com.spot.mvc.repository.UserJpaRepository;
//
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RegistrationRestController {

	public static final Logger logger = LoggerFactory.getLogger(RegistrationRestController.class);

	private UserJpaRepository userJpaRepository;
	private UserDtoService dtoService;

	@Autowired
	public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	@Autowired
	public void setUserDtoService(UserDtoService dtoService) {
		this.dtoService = dtoService;
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> listAllUsers() {
		List<UserDTO> users = dtoService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Long id) {
		UserDTO user = dtoService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity<UserDTO>((UserDTO) new CustomErrorType("User with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUserByName(@RequestBody final UserDTO user) {
		UserDTO currentUser = dtoService.findUserByEmail(user.getEmail());
		if (currentUser == null || !(user.getPassword().equals(currentUser.getPassword()))) {
			logger.error("User with id {} not found.", user);
			return new ResponseEntity<UserDTO>(new CustomErrorType("User with id " + user + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@RequestBody final UserDTO user) {
		UserDTO currentUser = dtoService.addUser(user);
		if (currentUser == null) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity<UserDTO>(
					new CustomErrorType(
							"Unable to create new user. A User with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		return new ResponseEntity<UserDTO>(currentUser, HttpStatus.CREATED);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
		UserDTO currentUser = dtoService.updateUser(user);
		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", user.getId());
			return new ResponseEntity<UserDTO>(
					new CustomErrorType("Unable to upate. User with id " + user.getId() + " not found."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Long id) {
		UserDTO user = userJpaRepository.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity<UserDTO>(
					new CustomErrorType("Unable to delete. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		userJpaRepository.delete(id);
		return new ResponseEntity<UserDTO>(new CustomErrorType("Deleted User with id " + id + "."),
				HttpStatus.NO_CONTENT);
	}

}

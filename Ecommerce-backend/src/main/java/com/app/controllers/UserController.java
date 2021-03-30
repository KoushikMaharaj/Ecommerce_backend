package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.UserDTO;
import com.app.pojos.User;
import com.app.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private IUserService userService;

	public UserController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	@PostMapping("/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
		try {
			return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> addCustomer(@RequestBody User user) {
		try {
			return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public UserDTO loginCustomer(@RequestBody User user) {
		return userService.loginUser(user.getUserEmail(), user.getUserPassword());
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserDTO user) {
		try {
			return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/address")
	public ResponseEntity<?> updateUserAddress(@RequestBody UserDTO user) {
		try {
			return new ResponseEntity<>(userService.updateUserAddress(user), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/password")
	public ResponseEntity<?> updateUserPassword(@RequestBody UserDTO user) {
		try {
			return new ResponseEntity<>(userService.updateUserPassword(user), HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Role;
import com.app.pojos.User;
import com.app.service.IUserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
	@Autowired
	private IUserService userService;

	public AdminController() {
		System.out.println("in ctor of " + getClass().getName());
	}

	@GetMapping
	public List<User> getAllAdmins() {
		return userService.getAllAdmins(Role.valueOf("Admin".toUpperCase()));
	}

	@DeleteMapping("/{id}")
	public void deleteAdmin(@PathVariable Integer id) {
		System.out.println("in deleteAdmin " + id);
		userService.deleteAdmin(id);
	}
}

package com.app.service;

import java.util.List;

import com.app.dto.UserDTO;
import com.app.pojos.Role;
import com.app.pojos.User;

public interface IUserService {
	UserDTO addUser(User cust);

	List<User> getAllAdmins(Role role);

	UserDTO loginUser(String email, String password);

	User getUserById(Integer id);

	void deleteAdmin(Integer id);
}

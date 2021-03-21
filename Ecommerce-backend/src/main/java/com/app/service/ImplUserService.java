package com.app.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserRepository;
import com.app.dto.UserDTO;
import com.app.pojos.Role;
import com.app.pojos.User;

@Service
@Transactional
public class ImplUserService implements IUserService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDTO addUser(User user) {
		User persistentCust = userRepo.save(user);
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(persistentCust, dto);
		System.out.println("in addCustomer " + dto);
		return dto;
	}

	@Override
	public List<User> getAllAdmins(Role role) {
		return userRepo.findByRole(role);
	}

	@Override
	public UserDTO loginUser(String email, String password) {
		User validUser = userRepo.findByUserEmailAndUserPassword(email, password)
				.orElseThrow(() -> new RuntimeException("User not Found"));
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(validUser, dto);
		System.out.println("in loginUser " + dto);
		return dto;
	}

	@Override
	public User getUserById(Integer id) {
		return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not Found"));
	}

	@Override
	public void deleteAdmin(Integer id) {
		userRepo.deleteById(id);
		
	}

}

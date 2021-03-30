package com.app.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.customException.ResourceNotFoundException;
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
		BeanUtils.copyProperties(persistentCust, dto, "userPassword");
		return dto;
	}

	@Override
	public List<User> getAllAdmins(Role role) {
		return userRepo.findByRole(role);
	}

	@Override
	public UserDTO loginUser(String email, String password) {
		User validUser = userRepo.findByUserEmailAndUserPassword(email, password)
				.orElseThrow(() -> new ResourceNotFoundException("User not Found"));
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(validUser, dto, "userPassword");
		return dto;
	}

	@Override
	public User getUserById(Integer id) {
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not Found"));
	}

	@Override
	public void deleteAdmin(Integer id) {
		userRepo.deleteById(id);

	}

	@Override
	public UserDTO updateUser(UserDTO cust) {
		User customer = userRepo.findById(cust.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not Found"));
		customer.setUserName(cust.getUserName());
		customer.setUserEmail(cust.getUserEmail());
		customer.setUserContact(cust.getUserContact());
		User persistentCust = userRepo.save(customer);
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(persistentCust, dto, "userPassword");
		return dto;
	}

	@Override
	public UserDTO updateUserAddress(UserDTO cust) {
		User customer = userRepo.findById(cust.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not Found"));
		customer.setUserAddr(cust.getUserAddr());
		User persistentCust = userRepo.save(customer);
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(persistentCust, dto, "userPassword");
		return dto;
	}

	@Override
	public UserDTO updateUserPassword(UserDTO cust) {
		User customer = userRepo.findById(cust.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User not Found"));
		customer.setUserPassword(cust.getUserPassword());
		User persistentCust = userRepo.save(customer);
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(persistentCust, dto, "userPassword");
		return dto;
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		User customer = userRepo.findByUserEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		UserDTO dto = new UserDTO();
		BeanUtils.copyProperties(customer, dto, "userPassword");
		return dto;
	}

}

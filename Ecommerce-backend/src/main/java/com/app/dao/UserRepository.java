package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Role;
import com.app.pojos.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByRole(Role role);

	Optional<User> findByUserEmailAndUserPassword(String email, String password);
}

package com.intranet.secure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intranet.secure.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findUserByUsername(String username);
	User findUserByEmail(String email);
}

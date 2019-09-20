package com.example.usersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.usersapi.model.User;

public interface  UsersRepository extends JpaRepository<User, Long> {
	
}

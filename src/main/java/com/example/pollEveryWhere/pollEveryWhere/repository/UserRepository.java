package com.example.pollEveryWhere.pollEveryWhere.repository;


import org.springframework.data.repository.Repository;

import com.example.pollEveryWhere.pollEveryWhere.model.User;

public interface UserRepository extends Repository<User, Integer> {
	User save(User user);
	User findByEmail(String email);
}

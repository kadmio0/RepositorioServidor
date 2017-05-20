package com.balance.service;

import com.balance.model.User;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);
	Iterable<User> listAllUsers();
}
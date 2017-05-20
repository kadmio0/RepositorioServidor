package com.balance.service;

import com.balance.model.User;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user);
	Iterable<User> listAllUsers();
<<<<<<< HEAD
	User getUserById(Integer id);
	void deleteUser(Integer id);
=======
>>>>>>> aee3e168b14f0eaae80665da040e9f62079ce89e
}
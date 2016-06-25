package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User, Integer> {
	User findByUserName(String userName);
}

package com.example;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServicesImpl 
{
	@Autowired
	private IUserDao dao;
	Logger log = Logger.getLogger(this.getClass());
	
//	 @Secured("hasRole('USER')")
	public void createUser(User user)
	{
		log.info("---  Creating User ---");
		dao.save(user);
	}
	
	public User findByUserName(String userName)
	{
		return dao.findByUserName(userName);
	}
}

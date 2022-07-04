package com.edu.HotelReservationApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.exception.EmailIdNotFoundException;
import com.edu.HotelReservationApp.exception.FirstNameNotFoundException;
import com.edu.HotelReservationApp.exception.GivenIdNotFoundException;
import com.edu.HotelReservationApp.exception.LastNameNotFoundException;
import com.edu.HotelReservationApp.exception.NameNotFoundException;
import com.edu.HotelReservationApp.exception.NoRecordFoundException;
import com.edu.HotelReservationApp.exception.ResourceNotFoundException;
import com.edu.HotelReservationApp.exception.UsernameNotFoundException;
import com.edu.HotelReservationApp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepos;
	
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return userRepos.save(user);
	}
	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		
		List<User> use = userRepos.findAll();
		if(use.isEmpty()) {
			throw new NoRecordFoundException();
		}
		else {
			return use;
		}	
	}
	@Override
	public User getUserById(long userId) {
		Optional<User> user = userRepos.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			throw new GivenIdNotFoundException();
		}
	}
	@Override
	public User updateUser(long userId, User user) {
		// TODO Auto-generated method stub
		User user1 = new User();
		user1 = userRepos.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("User","Id",userId));
		user1.setFirstName(user.getFirstName());
		user1.setLastName(user.getLastName());
		user1.setContactNo(user.getContactNo());
		user1.setEmailId(user.getEmailId());
		user1.setUsername(user.getUsername());
		user1.setPassword(user.getPassword());
		user1.setFullAddress(user.getFullAddress());
		user1.setAadharNumber(user.getAadharNumber());
			
		userRepos.save(user1);
		return user1;
	}
	@Override
	public String deleteUser(long userId) {
		// TODO Auto-generated method stub
		User user = new User();
		user = userRepos.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("User","Id",userId));
		userRepos.deleteById(userId);
		return "Record is deleted successfully";
		
	}
	@Override
	public List<User> getUserByFirstName(String firstName) {
		// TODO Auto-generated method stub
		List<User> users = userRepos.getUserByFirstName(firstName);
		if(users.isEmpty()) {
			throw new FirstNameNotFoundException();
		}
		else {
			return users;
		}
	}
	@Override
	public List<User> getUserByLastName(String lastName) {
		// TODO Auto-generated method stub
		//return userRepos.getUserByLastName(lastName);
		List<User> users = userRepos.getUserByLastName(lastName);
		if(users.isEmpty()) {
			throw new LastNameNotFoundException();
		}
		else {
			return users;
		}
	}
	@Override
	public List<User> getUserByFullName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		
		List<User> users = userRepos.getUserByFullName(firstName,lastName);
		if(users.isEmpty()) {
			throw new NameNotFoundException();			
		}
		else {
			return users;	
		}
	}
	@Override
	public User getUserByEmailId(String emailId) {
		// TODO Auto-generated method stub
		//return userRepos.findByEmailId(emailId);
		 Optional<User> user = userRepos.findByEmailId(emailId);
			if(user.isPresent()) {
				return user.get();	
			}
			else {
				throw new EmailIdNotFoundException();
			}
	}
	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		//return userRepos.findByUsername(username);
		 Optional<User> user = userRepos.findByUsername(username);
			if(user.isPresent()) {
				return user.get();
			}
			else {
				throw new UsernameNotFoundException();	
	    }
	}
}

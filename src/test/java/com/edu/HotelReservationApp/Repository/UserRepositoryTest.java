package com.edu.HotelReservationApp.Repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void addUserTest() {
		User user = userRepository.save(new User(103,"efgh","ijkl","98713870982","userEFGH","ab3vghj41","efgh@gmail.com","652127883865","Chennai"));
		
		Assertions.assertThat(user.getUserId()).isGreaterThan(0);
	}
	
	@Test
	
	public void getUserTest() {
		User user = userRepository.findById(202L).get();
		
		Assertions.assertThat(user.getUserId()).isEqualTo(102L);
	}
	@Test
	public void getUserListTest() {
		List<User> users = userRepository.findAll();
		
		Assertions.assertThat(users.size()).isGreaterThan(0);
		
	}
	
	@Test
	public void updateUserTest() {
		User user = userRepository.findById(202L).get();
		
		user.setUsername("Sakthi");
		
		User update = userRepository.save(user);
		
		Assertions.assertThat(update.getUsername()).isEqualTo("Sakthi");
	}
	@Test
	public void deleteUserTest() {
		User us = userRepository.findById(752L).get();
		userRepository.delete(us);
		User user = null;
		Optional<User> user1 = userRepository.findByAadharNumber("318974210987");
		if(user1.isPresent()) {
			user = user1.get();
		}
		Assertions.assertThat(user).isNull();
	}
	
	

}

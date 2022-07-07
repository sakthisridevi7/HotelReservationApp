package com.edu.HotelReservationApp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.exception.GivenIdNotFoundException;
import com.edu.HotelReservationApp.exception.NoRecordFoundException;
import com.edu.HotelReservationApp.exception.RecordAlreadyExistException;
import com.edu.HotelReservationApp.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository userRepos;
	
	@Autowired
	@InjectMocks
	private UserServiceImpl userService;
	
	private User user1;
	private User user2;
	List<User> userList;
	
	//Method to execute before each testcase execution
	//before each testcase
	@BeforeEach
	public void setUp() {
		userList = new ArrayList<>();
		
		user1 = new User(11,"john","john@gmail.com");
		user2= new User(12,"jonny","jonny@gmail.com");
	userList.add(user1);
	userList.add(user2);
	}
	
	@AfterEach
	public void afterTest() {
		user1=user2=null;
		userList=null;
		
	}
	@DisplayName("Test for saveUser() method")
	@Test
	public void givenUserToAddShouldReturnAddedUser() {
		when(userRepos.save(user1)).thenReturn(user1);
		User savedUser=userService.saveUser(user1);
		
		System.out.println(savedUser);
	    assertThat(savedUser).isNotNull();
	}
	//To test saveUser() method throws exception if given Record is already exist
	@Test
	public void givenExistingIdWhenSaveUserThenThrowsException() {
		User user = new User(11,"john","john@gmail.com");
		when(userRepos.findById(user.getUserId())).thenReturn(Optional.of(user));
		
		org.junit.jupiter.api.Assertions.assertThrows(RecordAlreadyExistException.class,()->{
			 userService.saveUser(user);
		});
	}
	 // To test getSaveList() method
		@Test
		public void givenGetAllUserShouldReturnListOfAllUser()throws NoRecordFoundException {
			
			userRepos.saveAll(userList);
			
			when(userRepos.findAll()).thenReturn(userList);
			
			List<User> actualUserList = userService.getUserList();
			
			assertThat(actualUserList).isEqualTo(userList);
			
		}
		@Test
		public void givenIdThenShouldReturnUserOfThatId() throws GivenIdNotFoundException{
			
			when(userRepos.findById(11L)).thenReturn(Optional.ofNullable(user1));
			assertThat(userService.getUserById(user1.getUserId())).isEqualTo(user1);
			
		}
		
	@Test
		public void givenIdToDeleteThenShouldDeleteUserOfThatId() {
			when(userRepos.findById(user1.getUserId())).thenReturn(Optional.ofNullable(user1));
			
			
			
	        assertThat(userService.deleteUser(user1.getUserId())).isEqualTo("Record is deleted successfully");
		}
		
		@Test
		public void givenIdToDeleteNotExistThenThrowsException()  {
			long userId = 11L;
		    org.junit.jupiter.api.Assertions.assertThrows(GivenIdNotFoundException.class, () -> {
	        userService.deleteUser(userId);
	});

		} 
		
	    @DisplayName("JUnit test for updateUser method")
	    @Test
	    public void givenUserObject_whenUpdateUser_thenReturnUpdatedUser(){
	    	long userId = 11L;
	        when(userRepos.save(user1)).thenReturn(user1);
	        when(userRepos.findById(userId)).thenReturn(Optional.of(user1));
	        user1.setEmailId("ram@gmail.com");
	        user1.setFirstName("Ram");
	        User updatedUser = userService.updateUser(userId, user1);

	        assertThat(updatedUser.getEmailId()).isEqualTo("ram@gmail.com");
	        assertThat(updatedUser.getFirstName()).isEqualTo("Ram");
	    }
	    
	    @Test
		public void givenIdToUpdateNotExistThenThrowsException()  {
			long userId = 11L;
			User user = new User();
			user1.setEmailId("ram@gmail.com");
	        user1.setFirstName("Ram");
	        
		    org.junit.jupiter.api.Assertions.assertThrows(GivenIdNotFoundException.class, () -> {
	        userService.updateUser(userId, user);
	});

		} 


}

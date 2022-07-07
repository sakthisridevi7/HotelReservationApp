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

import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.exception.GivenIdNotFoundException;
import com.edu.HotelReservationApp.exception.NoRecordFoundException;
import com.edu.HotelReservationApp.exception.RecordAlreadyExistException;
import com.edu.HotelReservationApp.repository.RoomRepository;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
	
	 @Mock
	private RoomRepository roomRepos;
	
	@Autowired
	@InjectMocks
	private RoomServiceImpl roomService;
	
	private Room room1;
	private Room room2;
	List<Room> roomList;
	
	//Method to execute before each testcase execution
	//before each testcase
	@BeforeEach
	public void setUp() {
		roomList = new ArrayList<>();
		
		room1 = new Room(11,"1","3");
		room2 = new Room(12,"2","3");
	roomList.add(room1);
	roomList.add(room2);
	}
	
	@AfterEach
	public void afterTest() {
		room1=room2=null;
		roomList=null;
		
	}
	@DisplayName("Test for saveRoom() method")
	@Test
	public void givenRoomToAddShouldReturnAddedroom() {
		when(roomRepos.save(room1)).thenReturn(room1);
		Room savedRoom=roomService.saveRoom(room1);
		
		System.out.println(savedRoom);
	    assertThat(savedRoom).isNotNull();
	}
	//To test saveRoom() method throws exception if given Record is already exist
	@Test
	public void givenExistingIdWhenSaveRoomThenThrowsException() {
		Room room = new Room(11,"1","3");
		when(roomRepos.findById(room.getRoomId())).thenReturn(Optional.of(room));
		
		org.junit.jupiter.api.Assertions.assertThrows(RecordAlreadyExistException.class,()->{
			 roomService.saveRoom(room);
		});
	}
	 // To test getRoomList() method
		@Test
		public void givenGetAllRoomShouldReturnListOfAllRoom()throws NoRecordFoundException {
			
			roomRepos.saveAll(roomList);
			
			when(roomRepos.findAll()).thenReturn(roomList);
			
			List<Room> actualRoomList = roomService.getRoomList();
			
			assertThat(actualRoomList).isEqualTo(roomList);
			
		}
		@Test
		public void givenIdThenShouldReturnRoomOfThatId() throws GivenIdNotFoundException{
			
			when(roomRepos.findById(11L)).thenReturn(Optional.ofNullable(room1));
			assertThat(roomService.getRoomById(room1.getRoomId())).isEqualTo(room1);
			
		}
		
	    @Test
		public void givenIdToDeleteThenShouldDeleteRoomOfThatId() {
			when(roomRepos.findById(room1.getRoomId())).thenReturn(Optional.ofNullable(room1));
			
			
			
	        assertThat(roomService.deleteRoom(room1.getRoomId())).isEqualTo("Record is deleted successfully");
		}
		
		@Test
		public void givenIdToDeleteNotExistThenThrowsException()  {
			long roomId = 11L;
		    org.junit.jupiter.api.Assertions.assertThrows(GivenIdNotFoundException.class, () -> {
	        roomService.deleteRoom(roomId);
	});

		} 
		
	    @DisplayName("JUnit test for updateRoom method")
	    @Test
	    public void givenRoomObject_whenUpdateRoom_thenReturnUpdatedRoom(){
	    	long roomId = 11L;
	        when(roomRepos.save(room1)).thenReturn(room1);
	        when(roomRepos.findById(roomId)).thenReturn(Optional.of(room1));
	        room1.setRoomNo("4");
	        room1.setNoOfBed("1");
	        Room updatedRoom = roomService.updateRoom(roomId, room1);
	        System.out.println(updatedRoom);
	        assertThat(updatedRoom.getRoomNo()).isEqualTo("4");
	        assertThat(updatedRoom.getNoOfBed()).isEqualTo("1");
	    }
	    
	    @Test
		public void givenIdToUpdateNotExistThenThrowsException()  {
			long roomId = 11L;
			Room room = new Room();
			room1.setRoomNo("4");
	        room1.setNoOfBed("1");
	        
		    org.junit.jupiter.api.Assertions.assertThrows(GivenIdNotFoundException.class, () -> {
	        roomService.updateRoom(roomId, room1);
	});

		} 

	 

}

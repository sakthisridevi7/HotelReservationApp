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

import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.repository.RoomRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoomRepositoryTest {
	
	@Autowired
	private RoomRepository roomRepos;
	
	@Test
	public void addRoomTest() {
		Room room  = roomRepos.save(new Room(4,"15","2",4000,true));
		
		Assertions.assertThat(room.getRoomId()).isGreaterThan(0);
	}
	
	@Test
	public void getRoomTest() {
		Room room  = roomRepos.findById(503L).get();
		
		Assertions.assertThat(room.getRoomId()).isEqualTo(53L);
	}
	@Test
	public void getRoomListTest() {
		List<Room> rooms  = roomRepos.findAll();
		
		Assertions.assertThat(rooms.size()).isGreaterThan(0);
		
	}
	
	@Test
	public void updateRoomTest() {
		Room room  = roomRepos.findById(153L).get();
		
		room.setStatus(false);
		
		Room update = roomRepos.save(room);
		
		Assertions.assertThat(update.isStatus()).isEqualTo(false);
	}
	@Test
	public void deleteRoomTest() {
		Room rom = roomRepos.findById(2L).get();
		roomRepos.delete(rom);
		Room room  = null;
		Optional<Room> room1 = roomRepos.findByRoomFare(4000.00);
		if(room1.isPresent()) {
			room = room1.get();
		}
		Assertions.assertThat(room).isNull();
	}
	

}

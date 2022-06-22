package com.edu.HotelReservationApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.entity.User;
import com.edu.HotelReservationApp.repository.RoomRepository;

import exception.GivenIdNotFoundException;
import exception.NoRecordFoundException;
import exception.ResourceNotFoundException;
@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	RoomRepository roomRepos;

	@Override
	public Room addRoom(Room room) {
		// TODO Auto-generated method stub
		return roomRepos.save(room);
	}

	@Override
	public List<Room> getRoomList() {
		// TODO Auto-generated method stub
		List<Room> rooms = roomRepos.findAll();
		if(rooms.isEmpty()) {
			throw new NoRecordFoundException();
		}
		else {
			return rooms;
		}
	}

	@Override
	public Room getRoomById(long roomId) {
		// TODO Auto-generated method stub
		/*Room room = new Room();
		room = roomRepos.findById(roomId).orElseThrow(
				()->new ResourceNotFoundException("Room","Id",roomId));
		roomRepos.findById(roomId);
				
		return room;*/
		Optional<Room> room = roomRepos.findById(roomId);
		if(room.isPresent()) {
			return room.get();
		}
		else {
			throw new GivenIdNotFoundException();
		}
		
	}

	@Override
	public Room updateRoom(long roomId, Room room) {
		// TODO Auto-generated method stub
		Room room1 = new Room();
		room1 = roomRepos.findById(roomId).orElseThrow(
				()->new ResourceNotFoundException("Room","Id",roomId));
		roomRepos.findById(roomId);
		
		room1.setRoomNo(room.getRoomNo());
		room1.setRoomFare(room.getRoomFare());
		room1.setNoOfBed(room.getNoOfBed());
		room1.setStatus(room.isStatus());
		
		roomRepos.save(room1);
		return room1;
	}

	@Override
	public String deleteRoom(long roomId) {
		// TODO Auto-generated method stub
		Room room = new Room();
		room = roomRepos.findById(roomId).orElseThrow(
				()->new ResourceNotFoundException("User","Id",roomId));
		roomRepos.deleteById(roomId);
		return "Record is deleted successfully";
		
	}

	@Override
	public Room getRoomByRoomNo(String roomNo) {
		// TODO Auto-generated method stub
		return roomRepos.getRoomByRoomNo(roomNo);
	}

	@Override
	public List<Room> getRoomByNoOfBed(String noOfBed) {
		// TODO Auto-generated method stub
		return roomRepos.getRoomByNoOfBed(noOfBed);
	}

	@Override
	public List<Room> getRoomByStatus(boolean status) {
		// TODO Auto-generated method stub
		return roomRepos.findByStatus(status);
	}
	
	
	

}

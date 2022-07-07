package com.edu.HotelReservationApp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.exception.GivenIdNotFoundException;
import com.edu.HotelReservationApp.exception.NoRecordFoundException;
import com.edu.HotelReservationApp.exception.RecordAlreadyExistException;
import com.edu.HotelReservationApp.exception.RoomNotFoundException;
import com.edu.HotelReservationApp.exception.StatusRecordNotFoundException;
import com.edu.HotelReservationApp.repository.RoomRepository;

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
				()->new GivenIdNotFoundException());
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
				()->new GivenIdNotFoundException());
		roomRepos.deleteById(roomId);
		return "Record is deleted successfully";
		
	}

	@Override
	public Room getRoomByRoomNo(String roomNo) {
		// TODO Auto-generated method stub
		Optional<Room> room = roomRepos.getRoomByRoomNo(roomNo);
		if(room.isPresent()) {
			return room.get();
		}
		else {
			throw new RoomNotFoundException();
		}
	}

	@Override
	public List<Room> getRoomByNoOfBed(String noOfBed) {
		// TODO Auto-generated method stub
		List<Room> rooms = roomRepos.getRoomByNoOfBed(noOfBed);
		if(rooms.isEmpty()) {
			throw new NoRecordFoundException();
		}
		else {
			return rooms;
		}
	}

	@Override
	public List<Room> getRoomByStatus(boolean status) {
		// TODO Auto-generated method stub
		
		List<Room> rooms = roomRepos.findByStatus(status);
		if(rooms.isEmpty()) {
			throw new StatusRecordNotFoundException();
		}
		else {
			return rooms;
		}
	}

	@Override
	public Map<Object, Object> getRoomGroupByStatus() {
		// TODO Auto-generated method stub
		List<Object[]> objects = roomRepos.getRoomGroupByStatus();
		Map<Object,Object> map=new HashMap<>();
		for(Object[] obj : objects) {
			map.put(obj[0],obj[1]);
		}
		return map;
	}

	public Room saveRoom(Room room1) {
		// TODO Auto-generated method stub
		Optional<Room> ro=roomRepos.findById(room1.getRoomId());
		if(!ro.isPresent())
		return roomRepos.save(room1);
		else
			throw new RecordAlreadyExistException();
	
	}
	
	
	
	

}

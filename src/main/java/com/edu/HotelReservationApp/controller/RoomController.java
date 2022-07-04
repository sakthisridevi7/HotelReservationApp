package com.edu.HotelReservationApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.HotelReservationApp.entity.Room;
import com.edu.HotelReservationApp.service.RoomService;

@RestController
@RequestMapping("/api/room")
public class RoomController {
	
	@Autowired
	RoomService roomService;
	
	@PostMapping
	public ResponseEntity<Room> addRoom(@Valid @RequestBody Room room){
		return new ResponseEntity<Room>(roomService.addRoom(room),HttpStatus.CREATED);
		
	}
	@GetMapping
	public List<Room> getRoomList(){
		return roomService.getRoomList();
	}
	@GetMapping("/{id}")
	public Room getRoomById(@PathVariable("id")long roomId) {
		return roomService.getRoomById(roomId);
	}
	@PutMapping("/{id}")
	public Room updateRoom(@PathVariable("id")long roomId,@Valid @RequestBody Room room) {
		return roomService.updateRoom(roomId,room);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRoom(@PathVariable("id")long roomId){
		return new ResponseEntity<String>(roomService.deleteRoom(roomId),HttpStatus.OK);
		
	}
	@GetMapping("/GetRoomByRoomNo/{roomNo}")
	public Room getRoomByRoomNo(@PathVariable("roomNo")String roomNo) {
		return roomService.getRoomByRoomNo(roomNo);
	}
	@GetMapping("/GetRoomByNoOfBed/{noOfBed}")
	public List<Room> getRoomByNoOfBed(@PathVariable("noOfBed")String noOfBed){
		return roomService.getRoomByNoOfBed(noOfBed);
	}
	@GetMapping("/GetRoomByStatus/{status}")
	public List<Room> getRoomByStatus(@PathVariable("status") boolean status){
		return roomService.getRoomByStatus(status);
	}
	

}

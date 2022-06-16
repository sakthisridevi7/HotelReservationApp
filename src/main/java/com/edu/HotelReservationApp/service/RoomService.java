package com.edu.HotelReservationApp.service;

import java.util.List;

import com.edu.HotelReservationApp.entity.Room;

public interface RoomService {

	Room addRoom(Room room);

	List<Room> getRoomList();

	Room getRoomById(long roomId);

	Room updateRoom(long roomId, Room room);

	String deleteRoom(long roomId);

	Room getRoomByRoomNo(String roomNo);

	List<Room> getRoomByNoOfBed(String noOfBed);

	List<Room> getRoomByStatus(boolean status);

}

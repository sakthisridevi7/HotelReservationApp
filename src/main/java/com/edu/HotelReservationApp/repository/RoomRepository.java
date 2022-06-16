package com.edu.HotelReservationApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.HotelReservationApp.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Long>{
	@Query("select r from Room r where r.roomNo = :roomNo")
	Room getRoomByRoomNo(@Param("roomNo")String roomNo);
	
	@Query("select r from Room r where r.noOfBed = :noOfBed")
	List<Room> getRoomByNoOfBed(@Param("noOfBed")String noOfBed);

	List<Room> findByStatus(boolean status);

	

}

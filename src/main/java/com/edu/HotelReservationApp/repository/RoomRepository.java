package com.edu.HotelReservationApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edu.HotelReservationApp.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Long>{
	@Query("select r from Room r where r.roomNo = :roomNo")
	Optional<Room> getRoomByRoomNo(@Param("roomNo")String roomNo);
	
	@Query("select r from Room r where r.noOfBed = :noOfBed")
	List<Room> getRoomByNoOfBed(@Param("noOfBed")String noOfBed);

	List<Room> findByStatus(boolean status);

	Optional<Room> findByRoomFare(double d); 
	
	
	@Query("select r.status,count(r.roomId) from Room r group by r.status")
	List<Object[]> getRoomGroupByStatus();

	

}

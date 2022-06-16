package com.edu.HotelReservationApp.controller;

import java.util.List;

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

import com.edu.HotelReservationApp.entity.Reservation;
import com.edu.HotelReservationApp.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@PostMapping
	public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation){
		return new ResponseEntity<Reservation>(reservationService.saveReservation(reservation),HttpStatus.CREATED);
	}
	@GetMapping
	public List<Reservation> getReservationList(){
		return reservationService.getReservationList();
	}
	@GetMapping("/{id}")
	public Reservation getReservationById(@PathVariable("id")long resId) {
		return reservationService.getReservationById(resId);
	}
	@PutMapping("/{id}")
	public Reservation updateReservation(@PathVariable("id")long resId,@RequestBody Reservation reservation) {
		return reservationService.updateReservation(resId,reservation);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReservation(@PathVariable("id")long resId){
		return new ResponseEntity<String>(reservationService.deleteReservation(resId),HttpStatus.OK);
	}

}

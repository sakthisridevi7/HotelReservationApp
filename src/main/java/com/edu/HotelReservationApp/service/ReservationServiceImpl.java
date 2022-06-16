package com.edu.HotelReservationApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.HotelReservationApp.entity.Reservation;
import com.edu.HotelReservationApp.repository.ReservationRepository;

import exception.ResourceNotFoundException;

@Service
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	ReservationRepository reservationRepos;

	@Override
	public Reservation saveReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return reservationRepos.save(reservation) ;
	}

	@Override
	public List<Reservation> getReservationList() {
		// TODO Auto-generated method stub
		return reservationRepos.findAll();
	}

	@Override
	public Reservation getReservationById(long resId) {
		// TODO Auto-generated method stub
		
		Reservation reservation = new Reservation();
		reservation = reservationRepos.findById(resId).orElseThrow(
				()-> new ResourceNotFoundException("Reservation","Id",resId));
				
		reservationRepos.findById(resId);
		return reservation;
	}

	@Override
	public Reservation updateReservation(long resId, Reservation reservation) {
		// TODO Auto-generated method stub
		Reservation reserv = new Reservation();
		reserv = reservationRepos.findById(resId).orElseThrow(
				()-> new ResourceNotFoundException("Reservation","Id",resId));
		
		reserv.setResId(reservation.getResId());
		reserv.setStayDays(reservation.getStayDays());
		reserv.setNoOfGuest(reservation.getNoOfGuest());
		reserv.setCheckInDateTime(reservation.getCheckInDateTime());
		reserv.setCheckOutDateTime(reservation.getCheckOutDateTime());
		return null;
	}

	@Override
	public String deleteReservation(long resId) {
		// TODO Auto-generated method stub
		Reservation reservation = new Reservation();
		reservation = reservationRepos.findById(resId).orElseThrow(
				()-> new ResourceNotFoundException("Reservation","Id",resId));
				
		reservationRepos.findById(resId);
		return "Record is deleted Successfully";
	}
	

}

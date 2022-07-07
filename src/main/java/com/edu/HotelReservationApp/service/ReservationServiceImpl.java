package com.edu.HotelReservationApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.HotelReservationApp.entity.Reservation;
import com.edu.HotelReservationApp.exception.GivenIdNotFoundException;
import com.edu.HotelReservationApp.exception.NoRecordFoundException;
import com.edu.HotelReservationApp.exception.RecordAlreadyExistException;
import com.edu.HotelReservationApp.repository.ReservationRepository;



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
		
		
		List<Reservation> reservation = reservationRepos.findAll();
		if(reservation.isEmpty()) {
			throw new NoRecordFoundException();
		}else
		{
			return reservation;
		}
	}

	@Override
	public Reservation getReservationById(long resId) {
		// TODO Auto-generated method stub
		
		Optional<Reservation> reservation = reservationRepos.findById(resId);
		if(reservation.isPresent()) {
			return reservation.get();
		}
		else {
			throw new GivenIdNotFoundException();
		}
	}

	@Override
	public Reservation updateReservation(long resId, Reservation reservation) {
		// TODO Auto-generated method stub
		Reservation reserv = new Reservation();
		reserv = reservationRepos.findById(resId).orElseThrow(
				()-> new GivenIdNotFoundException());
		
		reserv.setResId(reservation.getResId());
		reserv.setStayDays(reservation.getStayDays());
		reserv.setNoOfGuest(reservation.getNoOfGuest());
		reserv.setReserveDate(reservation.getReserveDate().now());
		reserv.setCheckInDateTime(reservation.getCheckInDateTime());
		reserv.setCheckOutDateTime(reservation.getCheckInDateTime().plusDays(reservation.getStayDays()));
		reservationRepos.save(reserv);
		return reserv;
	}

	@Override
	public String deleteReservation(long resId) {
		// TODO Auto-generated method stub
		Reservation reservation = new Reservation();
		reservation = reservationRepos.findById(resId).orElseThrow(
				()-> new GivenIdNotFoundException());
		
		reservationRepos.deleteById(resId);
		return "Record is deleted successfully";
	}

	@Override
	public List<Reservation> getReservationByCheckInDateTime(LocalDateTime checkInDateTime) {
		// TODO Auto-generated method stub
		//System.out.println(checkInDateTime);
		List<Reservation> reservation = reservationRepos.getReservationByCheckInDateTime(checkInDateTime);
		if(reservation.isEmpty()) {
			throw new NoRecordFoundException();
		}
		else {
			return reservation;
		}
	}
	public Reservation saveReserv(Reservation reserv1) {
		// TODO Auto-generated method stub
		Optional<Reservation> res=reservationRepos.findById(reserv1.getResId());
		if(!res.isPresent())
		return reservationRepos.save(reserv1);
		else
			throw new RecordAlreadyExistException();
	}
	@Override
	public List<Reservation> getReservationByUserId(long userId) {
		// TODO Auto-generated method stub

		List<Reservation> reservation = reservationRepos.getReservationByUserId(userId);
		if(reservation.isEmpty()) 
			throw new GivenIdNotFoundException();
		else 
			return reservation;
	}
	
	@Override
	public List<Reservation> getReservationDateByReserveDate(LocalDateTime reserveDate) {
		// TODO Auto-generated method stub
		List<Reservation> reservation = reservationRepos.getReservationDateByReserveDate(reserveDate);
		if(reservation.isEmpty()) {
			throw new NoRecordFoundException();
		}
		else {
			return reservation;
		}
	}
	
}
	

	
	



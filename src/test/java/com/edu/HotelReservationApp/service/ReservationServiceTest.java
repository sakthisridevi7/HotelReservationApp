package com.edu.HotelReservationApp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.HotelReservationApp.entity.Reservation;
import com.edu.HotelReservationApp.exception.GivenIdNotFoundException;
import com.edu.HotelReservationApp.exception.NoRecordFoundException;
import com.edu.HotelReservationApp.exception.RecordAlreadyExistException;
import com.edu.HotelReservationApp.repository.ReservationRepository;


@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
	@Mock
	private ReservationRepository reservationRepos;
	
	@Autowired
	@InjectMocks
	private ReservationServiceImpl reservationService;
	
	private Reservation reserv1;
	private Reservation reserv2;
	List<Reservation> reservList;
	LocalDateTime d = LocalDateTime.of(2022,07,10,14,56);
	LocalDateTime d1 = LocalDateTime.of(2022,07,15,14,56);
	
	//Method to execute before each testcase execution
	//before each testcase
	@BeforeEach
	public void setUp() {
		reservList = new ArrayList<>();
		
		
		reserv1 = new Reservation(11,2,3,d,d1);
		reserv2 = new Reservation(12,3,2,d,d1);
	reservList.add(reserv1);
	reservList.add(reserv2);
	}
	
	@AfterEach
	public void afterTest() {
		reserv1=reserv2=null;
		reservList=null;
		
	}
	@DisplayName("Test for saveReservation() method")
	@Test
	public void givenReservationToAddShouldReturnAddedreservation() {
		when(reservationRepos.save(reserv1)).thenReturn(reserv1);
		Reservation savedReserv=reservationService.saveReserv(reserv1);
		
		System.out.println(savedReserv);
	    assertThat(savedReserv).isNotNull();
	}
	//To test saveReservation() method throws exception if given Record is already exist
	@Test
	public void givenExistingIdWhenSaveReservationThenThrowsException() {
		Reservation reservation = new Reservation(11,2,3,d,d1);
		when(reservationRepos.findById(reservation.getResId())).thenReturn(Optional.of(reservation));
		
		org.junit.jupiter.api.Assertions.assertThrows(RecordAlreadyExistException.class,()->{
			 reservationService.saveReserv(reservation);
		});
	}
	 // To test getReservList() method
		@Test
		public void givenGetAllReservationShouldReturnListOfAllReservation()throws NoRecordFoundException {
			
			reservationRepos.saveAll(reservList);
			
			when(reservationRepos.findAll()).thenReturn(reservList);
			
			List<Reservation> actualReservList = reservationService.getReservationList();
			
			assertThat(actualReservList).isEqualTo(reservList);
			
		}
		@Test
		public void givenIdThenShouldReturnReservationOfThatId() throws GivenIdNotFoundException{
			
			when(reservationRepos.findById(11L)).thenReturn(Optional.ofNullable(reserv1));
			assertThat(reservationService.getReservationById(reserv1.getResId())).isEqualTo(reserv1);
			
		}
		
	    @Test
		public void givenIdToDeleteThenShouldDeleteReservationOfThatId() {
			when(reservationRepos.findById(reserv1.getResId())).thenReturn(Optional.ofNullable(reserv1));
			
			
			
	        assertThat(reservationService.deleteReservation(reserv1.getResId())).isEqualTo("Record is deleted successfully");
		}
		
		@Test
		public void givenIdToDeleteNotExistThenThrowsException()  {
			long resId = 11L;
		    org.junit.jupiter.api.Assertions.assertThrows(GivenIdNotFoundException.class, () -> {
	        reservationService.deleteReservation(resId);
	});

		} 
		
	    @DisplayName("JUnit test for updateReservation method")
	    @Test
	    public void givenReservationObject_whenUpdateReservation_thenReturnUpdatedReservation(){
	    	long resId = 11L;
	        when(reservationRepos.save(reserv1)).thenReturn(reserv1);
	        when(reservationRepos.findById(resId)).thenReturn(Optional.of(reserv1));
	        reserv1.setNoOfGuest(4);
	        reserv1.setStayDays(5);
	        reserv1.setCheckInDateTime(d);
	        reserv1.setCheckOutDateTime(d1);
	        Reservation updatedReservation = reservationService.updateReservation(resId, reserv1);
	        System.out.println(updatedReservation);
	        assertThat(updatedReservation.getNoOfGuest()).isEqualTo(4);
	        assertThat(updatedReservation.getStayDays()).isEqualTo(5);
	        assertThat(updatedReservation.getCheckInDateTime()).isEqualTo(d);
	        assertThat(updatedReservation.getCheckOutDateTime()).isEqualTo(d1);
	    }
	    
	    @Test
		public void givenIdToUpdateNotExistThenThrowsException()  {
			long resId = 11L;
			Reservation reservation = new Reservation();
			reserv1.setNoOfGuest(4);
			reserv1.setStayDays(5);
	        reserv1.setCheckInDateTime(d);
	        reserv1.setCheckOutDateTime(d1);
	        
		    org.junit.jupiter.api.Assertions.assertThrows(GivenIdNotFoundException.class, () -> {
	        reservationService.updateReservation(resId, reserv1);
	     
	        
	});

		} 


}

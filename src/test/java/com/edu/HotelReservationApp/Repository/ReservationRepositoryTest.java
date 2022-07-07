package com.edu.HotelReservationApp.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.edu.HotelReservationApp.entity.Reservation;

import com.edu.HotelReservationApp.repository.ReservationRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ReservationRepositoryTest {
	
	@Autowired
	private ReservationRepository reservationRepos;
	
	@Test
	public void saveReservationTest() {
		LocalDateTime d = LocalDateTime.of(2022,07,10,14,56);
		LocalDateTime d1 = LocalDateTime.of(2022,07,10,14,56);
		Reservation reservation = reservationRepos.save(new Reservation(4,3,2,d,d1));
		Assertions.assertThat(reservation.getResId()).isGreaterThan(0);
	}
	/*@Test
	public void getReservationTest() {
		Reservation reservation= reservationRepos.findById(102L).get();
		
		Assertions.assertThat(reservation.getResId()).isEqualTo(102L);
	}
	@Test
	public void getReservationListTest() {
		List<Reservation> reservations = reservationRepos.findAll();
		
		Assertions.assertThat(reservations.size()).isGreaterThan(0);
		
	}
	
	@Test
	public void updateReservationTest() {
		Reservation reservation = reservationRepos.findById(202L).get();
		
		reservation.setStayDays(4);
		
		Reservation update = reservationRepos.save(reservation);
		
		Assertions.assertThat(update.getStayDays()).isEqualTo(4);
	}
	@Test
	public void deleteReservationTest() {
		Reservation  res = reservationRepos.findById(3L).get();
		reservationRepos.delete(res);
		Reservation reservation = null;
		Optional<Reservation> reservation1 = reservationRepos.findByNoOfGuest(2);
		if(reservation1.isPresent()) {
			reservation  = reservation1.get();
		}
		Assertions.assertThat(reservation).isNull();
	}*/
	

}

package com.edu.HotelReservationApp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandlers {
	@ExceptionHandler(GivenIdNotFoundException.class)
	public ResponseEntity<Object> handleGivenIdNotFoundException(){
		return new ResponseEntity<Object>("Given Id is not available",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<Object> handleNoRecordFoundException(){
		return new ResponseEntity<Object>("No Record Found",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(LastNameNotFoundException.class)
	public ResponseEntity<Object> handleLastNameNotFoundException(){
		return new ResponseEntity<Object>("Given Last Name is not available",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NameNotFoundException.class)
	public ResponseEntity<Object> handleNameNotFoundException(){
		return new ResponseEntity<Object>("Given  Name is not available",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(FirstNameNotFoundException.class)
	public ResponseEntity<Object> handleFirstNameNotFoundException(){
		return new ResponseEntity<Object>("Given First Name is not available",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EmailIdNotFoundException.class)
	public ResponseEntity<Object> handleEmailIdNotFoundException(){
		return new ResponseEntity<Object>("Given EmailId is not available",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> handleUsernameNotFoundException(){
		return new ResponseEntity<Object>("Given Username is not available",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<Object> handleRoomNotFoundException(){
		return new ResponseEntity<Object>("Given Room is not available",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(StatusRecordNotFoundException.class)
	public ResponseEntity<Object> handleStatusRecordNotFoundException(){
		return new ResponseEntity<Object>("Given Status Record is not found",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value= {MethodArgumentNotValidException.class})
	public ResponseEntity<Map<String , String> >handleValidationExceptions(
			MethodArgumentNotValidException ex){
		Map<String , String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName,errorMessage);
			
		});
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
		
	}
}

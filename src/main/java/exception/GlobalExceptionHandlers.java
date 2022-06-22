package exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@ExceptionHandler(FirstNameNotFoundException.class)
	public ResponseEntity<Object> handleFirstNameNotFoundException(){
		return new ResponseEntity<Object>("Given First Name Record Not Found",HttpStatus.NOT_FOUND);
	}

}

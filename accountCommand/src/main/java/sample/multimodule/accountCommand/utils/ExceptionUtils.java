package sample.multimodule.accountCommand.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionUtils {

	private ResponseEntity<Object> buildResponseEntity(Feedback feedback) {
		return new ResponseEntity<>(feedback, feedback.getStatus());
	}

	@ExceptionHandler
	public ResponseEntity<Object> handleClientException(MethodArgumentNotValidException exception) {
		var feedback = new Feedback(HttpStatus.BAD_REQUEST);
		feedback.setMessage("Invalid arguments");
		feedback.setDebugMessage(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		System.out.println(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return buildResponseEntity(feedback);
	}

}

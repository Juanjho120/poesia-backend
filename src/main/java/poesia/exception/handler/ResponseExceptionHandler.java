package poesia.exception.handler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import poesia.exception.dto.CarnetException;
import poesia.exception.dto.EdadException;
import poesia.exception.dto.ExceptionResponse;
import poesia.exception.dto.ModelNotFoundException;
import poesia.exception.dto.ReporteException;
import poesia.exception.dto.TelefonoException;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	//private String message = "";
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> allExceptionsHandler(Exception ex, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ModelNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> modelNotFoundExceptionHandler(ModelNotFoundException ex, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CarnetException.class)
	public final ResponseEntity<ExceptionResponse> carnetExceptionHandler(CarnetException ex, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EdadException.class)
	public final ResponseEntity<ExceptionResponse> edadExceptionHandler(EdadException ex, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TelefonoException.class)
	public final ResponseEntity<ExceptionResponse> telefonoHandler(TelefonoException ex, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ReporteException.class)
	public final ResponseEntity<ExceptionResponse> telefonoHandler(ReporteException ex, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(error, HttpStatus.FORBIDDEN);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getBindingResult().getAllErrors().stream().map(e -> {
			return e.getDefaultMessage().toString().concat(", ");
		}).collect(Collectors.joining());
		
		ExceptionResponse error = new ExceptionResponse(LocalDateTime.now(), message, request.getDescription(false));
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
}

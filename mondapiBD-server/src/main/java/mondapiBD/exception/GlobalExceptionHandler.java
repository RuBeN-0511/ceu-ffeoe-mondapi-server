package mondapiBD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<String> handle(ConflictException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}

	@ExceptionHandler(InactiveUserException.class)
	public ResponseEntity<String> handle(InactiveUserException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	@ExceptionHandler(IncorrectPasswordException.class)
	public ResponseEntity<String> handle(IncorrectPasswordException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handle(NotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

	@ExceptionHandler(NotValidException.class)
	public ResponseEntity<String> handle(NotValidException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest()
				.body(e.getFieldError().getField() + ": " + e.getFieldError().getDefaultMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle(Exception e) {
		log.error("Error inesperado en la petición: ", e); // Registrar el error en el servidor
		return ResponseEntity.internalServerError()
				.body("Error inesperado en el servidor. Consulte el log del servidor si tiene acceso");
	}
}

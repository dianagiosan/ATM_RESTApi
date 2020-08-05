package com.example.ATMProject.Infrastructure;

import com.example.ATMProject.Infrastructure.Exceptions.NotEnoughCashLeftException;
import com.example.ATMProject.Infrastructure.Exceptions.TransactionNotPossibleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.SocketTimeoutException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({NotEnoughCashLeftException.class, TransactionNotPossibleException.class, SocketTimeoutException.class})
		public final ResponseEntity<ApiError> handleException(Exception e){
			ApiError error = new ApiError(LocalDateTime.now(), e.getMessage());
			return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}
}

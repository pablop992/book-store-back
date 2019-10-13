package com.test.bookstore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.ExceptionHandler;

@AllArgsConstructor
@Getter
public class UnexpectedException extends RuntimeException {

  private Exception cause;

}

package com.lisade.togeduck.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class GeneralException extends RuntimeException {

    private final HttpStatus httpStatus;
}

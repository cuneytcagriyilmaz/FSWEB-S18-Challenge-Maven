package com.workintech.fswebs18challengemaven.exceptions;

import org.springframework.http.HttpStatus;

public class CardException extends RuntimeException {

    private HttpStatus status;

    public CardException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }


    public CardException(String message) {
        super(message);

    }

    public HttpStatus getHttpStatus() {
        return status;
    }
}

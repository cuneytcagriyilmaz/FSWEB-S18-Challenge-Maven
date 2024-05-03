package com.workintech.fswebs18challengemaven.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CardErrorResponse {
    private String message;
    private int statusCode;

    public CardErrorResponse(String message) {
        this.message = message;
    }
}

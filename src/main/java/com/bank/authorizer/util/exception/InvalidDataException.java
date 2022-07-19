package com.bank.authorizer.util.exception;

import org.springframework.validation.BindingResult;

public class InvalidDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final transient BindingResult bindingResult;

    public InvalidDataException(BindingResult bindingResult){
        super();
        this.bindingResult = bindingResult;
    }

    public InvalidDataException(String message, BindingResult bindingResult){
        super(message);
        this.bindingResult = bindingResult;
    }

    //Access methods
    public BindingResult getBindingResult() {
        return bindingResult;
    }
}

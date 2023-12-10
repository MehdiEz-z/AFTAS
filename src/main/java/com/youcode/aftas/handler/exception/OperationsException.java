package com.youcode.aftas.handler.exception;

import java.io.Serial;

public class OperationsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public OperationsException(String msg) {
        super(msg);
    }
}

package com.app.msscbeerservice.common.exception;

/**
 * @author t0k02w6 on 10/05/21
 * @project mssc-beer-service
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}

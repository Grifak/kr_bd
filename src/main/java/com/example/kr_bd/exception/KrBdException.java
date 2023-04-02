package com.example.kr_bd.exception;

public class KrBdException extends RuntimeException{
    public KrBdException(String message) {
        super(message, null);
    }
}

package com.zonni.error;

public class NoContaBuilderException extends RuntimeException {

    public NoContaBuilderException(String userNotBuilded) {
        super(userNotBuilded);
    }
}

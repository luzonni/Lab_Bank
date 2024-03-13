package com.zonni.error;

public class NoUserBuilderException extends RuntimeException {

    public NoUserBuilderException(String userNotBuilded) {
        super(userNotBuilded);
    }
}

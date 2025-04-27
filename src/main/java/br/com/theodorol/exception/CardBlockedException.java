package br.com.theodorol.exception;

public class CardBlockedException extends RuntimeException {

    public CardBlockedException(final String message) {
        super(message);
    }
}
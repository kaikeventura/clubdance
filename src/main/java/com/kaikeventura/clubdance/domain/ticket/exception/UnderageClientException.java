package com.kaikeventura.clubdance.domain.ticket.exception;

public class UnderageClientException extends RuntimeException {

    public UnderageClientException() {
        super("The client is under 18 years old");
    }
}

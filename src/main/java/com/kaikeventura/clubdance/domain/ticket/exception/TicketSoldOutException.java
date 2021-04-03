package com.kaikeventura.clubdance.domain.ticket.exception;

public class TicketSoldOutException extends RuntimeException {

    public TicketSoldOutException() {
        super("Ticket sold out");
    }
}

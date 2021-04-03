package com.kaikeventura.clubdance.domain.ticket.exception;

public class ExistingCpfForThisEventException extends RuntimeException {

    public ExistingCpfForThisEventException(final String cpf) {
        super(String.format("The cpf %s already belongs to an event ticket", cpf));
    }
}

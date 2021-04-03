package com.kaikeventura.clubdance.domain.ticket.infra.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class TicketDTO implements Serializable {

    private String clientName;

    private String clientCpf;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate clientDateOfBirth;

    private TicketType ticketType;
}

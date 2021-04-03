package com.kaikeventura.clubdance.domain.ticket.service;

import com.kaikeventura.clubdance.domain.event.entity.Event;
import com.kaikeventura.clubdance.domain.event.infra.dto.EventDTO;
import com.kaikeventura.clubdance.domain.event.infra.repository.EventRepository;
import com.kaikeventura.clubdance.domain.ticket.entity.Account;
import com.kaikeventura.clubdance.domain.ticket.entity.Ticket;
import com.kaikeventura.clubdance.domain.ticket.exception.ExistingCpfForThisEventException;
import com.kaikeventura.clubdance.domain.ticket.exception.UnderageClientException;
import com.kaikeventura.clubdance.domain.ticket.infra.dto.TicketDTO;
import com.kaikeventura.clubdance.domain.ticket.infra.dto.TicketType;
import com.kaikeventura.clubdance.domain.ticket.infra.handler.TicketAvailabilityHandler;
import com.kaikeventura.clubdance.domain.ticket.infra.repository.AccountRepository;
import com.kaikeventura.clubdance.domain.ticket.infra.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import static com.kaikeventura.clubdance.domain.event.infra.converter.EventMapper.EVENT_MAPPER;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final AccountRepository accountRepository;
    private final HttpSession httpSession;

    public EventDTO findEventByExternalId(final String externalId) {
        var event = this.retrieveEventByExternalId(externalId);
        this.saveExternalIdEventInSession(externalId);

        return EVENT_MAPPER.eventToEventDTO(event);
    }

    public void sellTicket(final TicketDTO ticketDTO) {
        this.checksIfThereIsAlreadyACpfForAnyEventTicket(ticketDTO.getClientCpf());
        this.checksIfTheClientIsOver18YearsOld(ticketDTO.getClientDateOfBirth());

        var event = this.retrieveEventByExternalId(this.retrieveExternalIdEvent());
        this.removeTicketFromAvailableQuantity(ticketDTO.getTicketType(), event);

        accountRepository.save(
                new Account(
                        new Ticket(
                                ticketDTO.getClientName(),
                                ticketDTO.getClientCpf(),
                                ticketDTO.getClientDateOfBirth(),
                                event
                        )
                )
        );
    }

    public String retrieveExternalIdEvent() {
        return (String) httpSession.getAttribute("externalIdEvent");
    }

    private Event retrieveEventByExternalId(final String externalId) {
        return this.eventRepository.findByExternalId(externalId).orElseThrow(RuntimeException::new);
    }

    private void saveExternalIdEventInSession(final String externalId) {
        httpSession.setAttribute("externalIdEvent", externalId);
    }

    private void checksIfThereIsAlreadyACpfForAnyEventTicket(final String cpf) {
        var tickets = ticketRepository.findAllByEventExternalId(this.retrieveExternalIdEvent());

        if (tickets.stream().anyMatch(ticket -> ticket.getClientCpf().equals(cpf))) {
            throw new ExistingCpfForThisEventException(cpf);
        }
    }

    private void checksIfTheClientIsOver18YearsOld(final LocalDate clientDateOfBirth) {
        var currentYear = LocalDate.now().getYear();
        if (currentYear - clientDateOfBirth.getYear() < 18) {
            throw new UnderageClientException();
        }
    }

    private void removeTicketFromAvailableQuantity(final TicketType ticketType, final Event event) {
        TicketAvailabilityHandler.valueOf(ticketType.ticketTypeHandler.name()).removeTicketFromAvailableQuantity(event);
    }
}

package com.kaikeventura.clubdance.domain.ticket.service;

import com.kaikeventura.clubdance.domain.event.entity.Event;
import com.kaikeventura.clubdance.domain.event.infra.dto.EventDTO;
import com.kaikeventura.clubdance.domain.event.infra.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.kaikeventura.clubdance.domain.event.infra.converter.EventMapper.EVENT_MAPPER;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final EventRepository eventRepository;
    private final HttpSession httpSession;

    public EventDTO findEventByExternalId(final String externalId) {
        var event = this.retrieveEventByExternalId(externalId);
        this.saveExternalIdEventInSession(externalId);

        return EVENT_MAPPER.eventToEventDTO(event);
    }

    private Event retrieveEventByExternalId(final String externalId) {
        return this.eventRepository.findByExternalId(externalId).orElseThrow(RuntimeException::new);
    }

    private void saveExternalIdEventInSession(final String externalId) {
        httpSession.setAttribute("externalIdEvent", externalId);
    }
}

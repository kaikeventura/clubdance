package com.kaikeventura.clubdance.domain.event.service;

import com.kaikeventura.clubdance.domain.event.infra.dto.EventDTO;
import com.kaikeventura.clubdance.domain.event.infra.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kaikeventura.clubdance.domain.event.infra.converter.EventMapper.EVENT_MAPPER;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDTO> allActiveEvents() {
        var activeEventGroup = this.eventRepository.findAllByActiveTrue();

        return EVENT_MAPPER.eventGroupToEventDTOGroup(activeEventGroup);
    }

    public void saveNewEvent(final EventDTO eventDTO) {
        var event = EVENT_MAPPER.eventDTOToEvent(eventDTO);
        this.eventRepository.save(event);
    }
}

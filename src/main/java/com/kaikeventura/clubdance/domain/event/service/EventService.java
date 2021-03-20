package com.kaikeventura.clubdance.domain.event.service;

import com.kaikeventura.clubdance.domain.event.infra.converter.EventMapper;
import com.kaikeventura.clubdance.domain.event.infra.dto.EventDTO;
import com.kaikeventura.clubdance.domain.event.infra.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<EventDTO> allActiveEvents() {
        var eventGroup = eventRepository.findAll();

        return EventMapper.EVENT_MAPPER.eventGroupToEventDTOGroup(eventGroup);
    }
}

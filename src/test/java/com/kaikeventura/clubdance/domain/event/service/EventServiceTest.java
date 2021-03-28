package com.kaikeventura.clubdance.domain.event.service;

import com.kaikeventura.clubdance.domain.event.entity.Event;
import com.kaikeventura.clubdance.domain.event.infra.repository.EventRepository;
import com.kaikeventura.clubdance.factory.EventFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static com.kaikeventura.clubdance.domain.event.infra.converter.EventMapper.EVENT_MAPPER;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void mustReturn_a_listOfAllRegisteredEvents_and_thatTheyAreActive() {
        var event = EventFactory.any().build();
        var expectedEventDTOGroup = EVENT_MAPPER.eventGroupToEventDTOGroup(singletonList(event));

        when(this.eventRepository.findAllByActiveTrue()).thenReturn(singletonList(event));

        var actualEventDTOGroup = this.eventService.allActiveEvents();

        assertThat(actualEventDTOGroup).usingRecursiveComparison().isEqualTo(expectedEventDTOGroup);
    }

    @Test
    void mustSave_a_newEvent_and_returnAnEventDTO() {
        var event = EventFactory.any().build();
        var expectedEventDTO = EVENT_MAPPER.eventToEventDTO(event);

        this.eventService.saveNewEvent(expectedEventDTO);

        verify(this.eventRepository, times(1)).save(any(Event.class));
    }

    @Test
    void mustRetrieve_anEventViaTheExternalId_and_returnAnEventDTO() {
        var event = EventFactory.any().build();
        var expectedEventDTO = EVENT_MAPPER.eventToEventDTO(event);

        when(this.eventRepository.findByExternalId(anyString())).thenReturn(Optional.of(event));

        var actualEventDTO = this.eventService.findEventByExternalId("fdd07425-a805-4b1a-a17e-19515a792248");

        assertThat(actualEventDTO).usingRecursiveComparison().isEqualTo(expectedEventDTO);
    }

    @Test
    void shouldWhenTryingToRetrieve_an_eventViaTheExternalId_throwRuntimeException_when_theEventIsNotFound() {
        when(this.eventRepository.findByExternalId(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> {
            this.eventService.findEventByExternalId("fdd07425-a805-4b1a-a17e-19515a792248");
        });
    }

    @Test
    void mustUpdate_the_eventWithTheNewDataReceived_and_returnAnEventDTO() {
        var event = EventFactory.any().build();
        var eventDTO = EVENT_MAPPER.eventToEventDTO(EventFactory.any().cabinCapacity(20).build());

        when(this.eventRepository.findByExternalId(anyString())).thenReturn(Optional.of(event));

        this.eventService.updateEvent(eventDTO);

        verify(this.eventRepository, times(1)).findByExternalId(eventDTO.getExternalId());
        verify(this.eventRepository, times(1)).save(event);
    }
}
package com.kaikeventura.clubdance.domain.event.infra.converter;

import com.kaikeventura.clubdance.domain.event.entity.Event;
import com.kaikeventura.clubdance.domain.event.infra.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, UUID.class})
public abstract class EventMapper {

    public static final EventMapper EVENT_MAPPER = Mappers.getMapper(EventMapper.class);

    public abstract List<EventDTO> eventGroupToEventDTOGroup(List<Event> eventGroup);

    @Mapping(target = "active", constant = "true")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "externalId", expression = "java(UUID.randomUUID().toString())")
    public abstract Event eventDTOToEvent(EventDTO eventDTO);

    public abstract EventDTO eventToEventDTO(Event event);
}

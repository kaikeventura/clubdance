package com.kaikeventura.clubdance.domain.event.infra.converter;

import com.kaikeventura.clubdance.domain.event.entity.Event;
import com.kaikeventura.clubdance.domain.event.infra.dto.EventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class EventMapper {

    public static final EventMapper EVENT_MAPPER = Mappers.getMapper(EventMapper.class);

    public abstract List<EventDTO> eventGroupToEventDTOGroup(List<Event> eventGroup);
}

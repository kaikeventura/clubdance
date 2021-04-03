package com.kaikeventura.clubdance.domain.ticket.infra.dto;

import com.kaikeventura.clubdance.domain.ticket.infra.handler.TicketAvailabilityHandler;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TicketType {
    NORMAL("Normal", TicketAvailabilityHandler.NORMAL_AND_VIP),
    VIP("Vip", TicketAvailabilityHandler.NORMAL_AND_VIP),
    CABIN("Camarote", TicketAvailabilityHandler.CABIN);

    public final String description;
    public final TicketAvailabilityHandler ticketTypeHandler;
}

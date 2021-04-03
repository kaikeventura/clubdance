package com.kaikeventura.clubdance.domain.ticket.infra.handler;

import com.kaikeventura.clubdance.domain.event.entity.Event;
import com.kaikeventura.clubdance.domain.ticket.exception.TicketSoldOutException;

public enum TicketAvailabilityHandler {
    NORMAL_AND_VIP {
        @Override
        public void removeTicketFromAvailableQuantity(Event event) {
            this.checkTicketAvailability(event.getCapacity());
            event.removesATicketFromNormalAndNipCapacity();
        }
    },

    CABIN {
        @Override
        public void removeTicketFromAvailableQuantity(Event event) {
            this.checkTicketAvailability(event.getCapacity());
            event.removesATicketFromCabinCapacity();
        }
    };

    public abstract void removeTicketFromAvailableQuantity(Event event);

    void checkTicketAvailability(Integer currentCapacity) {
        if (currentCapacity == 0) {
            throw new TicketSoldOutException();
        }
    }
}

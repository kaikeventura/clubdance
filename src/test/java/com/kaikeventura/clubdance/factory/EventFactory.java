package com.kaikeventura.clubdance.factory;

import com.kaikeventura.clubdance.domain.event.entity.Event;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventFactory {

    public static EventBuilder.EventBuilderBuilder any() {
        return EventBuilder
                .builder()
                .externalId("fdd07425-a805-4b1a-a17e-19515a792248")
                .name("Caqui Party Evolution")
                .place("Club Dance Space Mix 1")
                .date(LocalDate.parse("2021-05-01"))
                .startTime(LocalTime.parse("22:00:00"))
                .endTime(LocalTime.parse("05:00:00"))
                .capacity(500)
                .cabinCapacity(20)
                .normalTicketPrice(BigDecimal.valueOf(50))
                .vipTicketPrice(BigDecimal.valueOf(80))
                .cabinTicketPrice(BigDecimal.valueOf(150))
                .active(true)
                .createdAt(LocalDateTime.parse("2021-03-27T21:41:29.710969820"));
    }

    public static class EventBuilder extends Event {

        @Builder
        public EventBuilder(
                String externalId,
                String name,
                String place,
                LocalDate date,
                LocalTime startTime,
                LocalTime endTime,
                Integer capacity,
                Integer cabinCapacity,
                BigDecimal normalTicketPrice,
                BigDecimal vipTicketPrice,
                BigDecimal cabinTicketPrice,
                Boolean active,
                LocalDateTime createdAt
        ) {
            super(externalId, name, place, date, startTime, endTime, capacity, cabinCapacity, normalTicketPrice, vipTicketPrice, cabinTicketPrice, active, createdAt);
        }
    }

}

package com.kaikeventura.clubdance.domain.event.infra.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Setter
@Getter
public class EventDTO implements Serializable {

    private String externalId;

    private String name;

    private String place;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;

    private Integer capacity;

    private Integer cabinCapacity;

    private BigDecimal normalTicketPrice;

    private BigDecimal vipTicketPrice;

    private BigDecimal cabinTicketPrice;

    public EventDTO(
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
            BigDecimal cabinTicketPrice
    ) {
        this.externalId = externalId;
        this.name = name;
        this.place = place;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.cabinCapacity = cabinCapacity;
        this.normalTicketPrice = normalTicketPrice;
        this.vipTicketPrice = vipTicketPrice;
        this.cabinTicketPrice = cabinTicketPrice;
    }

}

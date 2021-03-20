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

}

package com.kaikeventura.clubdance.domain.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Column(nullable = false, unique = true, length = 36)
    private String externalId;

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String place;

    @Getter
    @Column(nullable = false)
    private LocalDate date;

    @Getter
    @Column(nullable = false)
    private LocalTime startTime;

    @Getter
    @Column(nullable = false)
    private LocalTime endTime;

    @Getter
    @Column(nullable = false)
    private Integer capacity;

    @Getter
    @Column(nullable = false)
    private Integer cabinCapacity;

    @Getter
    @Column(nullable = false)
    private BigDecimal normalTicketPrice;

    @Getter
    @Column(nullable = false)
    private BigDecimal vipTicketPrice;

    @Getter
    @Column(nullable = false)
    private BigDecimal cabinTicketPrice;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Event(
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
        this.externalId = UUID.randomUUID().toString();
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
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }
}

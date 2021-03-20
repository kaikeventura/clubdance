package com.kaikeventura.clubdance.domain.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Setter
    @Column(nullable = false, unique = true, length = 36)
    private String externalId;

    @Getter
    @Setter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(nullable = false)
    private String place;

    @Getter
    @Setter
    @Column(nullable = false)
    private LocalDate date;

    @Getter
    @Setter
    @Column(nullable = false)
    private LocalTime startTime;

    @Getter
    @Setter
    @Column(nullable = false)
    private LocalTime endTime;

    @Getter
    @Setter
    @Column(nullable = false)
    private Integer capacity;

    @Getter
    @Setter
    @Column(nullable = false)
    private Integer cabinCapacity;

    @Getter
    @Setter
    @Column(nullable = false)
    private BigDecimal normalTicketPrice;

    @Getter
    @Setter
    @Column(nullable = false)
    private BigDecimal vipTicketPrice;

    @Getter
    @Setter
    @Column(nullable = false)
    private BigDecimal cabinTicketPrice;

    @Column(nullable = false)
    @Setter
    private Boolean active;

    @Column(nullable = false)
    @Setter
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

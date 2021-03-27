package com.kaikeventura.clubdance.domain.event.infra.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Setter
@Getter
public class EventDTO implements Serializable {

    private String externalId;

    @NotBlank(message = "EVENT_ERROR_001")
    private String name;

    @NotBlank(message = "EVENT_ERROR_002")
    private String place;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "EVENT_ERROR_003")
    @FutureOrPresent(message = "EVENT_ERROR_004")
    private LocalDate date;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull(message = "EVENT_ERROR_005")
    @FutureOrPresent(message = "EVENT_ERROR_006")
    private LocalTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull(message = "EVENT_ERROR_007")
    @FutureOrPresent(message = "EVENT_ERROR_008")
    private LocalTime endTime;

    @Positive(message = "EVENT_ERROR_009")
    private Integer capacity;

    @Positive(message = "EVENT_ERROR_010")
    private Integer cabinCapacity;

    @NotNull(message = "EVENT_ERROR_011")
    @Positive(message = "EVENT_ERROR_012")
    private BigDecimal normalTicketPrice;

    @NotNull(message = "EVENT_ERROR_013")
    @Positive(message = "EVENT_ERROR_014")
    private BigDecimal vipTicketPrice;

    @NotNull(message = "EVENT_ERROR_015")
    @Positive(message = "EVENT_ERROR_016")
    private BigDecimal cabinTicketPrice;

}

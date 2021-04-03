package com.kaikeventura.clubdance.domain.ticket.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal totalValue;

    @Column(nullable = false)
    private boolean isPaid;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;

    public Account(Ticket ticket) {
        this.isPaid = false;
        this.createdAt = LocalDateTime.now();
        this.ticket = ticket;
    }
}

package com.kaikeventura.clubdance.domain.ticket.entity;

import com.kaikeventura.clubdance.domain.event.entity.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 60)
    private String clientName;

    @Getter
    @Column(nullable = false, length = 20)
    private String clientCpf;

    @Column(nullable = false)
    private LocalDate clientDateOfBirth;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "ticket")
    private Account account;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    public Ticket(String clientName, String clientCpf, LocalDate clientDateOfBirth, Event event) {
        this.clientName = clientName;
        this.clientCpf = clientCpf;
        this.clientDateOfBirth = clientDateOfBirth;
        this.createdAt = LocalDateTime.now();
        this.event = event;
    }
}

package com.kaikeventura.clubdance.domain.ticket.infra.repository;

import com.kaikeventura.clubdance.domain.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByEventExternalId(String externalId);
}

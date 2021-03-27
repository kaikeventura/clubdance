package com.kaikeventura.clubdance.domain.event.infra.repository;

import com.kaikeventura.clubdance.domain.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findAllByActiveTrue();
    Optional<Event> findByExternalId(String externalId);
}

package com.kaikeventura.clubdance.domain.ticket.infra.repository;

import com.kaikeventura.clubdance.domain.ticket.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}

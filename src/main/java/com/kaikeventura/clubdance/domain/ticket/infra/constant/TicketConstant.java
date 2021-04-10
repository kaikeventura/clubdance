package com.kaikeventura.clubdance.domain.ticket.infra.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TicketConstant {
    LESS_AGENT_CLIENT("O cliente é menor de idade"),
    CPF_HAS_A_TICKET("Já existe um ingresso com esse cpf no evento"),
    TICKET_SOLD_OUT("Ingresso esgotado"),
    TICKET_SOLD("Ingresso vendido com sucesso");

    public final String message;
}

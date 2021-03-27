package com.kaikeventura.clubdance.domain.event.infra.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EventConstant {
    NEW_SAVED_EVENT("Evento salvo com sucesso"),
    UPDATED_EVENT("Evento atualizado com sucesso");

    public final String message;
}

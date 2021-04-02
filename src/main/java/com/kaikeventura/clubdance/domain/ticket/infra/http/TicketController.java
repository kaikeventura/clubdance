package com.kaikeventura.clubdance.domain.ticket.infra.http;

import com.kaikeventura.clubdance.domain.event.infra.dto.EventDTO;
import com.kaikeventura.clubdance.domain.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("{externalId}")
    public ModelAndView homeTickets(@PathVariable("externalId") final String externalId) {
        var eventDTO = ticketService.findEventByExternalId(externalId);

        return new ModelAndView(
                "pages/ticket/ticket-home",
                Map.of(
                        "eventDTO", eventDTO
                ),
                HttpStatus.OK
        );
    }
}

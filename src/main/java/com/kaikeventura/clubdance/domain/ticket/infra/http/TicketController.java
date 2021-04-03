package com.kaikeventura.clubdance.domain.ticket.infra.http;

import com.kaikeventura.clubdance.domain.ticket.exception.ExistingCpfForThisEventException;
import com.kaikeventura.clubdance.domain.ticket.exception.TicketSoldOutException;
import com.kaikeventura.clubdance.domain.ticket.exception.UnderageClientException;
import com.kaikeventura.clubdance.domain.ticket.infra.dto.TicketDTO;
import com.kaikeventura.clubdance.domain.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

import static com.kaikeventura.clubdance.domain.ticket.infra.constant.TicketConstant.*;

@Controller
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private static final String REDIRECT_HOME_TICKET = "redirect:/ticket/";
    private static final String ERROR = "error";

    private final TicketService ticketService;

    @GetMapping("{externalId}")
    public ModelAndView homeTickets(@PathVariable("externalId") final String externalId) {
        var eventDTO = ticketService.findEventByExternalId(externalId);

        return new ModelAndView(
                "pages/ticket/ticket-home",
                Map.of(
                        "eventDTO", eventDTO,
                        "ticketDTO", new TicketDTO()
                ),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ModelAndView sellTicket(@Valid TicketDTO ticketDTO) {
        var externalIdEvent = ticketService.retrieveExternalIdEvent();

        try {
            ticketService.sellTicket(ticketDTO);
        }
        catch (ExistingCpfForThisEventException exception) {
            return new ModelAndView(REDIRECT_HOME_TICKET + externalIdEvent, Map.of(ERROR, CPF_HAS_A_TICKET.message));
        }
        catch (UnderageClientException exception) {
            return new ModelAndView(REDIRECT_HOME_TICKET + externalIdEvent, Map.of(ERROR, LESS_AGENT_CLIENT.message));
        }
        catch (TicketSoldOutException exception) {
            return new ModelAndView(REDIRECT_HOME_TICKET + externalIdEvent, Map.of(ERROR, TICKET_SOLD_OUT.message));
        }

        return new ModelAndView(REDIRECT_HOME_TICKET + externalIdEvent);
    }
}

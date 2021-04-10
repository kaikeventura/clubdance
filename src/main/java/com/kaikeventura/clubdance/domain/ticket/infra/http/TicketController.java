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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

import static com.kaikeventura.clubdance.domain.ticket.infra.constant.TicketConstant.*;

@Controller
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private static final String REDIRECT_HOME_TICKET = "redirect:/ticket/";
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";

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
    public ModelAndView sellTicket(@Valid TicketDTO ticketDTO, RedirectAttributes redirectAttributes) {
        var externalIdEvent = ticketService.retrieveExternalIdEvent();

        try {
            ticketService.sellTicket(ticketDTO);
        }
        catch (ExistingCpfForThisEventException exception) {
            redirectAttributes.addFlashAttribute(ERROR, CPF_HAS_A_TICKET.message);
            return new ModelAndView(REDIRECT_HOME_TICKET + externalIdEvent, HttpStatus.FOUND);
        }
        catch (UnderageClientException exception) {
            redirectAttributes.addFlashAttribute(ERROR, LESS_AGENT_CLIENT.message);
            return new ModelAndView(REDIRECT_HOME_TICKET + externalIdEvent, HttpStatus.FOUND);
        }
        catch (TicketSoldOutException exception) {
            redirectAttributes.addFlashAttribute(ERROR, TICKET_SOLD_OUT.message);
            return new ModelAndView(REDIRECT_HOME_TICKET + externalIdEvent, HttpStatus.FOUND);
        }

        redirectAttributes.addFlashAttribute(SUCCESS, TICKET_SOLD.message);

        return new ModelAndView(REDIRECT_HOME_TICKET + externalIdEvent, HttpStatus.FOUND);
    }
}

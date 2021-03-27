package com.kaikeventura.clubdance.domain.event.infra.http;

import com.kaikeventura.clubdance.domain.event.infra.dto.EventDTO;
import com.kaikeventura.clubdance.domain.event.service.EventService;
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

import static com.kaikeventura.clubdance.domain.event.infra.constant.EventConstant.*;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ModelAndView homeEvents() {
        return new ModelAndView(
                "pages/event/event-home",
                Map.of("activeEventGroup", this.eventService.allActiveEvents()),
                HttpStatus.OK
        );
    }

    @GetMapping("registration-form")
    public ModelAndView eventRegistration() {
        return new ModelAndView(
                "pages/event/event-registration-form",
                Map.of("eventDTO", new EventDTO()),
                HttpStatus.OK
        );
    }

    @PostMapping("save")
    public ModelAndView saveANewEvent(@Valid final EventDTO eventDTO, final RedirectAttributes redirectAttributes) {
        this.eventService.saveNewEvent(eventDTO);
        redirectAttributes.addFlashAttribute("success", NEW_SAVED_EVENT.message);

        return new ModelAndView("redirect:/event/registration-form", HttpStatus.CREATED);
    }

    @GetMapping("load-event-data/{externalId}")
    public ModelAndView loadFormEventData(@PathVariable("externalId") final String externalId) {
        var eventDTO = this.eventService.findEventByExternalId(externalId);

        return new ModelAndView(
                "pages/event/event-registration-form",
                Map.of("eventDTO", eventDTO),
                HttpStatus.OK
        );
    }

    @PostMapping("update")
    public ModelAndView updateEvent(@Valid final EventDTO eventDTO, final RedirectAttributes redirectAttributes) {
        this.eventService.updateEvent(eventDTO);
        redirectAttributes.addFlashAttribute("success", UPDATED_EVENT.message);

        return new ModelAndView("redirect:/event/registration-form", HttpStatus.NO_CONTENT);
    }
}

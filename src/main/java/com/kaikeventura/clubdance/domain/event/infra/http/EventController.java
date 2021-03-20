package com.kaikeventura.clubdance.domain.event.infra.http;

import com.kaikeventura.clubdance.domain.event.infra.dto.EventDTO;
import com.kaikeventura.clubdance.domain.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ModelAndView homeEvents() {
        return new ModelAndView(
                "pages/event/event-home",
                Map.of("activeEventGroup", eventService.allActiveEvents()),
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
    public ModelAndView saveANewEvent(final EventDTO eventDTO) {

        return new ModelAndView("redirect:/event/registration-form", HttpStatus.CREATED);
    }
}

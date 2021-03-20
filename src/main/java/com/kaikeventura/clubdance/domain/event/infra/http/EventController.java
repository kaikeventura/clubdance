package com.kaikeventura.clubdance.domain.event.infra.http;

import com.kaikeventura.clubdance.domain.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ModelAndView homeEvents(final ModelMap model) {
        model.addAttribute("activeEventGroup", eventService.allActiveEvents());

        return new ModelAndView("pages/event/home-event", HttpStatus.OK);
    }
}

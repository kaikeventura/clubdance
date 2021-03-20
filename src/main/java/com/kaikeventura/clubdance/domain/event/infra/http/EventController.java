package com.kaikeventura.clubdance.domain.event.infra.http;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/event")
public class EventController {

    @GetMapping
    public ModelAndView homeEvents() {

        return new ModelAndView("pages/event/home-event", HttpStatus.OK);
    }
}

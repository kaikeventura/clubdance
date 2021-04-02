package com.kaikeventura.clubdance.domain.event.infra.http;

import com.kaikeventura.clubdance.domain.event.infra.dto.EventDTO;
import com.kaikeventura.clubdance.domain.event.service.EventService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.kaikeventura.clubdance.domain.event.infra.constant.EventConstant.NEW_SAVED_EVENT;
import static com.kaikeventura.clubdance.domain.event.infra.constant.EventConstant.UPDATED_EVENT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Test
    void mustReturnData_toRenderInitialScreenOfEvents() throws Exception {
        mockMvc
                .perform(get("/event"))
                .andExpect(view().name("pages/event/event-home"))
                .andExpect(model().attributeExists("activeEventGroup"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDisplayAScreen_withTheEventRegistrationForm() throws Exception {
        mockMvc
                .perform(get("/event/registration-form"))
                .andExpect(view().name("pages/event/event-registration-form"))
                .andExpect(model().attributeExists("eventDTO"))
                .andExpect(status().isOk());
    }

    @Test
    void mustSaveAnEvent_and_redirectToThePage_with_theRegistrationForm_andDisplayASuccessMessage() throws Exception {
        mockMvc
                .perform(
                        post("/event/save")
                                .param("name", "Evento 1")
                                .param("place", "Local 1")
                                .param("normalTicketPrice", "20.00")
                                .param("vipTicketPrice", "50.00")
                                .param("cabinTicketPrice", "150.00")
                                .param("date", LocalDate.now().toString())
                                .param("startTime", LocalTime.now().plusHours(1).toString())
                                .param("endTime", LocalTime.now().plusHours(6).toString())
                )
                .andExpect(view().name("redirect:/event/registration-form"))
                .andExpect(redirectedUrl("/event/registration-form"))
                .andExpect(MockMvcResultMatchers.flash().attribute("success", NEW_SAVED_EVENT.message))
                .andExpect(status().isFound());
    }

    @Test
    void mustDisplayThePage_with_theRegistrationFormFilled_with_theDataOfAnExistingEvent() throws Exception {
        var event = new EventDTO();
        event.setName("Evento 1");

        Mockito.when(eventService.findEventByExternalId(Mockito.anyString())).thenReturn(event);

        mockMvc
                .perform(
                        get("/event/load-event-data/{externalId}", "eff8261d-6fd1-49d7-92ec-3803be0b5418"))
                .andExpect(view().name("pages/event/event-registration-form"))
                .andExpect(model().attributeExists("eventDTO"))
                .andExpect(status().isOk());
    }

    @Test
    void mustUpdateAnEvent_and_redirectToThePage_with_theRegistrationForm_andDisplayASuccessMessage() throws Exception {
        mockMvc
                .perform(
                        post("/event/update")
                                .param("name", "Evento 2")
                                .param("place", "Local 2")
                                .param("normalTicketPrice", "20.00")
                                .param("vipTicketPrice", "50.00")
                                .param("cabinTicketPrice", "150.00")
                                .param("date", LocalDate.now().toString())
                                .param("startTime", LocalTime.now().plusHours(1).toString())
                                .param("endTime", LocalTime.now().plusHours(6).toString())
                )
                .andExpect(view().name("redirect:/event/registration-form"))
                .andExpect(redirectedUrl("/event/registration-form"))
                .andExpect(MockMvcResultMatchers.flash().attribute("success", UPDATED_EVENT.message))
                .andExpect(status().isFound());
    }

}
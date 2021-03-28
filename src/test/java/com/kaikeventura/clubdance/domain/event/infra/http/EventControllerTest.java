package com.kaikeventura.clubdance.domain.event.infra.http;

import com.kaikeventura.clubdance.domain.event.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventService eventService;

    @Test
    void mustReturnData_toRenderInitialScreenOfEvents() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/event")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
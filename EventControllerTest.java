package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.Event;
import com.agripure.agripurebackend.entities.User;
import com.agripure.agripurebackend.service.impl.EventServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EventController.class)
@ActiveProfiles("test")
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EventServiceImpl eventService;
    private List<Event> eventList;

    @BeforeEach
    void setUp() {
        eventList = new ArrayList<>();
        eventList.add(new Event(1L, LocalDate.parse("2022-11-01"), "Regar la parcela de lechugas", new User()));
        eventList.add(new Event(2L, LocalDate.parse("2022-11-01"), "Regar la parcela de papas", new User()));
        eventList.add(new Event(3L, LocalDate.parse("2022-11-18"), "Fertilizar la parcela de tomates", new User()));
        eventList.add(new Event(3L, LocalDate.parse("2022-11-18"), "Fumigar la parcela de camotes", new User()));
    }
    @Test
    void findAllByDateTest() throws Exception {
        LocalDate date = LocalDate.parse("2022-11-01");

        given(eventService.findAllByDate(date)).willReturn(
                //lista filtrada por la fecha 2022-11-01
                eventList.stream()
                        .filter(e->e.getDate().equals(date))
                        .collect(Collectors.toList()));

        mockMvc.perform(get("/api/events?date={date}", date))
                .andExpect(status().isOk());
    }
}

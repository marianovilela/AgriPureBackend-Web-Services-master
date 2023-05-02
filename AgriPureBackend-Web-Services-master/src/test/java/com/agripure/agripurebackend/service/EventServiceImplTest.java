package com.agripure.agripurebackend.service;

import com.agripure.agripurebackend.entities.Event;
import com.agripure.agripurebackend.entities.User;
import com.agripure.agripurebackend.repository.IEventRepository;
import com.agripure.agripurebackend.service.impl.EventServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EventServiceImplTest {
    @Mock
    private IEventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    public void saveTest() {
        LocalDate date = LocalDate.parse("2022-11-01");
        Event event = new Event(1L, date, "Regar la parcela de lechugas", new User());

        given(eventRepository.save(event)).willReturn(event);

        Event savedEvent = null;
        try {
            savedEvent = eventService.save(event);
        } catch (Exception e) {
        }
        assertThat(savedEvent).isNotNull();
        assertEquals(event, savedEvent);

    }

    @Test
    public void deleteTest() throws Exception {
        Long id = 1L;
        eventService.delete(id);
        verify(eventRepository, times(1)).deleteById(id);
    }

    @Test
    public void findAllByDate() throws Exception {
        LocalDate date = LocalDate.parse("2022-11-01");
        List<Event> events = new ArrayList<>();
        events.add(new Event(1L, date, "Regar la parcela de lechugas", new User()));
        events.add(new Event(2L, date, "Regar la parcela de papas", new User()));
        events.add(new Event(3L, date, "Regar la parcela de tomates", new User()));

        given(eventRepository.findAllByDate(date)).willReturn(events);

        List<Event> eventsExpected = eventService.findAllByDate(date);
        assertEquals(eventsExpected, events);
    }
}

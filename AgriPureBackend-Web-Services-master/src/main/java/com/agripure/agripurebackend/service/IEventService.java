package com.agripure.agripurebackend.service;

import com.agripure.agripurebackend.entities.Event;

import java.time.LocalDate;
import java.util.List;

public interface IEventService extends CrudService<Event> {
    List<Event> findAllByDate(LocalDate date) throws Exception;
}
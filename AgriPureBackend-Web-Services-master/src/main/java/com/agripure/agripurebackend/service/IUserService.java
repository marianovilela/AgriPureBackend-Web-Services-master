package com.agripure.agripurebackend.service;


import com.agripure.agripurebackend.entities.Event;
import com.agripure.agripurebackend.entities.Plant;
import com.agripure.agripurebackend.entities.Plot;
import com.agripure.agripurebackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService extends CrudService<User>{
    Optional<User> findByEmail(String email) throws Exception;
    List<User> findByPremium(Boolean premium) throws Exception;
    List<Plant> getPlantsByUserId(Long id) throws Exception;
    List<Event> getEventsByUserId(Long id) throws Exception;
    List<Plot> getPlotsByUserId(Long id) throws Exception;
}

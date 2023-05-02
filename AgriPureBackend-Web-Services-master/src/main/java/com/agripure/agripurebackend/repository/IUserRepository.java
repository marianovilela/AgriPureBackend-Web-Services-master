package com.agripure.agripurebackend.repository;

import com.agripure.agripurebackend.entities.Event;
import com.agripure.agripurebackend.entities.Plant;
import com.agripure.agripurebackend.entities.Plot;
import com.agripure.agripurebackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.premium=TRUE")
    List<User> findByPremium(Boolean premium);
    @Query("SELECT u.plants FROM User u WHERE u.id = ?1")
    List<Plant> getPlantsByUserId(Long id);

    @Query("SELECT u.events FROM User u WHERE u.id = ?1")
    List<Event> getEventsByUserId(Long id);
    @Query("SELECT u.plots FROM User u WHERE u.id = ?1")
    List<Plot> getPlotsByUserId(Long id);
}
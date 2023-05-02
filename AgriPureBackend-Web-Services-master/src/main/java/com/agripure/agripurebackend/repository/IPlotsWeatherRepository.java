package com.agripure.agripurebackend.repository;

import com.agripure.agripurebackend.entities.PlotsWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPlotsWeatherRepository extends JpaRepository<PlotsWeather, Long> {
}

package com.agripure.agripurebackend.service.impl;

import com.agripure.agripurebackend.entities.PlotsWeather;
import com.agripure.agripurebackend.repository.IPlotsWeatherRepository;
import com.agripure.agripurebackend.service.IPlotsWeatherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PlotsWeatherServiceImpl implements IPlotsWeatherService {
    private final IPlotsWeatherRepository plotsWeatherRepository;

    public PlotsWeatherServiceImpl(IPlotsWeatherRepository plotsWeatherRepository) {
        this.plotsWeatherRepository = plotsWeatherRepository;
    }

    @Override
    @Transactional
    public PlotsWeather save(PlotsWeather plotsWeather) throws Exception {
        return plotsWeatherRepository.save(plotsWeather);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        plotsWeatherRepository.deleteById(id);
    }

    @Override
    public List<PlotsWeather> getAll() throws Exception {
        return plotsWeatherRepository.findAll();
    }

    @Override
    public Optional<PlotsWeather> getById(Long id) throws Exception {
        return plotsWeatherRepository.findById(id);
    }
}

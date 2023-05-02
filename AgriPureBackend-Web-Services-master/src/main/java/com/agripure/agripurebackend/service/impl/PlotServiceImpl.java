package com.agripure.agripurebackend.service.impl;

import com.agripure.agripurebackend.entities.Plot;
import com.agripure.agripurebackend.repository.IPlotRepository;
import com.agripure.agripurebackend.service.IPlotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PlotServiceImpl implements IPlotService {
    private final IPlotRepository plotRepository;

    public PlotServiceImpl(IPlotRepository plotRepository) {
        this.plotRepository = plotRepository;
    }

    @Override
    @Transactional
    public Plot save(Plot plot) throws Exception {
        return plotRepository.save(plot);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        plotRepository.deleteById(id);
    }

    @Override
    public List<Plot> getAll() throws Exception {
        return plotRepository.findAll();
    }

    @Override
    public Optional<Plot> getById(Long id) throws Exception {
        return plotRepository.findById(id);
    }
}

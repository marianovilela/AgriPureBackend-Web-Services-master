package com.agripure.agripurebackend.service.impl;

import com.agripure.agripurebackend.entities.Plant;
import com.agripure.agripurebackend.repository.IPlantRepository;
import com.agripure.agripurebackend.service.IPlantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PlantServiceImpl implements IPlantService {

    private final IPlantRepository plantRepository;

    public PlantServiceImpl(IPlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    @Transactional
    public Plant save(Plant plant) throws Exception {
        return plantRepository.save(plant);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        plantRepository.deleteById(id);
    }

    @Override
    public List<Plant> getAll() throws Exception {
        return plantRepository.findAll();
    }

    @Override
    public Optional<Plant> getById(Long id) throws Exception {
        return plantRepository.findById(id);
    }

    @Override
    public Plant findByName(String name) throws Exception {
        return plantRepository.findByName(name);
    }
}

package com.agripure.agripurebackend.service;

import com.agripure.agripurebackend.entities.Plant;

public interface IPlantService extends CrudService<Plant>{

    Plant findByName(String name) throws Exception;
}

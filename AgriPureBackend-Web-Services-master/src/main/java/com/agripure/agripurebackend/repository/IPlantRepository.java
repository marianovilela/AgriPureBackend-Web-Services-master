package com.agripure.agripurebackend.repository;

import com.agripure.agripurebackend.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlantRepository extends JpaRepository<Plant, Long> {

    Plant findByName(String name);
}

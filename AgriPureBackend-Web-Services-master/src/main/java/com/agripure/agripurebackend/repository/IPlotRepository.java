package com.agripure.agripurebackend.repository;

import com.agripure.agripurebackend.entities.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlotRepository extends JpaRepository<Plot, Long> {
}
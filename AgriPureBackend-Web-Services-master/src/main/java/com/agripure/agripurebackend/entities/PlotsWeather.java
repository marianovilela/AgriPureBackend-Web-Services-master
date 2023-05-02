package com.agripure.agripurebackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "plots_weather")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlotsWeather implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "wind", nullable = false)
    public Integer wind;
    @Column(name = "humidity", nullable = false)
    public Integer humidity;
    @Column(name = "pressure", nullable = false)
    public Long pressure;
    @Column(name = "thermal", nullable = false)
    public Integer thermal;
    @Column(name = "visibility", nullable = false)
    public Integer visibility;
    @Column(name = "dew_point", nullable = false)
    public Integer dewPoint;
}

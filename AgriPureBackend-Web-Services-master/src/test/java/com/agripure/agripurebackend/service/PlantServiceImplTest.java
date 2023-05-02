package com.agripure.agripurebackend.service;

import com.agripure.agripurebackend.entities.Plant;
import com.agripure.agripurebackend.repository.IPlantRepository;
import com.agripure.agripurebackend.service.impl.PlantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PlantServiceImplTest {
    @Mock
    private IPlantRepository plantRepository;

    @InjectMocks
    private PlantServiceImpl plantService;

    @Test
    public void saveTest(){
        Plant plant = new Plant(1L, "Tomato", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 5.5F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,3, new ArrayList<>());
        given(plantRepository.save(plant)).willReturn(plant);

        Plant savePlant = null;
        try {
            savePlant = plantService.save(plant);
        }catch (Exception e){

        }

        assertThat(savePlant).isNotNull();
        verify(plantRepository).save(any(Plant.class));
    }

    @Test
    public void deleteTest() throws Exception{
        Long id = 1L;
        plantService.delete(id);
        verify(plantRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception{
        List<Plant> list = new ArrayList<>();
        list.add(new Plant(1L, "Tomato", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 5.5F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,3, new ArrayList<>()));
        list.add(new Plant(2L, "Cualiflower", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 5.5F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,1, new ArrayList<>()));
        list.add(new Plant(3L, "Potatoe", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                true, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 5.5F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,1, new ArrayList<>()));
        list.add(new Plant(4L, "Lemon", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 4F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,4, new ArrayList<>()));

        given(plantRepository.findAll()).willReturn(list);
        List<Plant> listExpected = plantService.getAll();
        assertEquals(listExpected, list);
    }

    @Test
    public void getById() throws Exception{
        Long id = 1L;
        Plant plant = new Plant(1L, "Lemon", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 4F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,4, new ArrayList<>());

        given(plantRepository.findById(id)).willReturn(Optional.of(plant));
        Optional<Plant> plantExpected = plantService.getById(id);

        assertThat(plantExpected).isNotNull();
        assertEquals(plantExpected, Optional.of(plant));
    }

    @Test
    public void findByName() throws Exception{
        String name = "Lemon";
        Plant plant = new Plant(1L, "Lemon", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 4F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,4, new ArrayList<>());

        given(plantRepository.findByName(name)).willReturn(plant);
        Plant plantExpected = plantService.findByName(name);
        assertThat(plantExpected).isNotNull();
        assertEquals(plantExpected, plant);
    }
}

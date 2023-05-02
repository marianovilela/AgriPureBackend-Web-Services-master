package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.Plant;
import com.agripure.agripurebackend.service.impl.PlantServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PlantController.class)
@ActiveProfiles("test")
public class PlantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlantServiceImpl plantService;

    private List<Plant> plantList;

    @BeforeEach
    void setUp(){
        plantList = new ArrayList<>();
        plantList.add(new Plant(1L, "Tomato", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 5.5F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,3, new ArrayList<>()));
        plantList.add(new Plant(2L, "Cualiflower", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 5.5F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,1, new ArrayList<>()));
        plantList.add(new Plant(3L, "Potatoe", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                true, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 5.5F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,1, new ArrayList<>()));
        plantList.add(new Plant(4L, "Lemon", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 4F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,4, new ArrayList<>()));
    }

    @Test
    void findAllPlants() throws Exception{
        given(plantService.getAll()).willReturn(plantList);
        mockMvc.perform(get("/api/plants")).andExpect(status().isOk());
    }

    @Test
    void insertPlantTest() throws Exception{
        Plant plant = new Plant(1L, "Tomato", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 5.5F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,3, new ArrayList<>());
        given(plantService.save(plant)).willReturn(plant);
        mockMvc.perform(post("/api/plants", plant).content(asJsonString(plant))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    void updatePlantTest() throws Exception{
        Long id = 1L;
        Plant plant = new Plant(1L, "Tomato", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 5.5F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,3, new ArrayList<>());
        given(plantService.getById(id)).willReturn(Optional.of(plant));
        mockMvc.perform(put("/api/plants/{id}", id).content(asJsonString(plant))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    void deletePlantTest() throws Exception{
        Long id = 1L;
        Plant plant = new Plant(1L, "Tomato", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg",
                false, "Brassica oleracea var. botrytis", "Brassica oleracea",
                "information about the land type", 5.5F, "information about distance between plants",
                "40-60 cm", "information about ideal depth", "0.5-1.5 m",
                "Information about the weather conditions", "15°-18° C",
                "information about fertilization and fumigation", 2,3, new ArrayList<>());
        given(plantService.getById(id)).willReturn(Optional.of(plant));
        mockMvc.perform(delete("/api/plants/{id}", id))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj){
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.Plot;
import com.agripure.agripurebackend.service.impl.PlotServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(controllers = PlotController.class)
@ActiveProfiles("test")
public class PlotControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlotServiceImpl plotService;

    private List<Plot> plotList;

    @BeforeEach
    void setUp() {
        plotList = new ArrayList<>();
        plotList.add();
    }
}

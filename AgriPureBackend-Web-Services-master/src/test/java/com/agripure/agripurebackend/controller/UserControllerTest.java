package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.User;
import com.agripure.agripurebackend.service.impl.UserServiceImpl;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EventController.class)
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        userList = new ArrayList<>();
        userList.add(new User(1L, "admin123", "example123@gmail.com", "admin123", true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        userList.add(new User(2L, "admin456", "example456@gmail.com", "admin456", false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        userList.add(new User(3L, "admin789", "example789@gmail.com", "admin789", false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        userList.add(new User(4L, "admin000", "example000@gmail.com", "admin000", true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    @Test
    void findAllTest() throws Exception {
        given(userService.getAll()).willReturn(userList);
        mockMvc.perform(get("/api/users")).andExpect(status().isOk());
    }

    @Test
    void insertUserTest() throws Exception {
        User user = new User(100L, "admin", "example@gmail.com", "admin", false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        given(userService.save(user)).willReturn(user);
        mockMvc.perform(post("/api/users", user).content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

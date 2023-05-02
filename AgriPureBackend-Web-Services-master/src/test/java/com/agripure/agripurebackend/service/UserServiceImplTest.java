package com.agripure.agripurebackend.service;

import com.agripure.agripurebackend.entities.User;
import com.agripure.agripurebackend.repository.IUserRepository;
import com.agripure.agripurebackend.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private IUserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void saveTest() {
        User user = new User(1L, "example", "example123@gmail.com", "example", true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        given(userRepository.save(user)).willReturn(user);

        User savedUser = null;
        try {
            savedUser = userService.save(user);
        } catch(Exception e) {
        }
        assertThat(savedUser).isNotNull();
        assertEquals(user, savedUser);
    }

    @Test
    public void deleteTest() throws Exception {
        Long id = 1L;
        userService.delete(id);
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "admin123", "example123@gmail.com", "admin123", true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        users.add(new User(2L, "admin456", "example456@gmail.com", "admin456", false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        users.add(new User(3L, "admin789", "example789@gmail.com", "admin789", false, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        given(userRepository.findAll()).willReturn(users);
        List<User> usersExpected = userService.getAll();
        assertEquals(usersExpected, users);
    }
}

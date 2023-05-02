package com.agripure.agripurebackend.controller;

import com.agripure.agripurebackend.entities.Event;
import com.agripure.agripurebackend.entities.Plant;
import com.agripure.agripurebackend.entities.Plot;
import com.agripure.agripurebackend.entities.User;
import com.agripure.agripurebackend.service.IPlantService;
import com.agripure.agripurebackend.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
@Api(tags = "Users", value = "Web Service RESTful - Users")
public class UserController {
    private IUserService userService;
    private IPlantService plantService;

    public UserController(IUserService userService, IPlantService plantService) {
        this.userService = userService;
        this.plantService = plantService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>>findAllUsers() {
        try {
            List<User> users = userService.getAll();
            if(users.size() > 0)
                return new ResponseEntity<>(users, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User>findUserById(@PathVariable("id") Long id) {
        try {
            Optional<User> user = userService.getById(id);
            if (!user.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}/plants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Plant>>findAllPlantsByUserId(@PathVariable("id") Long id) {
        try {
            List<Plant> plants = userService.getPlantsByUserId(id);
            Optional<User> user = userService.getById(id);
            if (user.isPresent()) {
                if (plants.size() > 0)
                    return new ResponseEntity<>(plants, HttpStatus.OK);
                else
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "{id}/plants", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Plant> insertPlantIntoUser(@PathVariable("id") Long id, @Valid @RequestBody Plant plant) {
        try {
            Optional<User> user = userService.getById(id);
            if(user.isPresent()) {
                user.get().getPlants().add(plant);
                Plant newPlant = plantService.save(plant);
                return ResponseEntity.status(HttpStatus.CREATED).body(newPlant);
            }
            else
                return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "{id}/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Event>> findAllEventsByUserId(@PathVariable("id") Long id) {
        try {
            List<Event> events = userService.getEventsByUserId(id);
            Optional<User> user = userService.getById(id);
            if (user.isPresent()) {
                if (events.size() > 0)
                    return new ResponseEntity<>(events, HttpStatus.OK);
                else
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "{id}/plots", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Plot>> findAllPlotsByUserId(@PathVariable("id") Long id) {
        try {
            List<Plot> plots = userService.getPlotsByUserId(id);
            Optional<User> user = userService.getById(id);
            if(user.isPresent()) {
                if (plots.size() > 0)
                    return new ResponseEntity<>(plots, HttpStatus.OK);
                else
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findByEmail(@PathVariable("email") String email) {
        try {
            Optional<User> user = userService.findByEmail(email);
            if(!user.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/searchPremium", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>>findByPremium() {
        try {
            List<User> users = userService.findByPremium(true);
            if(users.size() > 0)
                return new ResponseEntity<>(users, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        try {
            Optional<User> deleteUser = userService.getById(id);
            if(!deleteUser.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> insertUser(@Valid @RequestBody User user) {
        try {
            User newUser = userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
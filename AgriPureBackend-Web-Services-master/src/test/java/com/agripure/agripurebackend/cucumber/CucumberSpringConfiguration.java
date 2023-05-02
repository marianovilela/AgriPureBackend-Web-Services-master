package com.agripure.agripurebackend.cucumber;

import com.agripure.agripurebackend.AgriPureBackendApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = AgriPureBackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = AgriPureBackendApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {

}

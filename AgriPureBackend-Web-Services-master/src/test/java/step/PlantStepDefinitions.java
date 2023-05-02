package step;

import com.agripure.agripurebackend.entities.Plant;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PlantStepDefinitions {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int randomServerPort;
    private String endpointPath;
    private ResponseEntity<String> responseEntity;
    @Given("The Endpoint {string} is available")
    public void theEndpointIsAvailable(String endpointPath) {
        this.endpointPath = String.format(endpointPath, randomServerPort);
    }

    @When("A plant request is sent with values {string}, {string}, {string}, {string}, {string}, {string}, {float}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {int}, {int}")
    public void aPlantRequestIsSentWithValues(String name, String image, String saved, String scientifist_name, String variety, String info_land_type, Float ph , String info_distance_between, String distance_between, String info_ideal_depth, String depth, String info_weather_conditions, String weather, String info_fert_fumig, int intervale_fert, int intervale_fumig) {
        Plant plant = new Plant(0L,
                name,
                image,
                Boolean.valueOf(saved),
                scientifist_name,
                variety,
                info_land_type,
                ph,
                info_distance_between,
                distance_between,
                info_ideal_depth,
                depth,
                info_weather_conditions,
                weather,
                info_fert_fumig,
                intervale_fert,
                intervale_fumig,
                new ArrayList<>());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Plant> request = new HttpEntity<>(plant, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }

    @Then("A plant with status {int} is received")
    public void aPlantWithStatusIsReceived(int expectedStatusCode) {
        int actualStatus = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatus);
    }

    @When("A plant delete is sent with id value {string}")
    public void aPlantDeleteIsSentWithIdValue(String id_plant) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_plant);
        testRestTemplate.delete(endpointPath + "/{id}", params);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
    }

    @When("A plant selected is sent with name value {string}")
    public void aPlantSelectedIsSentWithNameValue(String name) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", name);
        Plant plant = testRestTemplate.getForObject(endpointPath + "?searchByName=/{name}", Plant.class, params);
        //Plant plant = testRestTemplate.getForObject(endpointPath + "/searchByName/{name}", Plant.class, params);
        responseEntity = new ResponseEntity<>(plant.toString(), HttpStatus.OK);
        System.out.println(plant.toString());
    }
}

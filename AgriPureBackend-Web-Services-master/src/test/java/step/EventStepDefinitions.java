package step;

import com.agripure.agripurebackend.entities.Event;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EventStepDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int randomServerPort;
    private String endpointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The Endpoint {string} is available")
    public void theEndpointIsAvailable(String endpointPath) {
        this.endpointPath=String.format(endpointPath, randomServerPort);
        //throw new io.cucumber.java.PendingException();
    }

    @When("A event request is sent with values {string}, {string}")
    public void aEventRequestIsSentWithValues(LocalDate date, String description) {
        Event event = new Event(0L, date, description);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Event> request = new HttpEntity<>(event, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
        //throw new io.cucumber.java.PendingException();
    }

    @Then("A event with status {int} is received")
    public void aEventWithStatusIsReceived(int expectedStatusCode) {
        int actualStatusCode = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatusCode);
        //throw new io.cucumber.java.PendingException();
    }

    @When("A event delete is sent with id value {string}")
    public void aEventDeleteIsSentWithIdValue(String id_event) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_event);
        testRestTemplate.delete(endpointPath+"/{id}", params);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
    }

    @When("A event selected is sent with date value {string}")
    public void aEventSelectedIsSentWithDateValue(String date) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", date);
        Event event = testRestTemplate.getForObject(endpointPath+"?date=/{date}", Event.class, params);
        responseEntity = new ResponseEntity<>(event.toString(), HttpStatus.OK);
        System.out.println(event.toString());
    }
}

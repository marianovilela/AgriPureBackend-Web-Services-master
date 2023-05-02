package step;

import com.agripure.agripurebackend.entities.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserStepDefinitions {
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

    @When("A user request is sent with values {string}, {string}, {string}, {string}")
    public void aUserRequestIsSentWithValuesTrue(String username, String email, String password, Boolean premium) {
        User user = new User(0L, username, email, password, true);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity<>(user, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }

    @Then("A user with status {int} is received")
    public void aUserWithStatusIsReceived(int expectedStatusCode) {
        int actualStatus = responseEntity.getStatusCodeValue();
        assertThat(expectedStatusCode).isEqualTo(actualStatus);
    }

    @When("A user delete is sent with id value {string}")
    public void aUserDeleteIsSentWithIdValue(String id_user) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_user);
        testRestTemplate.delete(endpointPath + "/{id}", params);
        responseEntity = new ResponseEntity<>(HttpStatus.OK);
    }

    @When("A user selected is sent with id value {string}")
    public void aUserSelectedIsSentWithIdValue(String id_user) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id_user);
        User user = testRestTemplate.getForObject(endpointPath + "{/id}", User.class);
        responseEntity = new ResponseEntity<>(user.toString(), HttpStatus.OK);
        System.out.println(user.toString());
    }
}

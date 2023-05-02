Feature: Plant Testing
  Background:
    Given The Endpoint "http://localhost:8080/api/plants" is available

  @post-plant
  Scenario: Add Plant
    When A plant request is sent with values "Tomato", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/640px-Bright_red_tomato_and_cross_section02.jpg", "false", "Brassica oleracea var. botrytis", "Brassica oleracea", "information about the land type", 5.5, "information about distance between plants", "40-60 cm", "information about ideal depth", "0.5-1.5 m", "Information about the weather conditions", "15°-18° C", "information about fertilization and fumigation", 2, 3
    Then A plant with status 201 is received

  @delete-plant
  Scenario: Delete Plant
    When A plant delete is sent with id value "2"
    Then A plant with status 200 is received

  @get-plant-by-name
  Scenario: Get Plant By Id
    When A plant selected is sent with name value "Tomato"
    Then A plant with status 200 is received
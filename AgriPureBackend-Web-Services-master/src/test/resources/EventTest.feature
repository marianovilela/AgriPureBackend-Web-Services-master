Feature: Event Testing

  Background:
    Given The Endpoint "http://localhost:%d/api/events" is available

    @post-event
    Scenario: Add Event
      When A event request is sent with values "2022-11-01", "Regar la parcela de lechugas"
      Then A event with status 201 is received

    @delete-event
    Scenario: Delete Event
      When A event delete is sent with id value "13"
      Then A event with status 200 is received

    @get-event-by-date
    Scenario: Get Event By Date
      When A event selected is sent with date value "2022-11-21"
      Then A event with status 200 is received
Feature: User Testing

  Background:
    Given The Endpoint "http://localhost:%d/api/users" is available

  @post-user
  Scenario: Add User
    When A user request is sent with values "example", "example123@gmail.com", "example", "true"
    Then A user with status 201 is received

  @delete-user
  Scenario: Delete User
    When A user delete is sent with id value "1"
    Then A user with status 200 is received

  @get-user-by-id
  Scenario: Get User By Id
    When A user selected is sent with id value "0"
    Then A user with status 200 is received
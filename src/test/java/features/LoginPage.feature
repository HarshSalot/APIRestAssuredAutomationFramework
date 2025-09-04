@tag
Feature: Login Page scenarios
  
  @smokeTest @loginPageTest
  Scenario: Login to Spectool with invalid username and password
    Given Add Login Payload with "username" "password"
    When user in LoginPage calls "auth" API with "post" method at endpoint "login"
    Then the API call got "fail" with status code 403
    And the response body contains "payload.error" with value "someExpectedToken"

  @smokeTest  @loginPageTest
  Scenario: User reset password from forget Password
  Given Add forgot Payload with "harsh@atsspec.com"
  When user in LoginPage calls "auth" API with "post" method at endpoint "forgetPass"
  Then the API call got "success" with status code 200
  
   @smokeTest  @loginPageTest
    Scenario: New User SignUp for spectool
    Given Add signup payload with all the details
    When user in LoginPage calls "auth" API with "post" method at endpoint "registration"
    Then  the API call got "success" with status code 200
    And get the "userID""firmID""OfficeID" from the response
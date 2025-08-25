@tag
Feature: User Profile API Validation

  As a QA Automation Engineer
  I want to verify user profile details using a GET API call
  So that I can ensure the correct user information is returned

  @userProfilePageTest @smokeTest
  Scenario: User verify profile details
    Given user is authenticated with valid token
    When user with id "6903871" in UserProfilePage calls "contact" API with "get" method at endpoint "/user/"
    Then the UserProfilePage API response status code should be 200
    And response body should contain "firstName" as "QA"
    And response body should contain "lastName" as "ST Special Automation"
    And response body should contain "address1" as "885 Milner Ave"
    And response body should contain "city" as "Toronto"
    And response body should contain "companyName" as "Allied Technical Solutions"

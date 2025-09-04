@tag
Feature: Create a Projects API validation

  As a QA Automation Engineer
  I want to verify project creation 
  
  Background:
    Given User is authenticated with valid token

  @projectPageTest @smokeTest
  Scenario: Create a new Project with blank spec
    Given Create project payload from "projectPayload.json"
    When user calls "project" API with "post" method at endpoint "addProject"
    Then the ProjectPage API call is successful with status code 200
    And response contains a valid project id
    And verify project name from the response

   @projectPageTest @smokeTest
  Scenario: Delete a project using its project ID
    Given a valid project ID to delete
    When user calls "project" API with "delete" method at endpoint "deleteProject" and projectId "projectId"
    Then the ProjectPage API call is successful with status code 200  
    And verify project is deleted successfully 

@tag
Feature: Spec Page scenarios

  @smokeTest @specPageTest
  Scenario: User create a Spec
  Given Create spec payload
  When user in SpecPage calls "spec" API with "post" method at endpoint "/spec" 
  Then the SpecPage API call success with status code 201 
  And response body contains spec id
  And verify the spec name

  @smokeTest @specPageTest
  Scenario: User update existing spec
  Given spec id with the payload update
  When user in SpecPage calls "spec" API with "patch" method at endpoint "/spec/" and specId "specId"
  Then the SpecPage API call success with status code 200
  And verify the updated spec name
    
  @smokeTest @specPageTest
  Scenario: User delete created spec
  Given spec id with the payload
  When user in SpecPage calls "spec" API with "DELETE" method at endpoint "/spec/" and specId "specId"
  Then the SpecPage API call success with status code 200
  And verify the deleted spec
  
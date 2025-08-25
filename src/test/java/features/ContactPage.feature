@tag
Feature: Contact page scenario

  @smokeTest @contactPageTest
  Scenario: User create a new contact
    Given Add contact payload with all the details
    | firstName        | {{name}}     |
    | ownerId          | 4179247     |
    | ownerType        | office      |
    | setAsDefaultEmail| false       |
    When user in ContactPage calls "contact" API with "post" method at endpoint "/contact"
    Then  the ContactPage API call got success with status code 200
    And get the ID from the response
    And verify the contact name

  @hold
  Scenario: User search  a contact
    Given Search contact "My Contact"
    When user in ContactPage calls "contact" API with "get" method at endpoint "/contact"
    Then  the ContactPage API call got success with status code 200
    And get the "userID""firmID""OfficeID" from the response
    
  @smokeTest @contactPageTest
  Scenario: User update an existing contact
    Given Update contact with new details by updating name "UniqueIDupdated"
    When user in ContactPage calls "contact" API with "patch" method at endpoint "/contact/" and contactId "contactId"
    Then  the ContactPage API call got success with status code 200
    And verify contact is updated

 @smokeTest @contactPageTest
  Scenario: User delete an existing contact
    Given  Delete contact setup
    When user in ContactPage calls "contact" API with "delete" method at endpoint "/contact/" and contactId "contactId"
    Then  the ContactPage API call got success with status code 200
    And verify contact is deleted successfully
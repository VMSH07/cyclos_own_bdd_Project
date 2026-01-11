@Contacts
  Feature: Add Contact in contact list.
@AddContact
  Scenario: User should be able to add contact in to contact.
  Given User logins succefully logins with valid user name "VMSH" and password "Harsha@427" .
  When User clicks on contacts.
  And User navigates to contacts and clicks on add new button.
  And User searches for desired contact and selects from drop down.
  Then User clicks on submit button and verifies success notifaction and contact added.
   

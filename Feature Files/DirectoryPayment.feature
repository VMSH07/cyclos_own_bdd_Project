@Directory
Feature: Directory payment.

Background:
Given User clicks on directory option from dashboard.
 
@PayThroughDirectory
Scenario: Search and pay to user through directory list.
When  User searches for the contact from keywords text field "Battula" .
And User verfies the searched contact "BATTULA VIJAY SHANKAR" and clicks on it.
And User click on make payment link and navigates to payment to user and verify contact "BATTULA VIJAY SHANKAR".
And User enters amount "10" discription "Payment 1" and clicks on next button.
And User clicks on confirm button.
Then User should be displayed with success message "The payment was successfully processed" or per day limit message "You have exceeded the maximum of payments per day for the demo network".

@Advertisements
Scenario: Add jobs -> Java Development Course to favorites and remove.
When  User verifies and clicks on Advertisements.
And User verfies and clicks on show advertisements button.
And User searches for "Java" verifies "Java Development Course" advertisement and clicks on it.
And User verifies title "Java Development Course" and add to favorites.
And User verifies success message "Added to favorites" and label change "Remove from favorites" .
Then User clicks on remove from favorites and verifies message "Removed from favorites" .




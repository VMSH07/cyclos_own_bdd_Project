@pay
Feature: Pay user functionality

@payuser @regression
Scenario Outline: Pay to user positive data
Given User is successfully loggedin with the data "<username>" "<password>" and is on Dashboard page
When User clicks on pay user link
And User is navigated to payment page and types "<suggest>" and clicks on demo one suggestion 
And User enters "<amount>" amount and clicks on next button
And User is navigated to Payment confirmation page and click on confirm button
Then Verify Payment successfull message
Examples:
         |username  |password|suggest  |amount|
         |demo      |1234    |demo one |1234  |
         |demo5     |1235    |demo     |1235  |
         |demo      |1234    |demo     |1234  | 

@Schedulepay @regression
Scenario Outline: Schedule payment positive data
Given User is successfully loggedin with the data "<username>" "<password>" and is on Dashboard page
When User clicks on pay user link
And User is navigated to payment page and types "<suggest>" and clicks on demo one suggestion 
And User enters "<amount>" amount and Selects schedules option from Scheduling dropdown
And User fills the Due date "25062023" and clicks on next
And User is navigated to Payment confirmation page and click on confirm button
Then Verify Payment successfull message
Examples:
         |suggest |amount|username|password|
         |demo one|9.05  |demo    |1234    |
         |demo    |12.50 |demo5   |1235    |
         |de      |25.00 |demo    |1234    |  
@LoginPositiveNegitive
Feature: Login Positive and negative cases.
@LoginScenarios
Scenario Outline: Verify login cases positive and negative.
Given user click on login from home page.
When User enters valid/invalid/blank "<userName>" and valid/invalid/blank "<password>".
And User clicks on submit button.
Then User verifies validation messages for negative cases and verify logout button presence for positive case.

Examples:
| userName  |   password   |
|           |              |
|           |   Harsha@427 |
|   VMSH    |              |
|   VMSH    |   Harsha@427 |
|DemoProject|   1234567    |
|   VMSH    |   7890       |
|DemoProject|   Harsha@427 |
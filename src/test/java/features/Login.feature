Feature: Application Login

Scenario: Login with valid credentials

Given open any browser
And Navigate to login page
When User enters username as "din@gmail.com" and password as "din123" into fields
And User clicks on login button
Then Verify user is able to successfully login
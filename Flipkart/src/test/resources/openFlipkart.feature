Feature: This is to open Flipkart and check for validation

@Login
Scenario Outline: Check if user is able to login

	Given I am on Flipkart home Page <URL>
	When i submit credentials <UN> and <PAss>
	Then i should be able to login
	Then compare the logo
	
	Examples:
	|URL|UN|PAss|
	|"https://www.flipkart.com/"|"rahtiwari@gmail.com"|"Test1234!"|

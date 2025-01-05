#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Amazon login page

Background:
Given Launch url

Scenario: TC01_Login Page
    When Enter email
    Then Click on continue
  
  Scenario Outline: TC01_Login Page
    When Enter "<email>" count <number>
    Then Click on continue
Examples:
    |email |number|
    |Kaar@gamil.com|1|
    |Mani@gamil.com|2|
    |Venky@gamil.com|3|
 @SSS   
Scenario: TC03_Select costly phone
When Search iphone
Then Find costliest iphone
And Select costliest iPhone


  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |

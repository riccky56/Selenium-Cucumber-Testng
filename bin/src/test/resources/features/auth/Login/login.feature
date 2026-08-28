@smoke @auth @login
Feature: Login
  As a registered user
  I want to log into the website
  So that I can access my account

  Scenario: Successful login with registered account
    Given I am on the login page
    When  I login with the registered account credentials
    Then  I should be logged in

  Scenario Outline: Failed login with wrong credentials
    Given I am on the login page
    When  I login with email "<email>" and password "<pass>"
    Then  I should see login error "Your email or password is incorrect!"

    Examples:
      | email          | pass       |
      | wrong@test.com | WrongPass1 |

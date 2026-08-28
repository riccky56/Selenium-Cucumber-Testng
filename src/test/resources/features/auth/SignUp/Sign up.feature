@smoke @auth @signup
Feature: Sign Up - Account Information
  As a new user
  I want to complete my account registration
  So that I can access my account

  Scenario Outline: Happy - Fill all account info and create account successfully
    Given I am on the SignUp page
    And   I enter name "<name>" and email "<email>"
    And   I should see the account information form
    When  I fill account info with title "<title>" password "<password>" day "<day>" month "<month>" year "<year>"
    And   I fill address info firstName "<firstName>" lastName "<lastName>" company "<company>" address "<address>" country "<country>" state "<state>" city "<city>" zipcode "<zipcode>" mobile "<mobile>"
    And   I click create account
    Then  I should see account created page

    Examples:
      | name     | email                 | title | password  | day | month | year | firstName | lastName | company | address     | country       | state      | city        | zipcode | mobile     |
      | AutoUser | autouser@mailtest.com | Mr    | Auto@1234 | 15  | June  | 1990 | Auto      | User     | AutoCo  | 123 Main St | United States | California | Los Angeles | 90001   | 1234567890 |

  Scenario Outline: Bad - Leave password empty and try to create account
    Given I am on the SignUp page
    And   I enter name "<name>" and email "<email>"
    And   I should see the account information form
    When  I fill account info with title "<title>" password "<password>" day "<day>" month "<month>" year "<year>"
    And   I fill address info firstName "<firstName>" lastName "<lastName>" company "<company>" address "<address>" country "<country>" state "<state>" city "<city>" zipcode "<zipcode>" mobile "<mobile>"
    And   I click create account
    Then  I should remain on the account info page

    Examples:
      | name     | email                    | title | password | day | month | year | firstName | lastName | company | address     | country       | state      | city        | zipcode | mobile     |
      | AutoUser | autobaduser@mailtest.com | Mr    |          | 15  | June  | 1990 | Auto      | User     | AutoCo  | 123 Main St | United States | California | Los Angeles | 90001   | 1234567890 |

Feature: Add employee in HRMS

  Background:
    #Given user is able to access HRMS application
    When user enters admin username and admin password
    And user clicks on login button
    Then user is navigated to dashbaord page
    When user clicks on PIM option

  @add @regression @sprint1

  Scenario: Add employee
    And user clicks on add employee option
    When user enters firstname and lastname in the name fields
    And user clicks on save button
    Then user added successfully

  @regression @sprint2
  Scenario: Add employee by firstname middlename and lastname
    And user clicks on add employee option
    When user enters firstname middlename lastname in the name fields
    And user clicks on save button
    Then user added successfully


  @params
  Scenario: Adding employee using parameters
    And user enters "mark" and "antony" and "jacob" in the application
    And user clicks on save button
    Then employee added successfully

    @ddt
    Scenario Outline: Adding multiple employees
      When user enters "<firstname>" and "<middlename>" and "<lastname>"
      And user clicks on save button
      Then user added successfully
      Examples:
        | firstname | middlename | lastname |
        |beria      |ms          |juli      |
        |jack       |ms          |sparrow   |
        |regina     |ms          |filanji   |

      @datatable
      Scenario: Adding employees using data table
        When user enters employees using data table and save them
        |firstname  |middlename  |lastname  |
        |beria      |ms          |juli      |
        |jack       |ms          |sparrow   |
        |regina     |ms          |filanji   |

        @excel
        Scenario: Adding employees using excel file
          When user adds multiple employees using excel file
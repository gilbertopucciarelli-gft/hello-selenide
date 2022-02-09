Feature: Robobar Cart
  Scenario: User adds one RoboCola to Cart
    Given User opens RoboBar Website
    When User adds a RoboCola to Cart
    Then Total should be €1.25

  Scenario: User adds two RoboColas to Cart
    Given User opens RoboBar Website
    When User adds a RoboCola to Cart
    And User adds a RoboCola to Cart
    Then Total should be €2.50

  Scenario: User adds one RoboBeer to Cart
    Given User opens RoboBar Website
    When User adds a RoboBeer to Cart
    Then Total should be €2.00

  Scenario: User adds one RoboBeer to Cart
    Given User opens RoboBar Website
    When User adds a RoboBeer to Cart
    And User adds a RoboBeer to Cart
    Then Total should be €4.00

  Scenario: User adds one RoboWine to Cart
    Given User opens RoboBar Website
    When User adds a RoboWine to Cart
    Then Total should be €3.00

  Scenario: User adds one RoboBeer to Cart
    Given User opens RoboBar Website
    When User adds a RoboWine to Cart
    And User adds a RoboWine to Cart
    Then Total should be €6.00

  Scenario: User adds one RoboCola and one RoboBeer to Cart
    Given User opens RoboBar Website
    When User adds a RoboCola to Cart
    And User adds a RoboBeer to Cart
    Then Total should be €3.25

  Scenario: User adds one RoboCola, one RoboBeer and one RoboWine to Cart
    Given User opens RoboBar Website
    When User adds a RoboCola to Cart
    And User adds a RoboBeer to Cart
    And User adds a RoboWine to Cart
    Then Total should be €6.25

  Scenario: User adds one RoboCola and proceeds to Checkout his order
    Given User opens RoboBar Website
    When User adds a RoboCola to Cart
    And User proceeds to Checkout
    And User proceeds to complete his order
    Then Confirmation Message should be "Coming right up! ~bzzzt~"

  Scenario: User adds one RoboBeer proceeds to Checkout his order (Underage)
    Given User opens RoboBar Website
    When User adds a RoboBeer to Cart
    And User proceeds to Checkout
    And User clicks Age Input
    And User specify 17 as his age
    And User proceeds to complete his order
    Then Alert Message should be "Only adults can buy alcohol!"

  Scenario: User adds one RoboBeer proceeds to Checkout his order (Overage)
    Given User opens RoboBar Website
    When User adds a RoboBeer to Cart
    And User proceeds to Checkout
    And User clicks Age Input
    And User specify 21 as his age
    And User proceeds to complete his order
    Then Confirmation Message should be "Coming right up! ~bzzzt~"
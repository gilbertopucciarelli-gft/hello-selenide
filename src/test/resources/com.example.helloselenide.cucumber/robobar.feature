Feature: Robobar Cart
  Scenario Outline: User adds several RoboColas to Cart
    Given User opens RoboBar Website
    When User adds <n> RoboCola to Cart
    Then Total should be €<total>
    Examples:
      | n | total |
      | 1 | 1.25  |
      | 2 | 2.50  |

  Scenario Outline: User adds several RoboBeers to Cart
    Given User opens RoboBar Website
    When User adds <n> RoboBeer to Cart
    Then Total should be €<total>
    Examples:
      | n | total |
      | 1 | 2.00  |
      | 2 | 4.00  |

  Scenario Outline: User adds several RoboWines to Cart
    Given User opens RoboBar Website
    When User adds <n> RoboWine to Cart
    Then Total should be €<total>
    Examples:
      | n | total |
      | 1 | 3.00  |
      | 2 | 6.00  |

  Scenario Outline: User adds several drinks to Cart
    Given User opens RoboBar Website
    When User adds <cola> RoboCola, <beer> RoboBeer and <wine> RoboWine to Cart
    Then Total should be €<total>
    Examples:
      | cola | beer | wine | total |
      |   1  |   1  |   1  |  6.25 |
      |   1  |   1  |   0  |  3.25 |
      |   0  |   1  |   1  |  5.00 |
      |   1  |   0  |   0  |  1.25 |
      |   2  |   0  |   0  |  2.50 |
      |   0  |   1  |   0  |  2.00 |
      |   0  |   2  |   0  |  4.00 |
      |   0  |   0  |   1  |  3.00 |
      |   0  |   0  |   2  |  6.00 |

  # ---
  Scenario: User adds one RoboCola and proceeds to Checkout his order
    Given User opens RoboBar Website
    When User adds 1 RoboCola to Cart
    Then User proceeds to Checkout

  Scenario: User Checkout his order and proceeds to complete his order
    Given User proceeds to Checkout and order several RoboColas
    When User proceeds to complete his order
    Then Confirmation Message should be "Coming right up! ~bzzzt~"

  # ---
  Scenario: User adds one RoboBeer proceeds to Checkout his order (Underage)
    Given User opens RoboBar Website
    When User adds 1 RoboBeer to Cart
    Then User proceeds to Checkout

  Scenario: User Checkout his order and proceeds to input his age (Underage)
    Given User proceeds to Checkout and order several RoboBeers
    When User specify 17 as his age
    Then User proceeds to complete his order

  Scenario: User completes his order and receives a message (Underage)
    Given User proceeds to complete his order Underage
    When User proceeds to complete his order
    Then Alert Message should be "Only adults can buy alcohol!"

  # ---
  Scenario: User adds one RoboBeer proceeds to Checkout his order (Overage)
    Given User opens RoboBar Website
    When User adds 1 RoboBeer to Cart
    Then User proceeds to Checkout

  Scenario: User Checkout his order and proceeds to input his age (Overage)
    Given User proceeds to Checkout and order several RoboBeers
    When User specify 21 as his age
    Then User proceeds to complete his order

  Scenario: User completes his order and receives a message (Overage)
    Given User proceeds to complete his order Overage
    When User proceeds to complete his order
    Then Alert Message should be "Coming right up! ~bzzzt~"

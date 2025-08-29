Feature: Triangle classification (black-box)

  Scenario Outline: EP and BVA sets
    Given I have the sides <a>, <b>, and <c>
    When I classify the triangle
    Then the result should be "<expected>"

    Examples:
      | a   | b   | c   | expected       |
      |   5 |   5 |   5 | Equilateral    |
      |   5 |   5 |   3 | Isosceles      |
      |   5 |   4 |   3 | Scalene        |
      |   1 |   2 |   3 | Not a triangle |
      |   2 |   3 |   5 | Not a triangle |
      |   2 |   3 |   4 | Scalene        |
      |   0 |   5 |   5 | Invalid sides  |
      |  -5 |   5 |   5 | Invalid sides  |
      | 201 |  10 |  10 | Invalid sides  |
      |   1 |   1 |   1 | Equilateral    |
      | 200 | 200 | 199 | Isosceles      |
      | 199 | 200 | 200 | Isosceles      |

Feature: Triangle classification

  Scenario Outline: Classify valid and invalid triangles
    Given I have the sides <a>, <b>, and <c>
    When I classify the triangle
    Then the result should be "<expected>"

    Examples:
      | a   | b  | c  | expected       |
      |   5 |  5 |  5 | Equilateral    |
      |   5 |  5 |  3 | Isosceles      |
      |   5 |  4 |  3 | Scalene        |
      |   1 |  2 |  3 | Not a triangle |
      |  -5 |  0 |  5 | Invalid sides  |
      | 201 | 10 | 10 | Invalid sides  |

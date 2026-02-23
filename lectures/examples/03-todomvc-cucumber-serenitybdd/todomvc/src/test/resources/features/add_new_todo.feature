Feature: Add new item to TODO list

Scenario: Add buying milk to the list
Given User is looking at his TODO list
When User adds "Buy some milk" to the list
Then User sees "Buy some milk" as an item in the TODO list
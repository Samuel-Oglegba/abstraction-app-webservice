Feature: Test Cucumber class

  Scenario: Clicking on Deduplication
    Given The Deduplication task graph
    When I click on the edge named compress_queue
    Then Deduplication task shows, communication (to data structure involved) and communication variable

  Scenario: The task Graph Action
    Given The Deduplication task graph
    When I click on any node
    Then The function name should be shown in the implementation pan/window -- Show the type of the communication (e.g pipe, work/slave etc) and the data abstraction involved
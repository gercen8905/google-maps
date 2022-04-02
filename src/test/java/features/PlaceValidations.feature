Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: verify if Place is being successfully added by addPlaceApi
    Given add Place payload with "<name>" "<language>" "<address>"
    When user calls "ADD_PLACE_API" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_ID created maps to "<name>" using "GET_PLACE_API"
    Examples:
      | name      | language | address      |
      | MY_HOUSE  | Spanish  | Taudesku,18  |
      | MY_HOUSE1 | English  | Taudesku,119 |
      | MY_HOUSE2 | Suomi    | Taudesku,100 |

  @DeletePlace @Regression
  Scenario: verify if Delete Place functionality is working

    Given DeletePlace Payload
    When user calls "DELETE_PLACE_API" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
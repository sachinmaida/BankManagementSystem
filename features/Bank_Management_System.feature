Feature: BankManagement Scenario

@P1
Scenario Outline: customer makes call to GET /logout
Given I am on login page with <username> and <password>
When the customer calls /logout
Then customer receives status code of 200

Examples: 
      | username  | password | status  |
      | "chinnu" |    "qwerty" | 200 |

@P2
Scenario Outline: To check customer login 
    Given I want to login with <username> and <password>
    When I click on login in step
    Then I verify the <statuscode>

    Examples: 
      | username  | password | status  |
      | "chinnu" |    "qwerty" | 200 |
      | "chinmay"| "qwerty" | 200|
      | "sfef" | "wrw" | 200|
  
@P3
Scenario Outline: To check customer registration 
    Given I want to register with <username> and <password> and <name> and <address> and <state> and <country> and <emailaddress> and <PAN> and <contactnumber> and <Accounttype> 
    When I click on register in step
    Then I verify the <statuscode>
    Examples:
    | username  | password | name  | address  | state | country  |   emailaddress  | PAN  | contactnumber | Accounttype  | status  |
    | "roshen123"  | "qwerty" | "Roshen"  | "Kota"  | "rajasthsn" | "India"  |   "chinmay@abc.com"  | "qwedwsr1"  | "123456789" | "carloan"  |  200  |
     
@P4
Scenario Outline: To Apply for loan to customer
Given I am on login page with <username> and <password>
When the customer applies for loan <loanType> and <loanAmount> and <date> and <rateOfInterest> and <durationOfLoan> 
Then  I verify the <statuscode>

	Examples: 
      | username  | password   |loanType |loanAmount |date  | rateOfInterest | durationOfLoan    | status|
      | "sachin265" |    "qwerty" | "carloan"| "2000" | "12-12-2021"  | "15" | "10"  |200|
 
 
 @P5
Scenario Outline: To update Customer Details
Given I am on login page with <username> and <password>
When the customer wants to update <new_username> and <new_password> and <new_name> and <new_address> and <new_state> and <new_country> and <new_emailaddress> and <new_PAN> and <new_contactnumber> and <new_Accounttype>
Then  I verify the <statuscode>

	Examples:
    | username  | password    | new_username  | new_password | new_name  | new_address  | new_state | new_country  |   new_emailaddress  | new_PAN  | new_contactnumber | new_Accounttype  | status  |
    | "sachin265"  | "qwerty" | "sachin"      | "qwerty"      |"Sachin Maida" | "Thandla"  | "Madhya Pradesh" | "India"  |   "sachinmaida@abc.com"  | "qwedwsr1"  | "8827620462" | "saving"  |  200  |
  
	@P6
Scenario Outline: To view loan details 
    Given I am on login page with <username> and <password>
    When the customer wants to view Loan Details
    Then customer receives gets loan details

    Examples: 
      | username  | password | status  |
      | "sachin265" |    "qwerty" | 200 |
	
 
    

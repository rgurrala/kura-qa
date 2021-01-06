Feature: smoke pack

#create an org, edit it and perform assertion pre and post edit

  Scenario: as an operator i should be able to add and edit an organisation
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    When I click on the plus icon
    Then I should be able to add an organisation and search and assert the creation organisation and search and assert the creation
    Then I should be able to edit the created organisation and assert the changes

#    create and org, create a person profile, edit it and perform assertion pre and post edit

  Scenario: as a operator i should be able to add and edit a person profile
    Given I am logged in as an operator and create an organisation
    When i navigate to the magic search page and search & select a particular organization
    Then I should be able to add a person profile and assert its creation
    And I should be able to edit the created person profile and assert the changes

#    create and org,add a vehicle, edit it and perform assertion pre and post edit


  Scenario: as an operator i should be able to add and edit a vehicle
    Given I am logged in as an operator and create an organisation
    When i navigate to the magic search page and search & select a particular organization
    Then I should be able to add a new vehicle and assert its creation
    And I should be able to edit the created vehicle and assert the changes

#  create an org, create an action plan, edit it and perform assertion pre and post edit

  Scenario: as a operator i should be able to create & edit an action plan
    Given I am logged in as an operator and create an organisation
    When i navigate to the magic search page and search & select a particular organization
    Then I should be able to create an action plan and assert its creation
    And I should be able to update the created action plan


#    create an action plan and use it to create client booking, asset the client booking creation

  Scenario: create an action plan and use it to create a client booking & edit it
    Given I am logged in as an operator and create an organisation
    When i navigate to the magic search page and search & select a particular organization
    And create an action plan
    Then I should be able to use it to create a client booing
    And I should be able to confirm new client booking creation

#    create an org, create an auth group,  edit it and perform assertion pre and post edit

  Scenario: as an operator i should be able to create an auth profile and assert its creation
    Given I am logged in as an operator and create an organisation
    When i navigate to the magic search page and search & select a particular organization
    Then i should be able to add an auth group and assert its creation
    And I should be able to edit the created auth group and assert the changes

 #multiple tasks

  @Web
  Scenario: as an operator i should be able to create an auth profile and assign the profile to a user
  login as the user and confirm that the permissions are enforced
    Given I am logged in as an operator and create an organisation
    When I create an auth group and assign that to a new person profile
    Then I should be able to login as that person and confirm that the permissions are enforced


  Scenario: as an operator i should be able to create a supplier and a vehicle and
  use the supplier details to create the trip, and finally login as the driver and check that
  the trip is registered under him
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    When I create a supplier and add driver and vehicle
    Then I should be able to create a trip with the new driver and vehicle details
    And when logged as a driver, I should be able to see the trip

  @Web
  Scenario Outline: try creating a trip with name that includes special characters
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And i pick organisation from magic search or org page
    Then I should be able to add a "<tripName>" see if i can access the trip


    Examples:
      | tripName |
      | ()       |
      | []       |
      | {}       |
      | ;        |
      | :        |
      | -        |
      | @        |
      | *        |
      | &        |


  @Web
  Scenario: as an operator i should be able to bulk upload passenger and observer data
    Given I am logged in as an operator and create an organisation
    And i navigate to the magic search page and search & select a particular organization
    When i perform bulk upload
    Then the passengers and observers list should be populated
    And when i update the list with a new updated upload then the list should be updated accordingly


  Scenario: as an operator i would like to capture late alarm and late schedule observer notifications
    Given I create a two hour head single day trip with "passengerfive"
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    When i wait for 2 hour
    Then i should be able to capture the no logon and late alarms on the alarm page
    Then I should be able to capture delayed parent notification in oneSignal

  @Web
  Scenario: as an operator i would like to create an org, add observer, add passenger and link the passenger to the observer,
  create a trip for that passenger and login as the observer and see that he can see the trip and i break and bond the link between the
  cognitive account and the observer profile. trip witin 2 hours 10 minutes
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    When I create an org and add an observer profile
    And i navigate to the magic search page and search & select a particular organization
    And create a client booking for the trip via setting the contract to be observable by "NONE"
    And i create cognito account and link the already created observer profile to the passenger whilst creating a passenger profile with linking multiple observers set to "true"
    And I link up the first observer with the created cognito account
    And I should be able to export the person profiles as a csv file
    And I should be able to add a trip with passenger addition set to "true"
    And I should be able to sign up to the observerapp on the mobile device
    When I break the link between the observer profile and cognitive account
    Then the link page should be shown when the observer logs in
    And when i re-establish the link
    Then the observer should be able to see the passenger again on the app


#    observer should be able to see the trip his passenger is on and should not be able to see once he is removed off the trip
  @Trip
  Scenario: as an observer i should be able to see the trips the passenger is on, and un see once he is removed from the trip
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    Given I create a single day trip with stops "my home" and "old home", "passengerfive" and contract "notifications"
    And simultaneously login as "observertwo"
    Then I should be able to see the trip under the observer app
    And once the passenger is removed off the trip
    Then the observer "observertwo" will not be able to see


#      orgs admin should see the updates made to the booking table

  @Web
  Scenario: ops with in the org should be able to see passenger additions and deletions under bookings summary and check pagination
    Given as a coachhire user i issue permissions to org user
    And I create a single day trip with stops "my home" and "old home", "passengerfive" and contract "notifications"
    Then I should be able to view passenger addition under booking when i login as ops with in the org
    And once the passenger is removed off the trip
    Then I should be able to view passenger deletion under booking when i login as ops with in the org



#  bulk email send (1 linked and 1 unlinked obs) and document upload to the person profile
  @Web
  Scenario: automate bulk email send
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    When I create an org and add an observer profile
    And i create cognito account and link the already created observer profile to the passenger whilst creating a passenger profile with linking multiple observers set to "true"
    And I link up the first observer with the created cognito account and provide an email address to the second observer
    Then I should see one observers profile linked
    And when i try sending re-invite on emails should be sent
    And when i send invite only email should be sent
    And only the non linked user gets sent the email
    Then I should be able to successfully add a document


#adding passenger when the slot becomes free -via edit passengers
  @Web
  Scenario: as a user i should be able to add a passenger to the stop (via edit supplier) where there are spaces are available
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And i create a four point trip with multiple passengers
    And I navigate to the modify trip tab
    And I should be able to add supplier for the whole of the trip
    And I navigate to the modify trip tab
    When i try adding passenger at the stop where the slot is available
    Then the passenger addition should be successful


#    adding passengers via observer app once the slot becomes free
  @Trip
  Scenario: as an observer i should be able to add a passenger to the stop where there are spaces available trip beyond 2 hours 10 minutes
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And i create a four point trip with multiple passengers
    And I set the trip to be observable by observers
    And I navigate to the modify trip tab
    And I should be able to add supplier for the whole of the trip
    When simultaneously login as "furio"
    And add my passenger to the stop where there are spaces available
    Then the addition should be successful
















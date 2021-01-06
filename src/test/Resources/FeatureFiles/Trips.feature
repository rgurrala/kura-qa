Feature: trips

  @Trip
  Scenario: user should be able to clone a trip, clone a trip in reverse
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a single day trip for tomorrow with stops "my home" and "old home", "passengerfive" and contract "notifications"
    When I navigate to the view trip I should be able to clone a trip and clone the trip in reverse

#adding and deleting passengers across full length of a trip schedule
  @Trip
  Scenario: as an operator i should be able to add and delete passengers across the length of the trip
    Given I am logged in as an operator and a trip skip dates set to "false" has been recently created with passenger "passengerone"
    When I navigate to the modify trip tab
    Then I should be able to add passengers for the full trip schedule
    And assert that passengers have been added to the full trip
    When I navigate to the modify trip tab
    And  delete the passengers
    Then the i should be able to assert passenger deleted


#adding and deleting supplier for full length of the trip
  @Trip
  Scenario: as an operator i should be able to add a supplier for full length of trip and then delete the supplier for single day
    Given I am logged in as an operator and a trip skip dates set to "false" has been recently created with passenger "passengerone"
    When I navigate to the modify trip tab
    Then I should be able to add supplier for the whole of the trip
    And assert the supplier added to full trip
    And when i delete the supplier for single day
    Then the supplier should be deleted for that particular day

 #adding and deleting a route point to full length trip schedule
  @Trip
  Scenario: as an operator i would like to add and delete a route point to full length trip
    Given I am logged in as an operator and a trip skip dates set to "false" has been recently created with passenger "passengerone"
    When I navigate to the modify trip tab
    And when I add route points to full trip wait "false"
    Then I should be able to assert that new added rout points
    When I delete the added route point across trip schedule
    Then the route should be deleted

    #adding an extra passenger and route point for today
  @Trip
  Scenario: user should be able to add extra passenger and route point for today via edit trip, in a trip schedule
    Given I am logged in as an operator and a trip schedule starting "150" minutes ahead from today has been recently created
    And add extra passengers from trip schedule for today
    Then I should be able to asset added passengers for today
    And I should be able to add extra way points for today
    And assert that the way point is only added to today

  Scenario: user should not be able to edit route points with in 2 hours and 5 minutes of the scheduled start time
    Given I am logged in as an operator and a trip schedule starting "90" minutes ahead from today has been recently created
    When I try adding a route point for trip that is scheduled for today
    Then I should get an error message stating "Trip has already started"

#   deleting route point where passenger de-boards; trip schedule
  @Trip
  Scenario: when a route point where a passenger is supposed to be added or deleted is deleted (across the trip schedule) then the passenger is automatically deleted
  along with that particular route point from the full trip schedule
    Given I am logged in as an operator and a trip skip dates set to "false" has been recently created with passenger "passengerone"
    When the route point where the passenger is supposed to de-board is deleted across full length of the trip
    Then the passenger along with the route point is deleted from the full length of the trip
    And the booking table should show manifest the changes for passenger "passengerone"


#    deleting route point where passenger de-boards; single day
  @Trip
  Scenario: when a route point where a passenger is supposed to be added or deleted is deleted (for a single day) then the passenger is automatically deleted
  along with that particular route point for that particular day
    Given I am logged in as an operator and a trip skip dates set to "false" has been recently created with passenger "passengerone"
    When the route point where the passenger is supposed to de-board is deleted for single day only
    Then the passenger along with the route point is deleted for that particular day



    #vehicle capacity
  @Trip
  Scenario: an operator should not be able to set the vehicle capacity to lower than the number of people on board
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a trip with four stops with "passengerone", "passengertwo", "passengerthree" and "passengerfour" on board with onlyOnePassengerOnBoardAtAnInstance set to "false" and time to trip 90
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "1"
    Then I should see an error message stating "Too many assets on board"

 #vehicle capacity
  @Trip
  Scenario: an operator should not be able to over-boarding the vehicle through edit trip after the capacity is set
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a trip with four stops with "passengerone", "passengertwo", "passengerthree" and "passengerfour" on board with onlyOnePassengerOnBoardAtAnInstance set to "false" and time to trip 90
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "3"
    And I try over-boarding the vehicle via edit trip
    Then I should see an error message stating "Too many assets on board"

#    change trip name
  @Trip
  Scenario: operator should be able to change the name of the trip schedule
    Given I am logged in as an operator and a trip skip dates set to "false" has been recently created with passenger "passengerone"
    When i navigate to the search trip page
    Then I should be able to change the trip name and assert the changes

#notification message from driver

  @Trip
  Scenario: as a driver i would like to see the trips created under my supplier and i should be able to send a message and cancel the trip
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a single day trip with stops "my home" and "old home", "passengerone" and contract "searchableByObservers"
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    When I launch kura mobile app
    And login as the driver "uatkuratesting+driver@gmail.com"
    And navigate to the particular trip
    Then i should be able to send a message and cancel the trip,which should be captured under journey events on web kura

#notification message to driver
  @Trip
  Scenario: simulate a driver journey by spoofing the gps and send notifications to the driver app as an operator
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a single day trip with stops "my home" and "old home", "passengerone" and contract "searchableByObservers"
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    When I launch kura mobile app
    And  set geo location for maidenhead road
    And login as the driver "uatkuratesting+driver@gmail.com"
    And navigate to the particular trip
    Then I should be able to mimic that i am actually at the pick up point and board passengers
    And when i end the journey
    Then I should be able to navigate to the trip journey page
    And send notifications to the driver

#  skip stops
  @Trip
  Scenario: driver should be able to skip stops
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a trip with four stops with "passengerone", "passengertwo", "passengerthree" and "passengerfour" on board with onlyOnePassengerOnBoardAtAnInstance set to "true" and time to trip 90
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    When I launch kura mobile app
    And  set geo location for maidenhead road
    And login as the driver "uatkuratesting+driver@gmail.com"
    Then I should be able to skip the desired stop and complete my Journey
    And I should be able to capture the skip notification under journey view

#  i am here
  @Trip
  Scenario: driver should be able to see and confirm his location by clicking i am here button
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a trip with four stops with "passengerone", "passengertwo", "passengerthree" and "passengerfour" on board with onlyOnePassengerOnBoardAtAnInstance set to "true" and time to trip 90
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    When I launch kura mobile app
    And  set geo location for maidenhead road
    And login as the driver "uatkuratesting+driver@gmail.com"
    And navigate to the particular trip
    And board "passengerone" at maidenhead road
    When I set geo location for croydon area to jump the maidenhead bridge
    Then i should be able confirm my location by clicking i am here button and complete the rest of the journey
    And I should be able to capture the i am here button on the jurney event screen

#driver journey with board and disembark
  @Trip
  Scenario: 4 pointer journey with multiple boarding and deboarding at each way point
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a trip with four stops with "passengerone", "passengertwo", "passengerthree" and "passengerfour" on board with onlyOnePassengerOnBoardAtAnInstance set to "true" and time to trip 90
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    When I launch kura mobile app
    And  set geo location for maidenhead road
    And login as the driver "uatkuratesting+driver@gmail.com"
    And navigate to the particular trip
    And board "passengerone" at maidenhead road
    And confirm the stop name "my home" and address split "31 Royston Way"
    And  set geo location for maidenhead bridge
    And board "passengertwo" and deboard "passengerone" at maidenhead bridge
    And set geo location for croydon arena
    And board "passengerthree" and deboard "passengertwo" at croydon arena
    And set geo location for sevenoaks rail station
    And board and deboard passengers at sevenoaks rail station
    Then I should be able to capture starting, ending points of journey as well as boardings, deboardings under the journey page.
    And I should be able to compare event time and system time

#search and find passengers on driver app
  @Trip
  Scenario: as a driver i would like to use the search and find passengers on the passengers page
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a trip with four stops with "aad", "passengertwo", "passengerthree" and "passengerfour" on board with onlyOnePassengerOnBoardAtAnInstance set to "true" and time to trip 90
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    When I launch kura mobile app
    And  set geo location for maidenhead road
    And login as the driver "uatkuratesting+driver@gmail.com"
    And navigate to the particular trip
    Then I should be able to search and find the passenger

#early schedule, boarding and deboarding
  @Trip
  Scenario: as a parent i would like to see the status of my child school trip and receive early schedule, boarding and de_boarding notifications
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a single day trip with stops "my home" and "old home", "passengerfive" and contract "notifications"
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    When I launch kura mobile app
    And  set geo location for maidenhead road
    And simultaneously login as "observertwo"
    And login as the driver "uatkuratesting+driver@gmail.com"
    And navigate to the particular trip
    And board "passengerfive" at maidenhead road with passenger search set to "false" and unexpectedBoard set to "false"
    Then I should be able to capture the boarding notification on the parent app
    And  set geo location for maidenhead bridge
    And unboard the "passengerfive" at maidenhead bridge
    Then i should be able to capture deboarding notification on the parent app
    And end the journey
    And I should be able to process the early alarms under the alarm section

#passenger skipped boarding, manual alarms

  @Trip
  Scenario: as an operator i would like manual triggered alarm, driver sent medical alerts and passenger skipped boarding alerts on alarm page
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a single day trip with stops "my home" and "old home", "passengerfive" and contract "notifications"
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    When I launch kura mobile app
    And  set geo location for maidenhead road
    And simultaneously login as "observertwo"
    And login as the driver "uatkuratesting+driver@gmail.com"
    And navigate to the particular trip
    And  set geo location for maidenhead bridge
    And wait for "15000"
    Then I should receive a passenger not boarded notification on parent app
    When send a medical alarm
    And end the journey
    Then I should be able to trigger a manual alarm alert on the journey page
    And i should be able to capture manual triggered alarms, driver sent medical alerts and no boardings under alarms

#unexpected passenger on journey boarded on route
  @Trip
  Scenario: driver should see a confirmation message when he tries to board passengers who are not on the route and the parent will receive un expected board and disembark messages on parent app
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a trip with four stops with "passengerone", "passengertwo", "passengerthree" and "passengerfour" on board with onlyOnePassengerOnBoardAtAnInstance set to "true" and time to trip 90
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    And simultaneously login as "observerthree"
    When I launch kura mobile app
    And  set geo location for maidenhead road
    And login as the driver "uatkuratesting+driver@gmail.com"
    And  set geo location for right outside the boarding point of the geo fence
    And navigate to the particular trip
    And open the passenger board page
    And board "aad" at maidenhead road with passenger search set to "false" and unexpectedBoard set to "true"
    Then I should get unexpected boarding message on parent app
    And  set geo location for maidenhead bridge
    And advance to stop
    And unboard the "aad" at maidenhead bridge
    And close driver app
    Then I should get unexpected deboarding message on parent app
    And I should capture unexpected journey notification on the kura


#contract override
  @Trip
  Scenario: as an user i should be able to override the contract for a trip
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a single day trip with stops "my home" and "old home", "passengerfive" and contract "notifications"
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    When I override the contract under particular client booking
    And I launch kura mobile app
    And  set geo location for maidenhead road
    And login as the driver "uatkuratesting+driver@gmail.com"
    And simultaneously login as "observertwo"
    And navigate to the particular trip
    And board "passengerfive" at maidenhead road with passenger search set to "false" and unexpectedBoard set to "false"
    Then i should be able to confirm that by performing the trip

#  observer bookable trips
  @Trip
  Scenario: an operator should be able to create a passenger bookable trips and the passenger should be able to login and book a trip
    Given I create a trip with four stops and passenger "ajith" addition is set to "false" and the trip is configured to be viewable by an observer
    When I login as the observer "furio"
    Then I should be able to view the trip and book the passenger on to the trip
    When I remove the passenger off the trip
    Then The passenger should not appear under the obs app

#  observer bookable trips
  @Trip
  Scenario: observer shall not be able to put a passenger on the trip if its over the capacity
    Given I create a trip with four stops and passenger "passengerfive" addition is set to "true" and the trip is configured to be viewable by an observer
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "1"
    When I login as the observer "furio"
    And try adding a passenger to the trip
    Then an error message stating "Trip is full" should be thrown

#    passenger trip schedule button
  @Trip
  Scenario: view passenger scheduled trips button
    Given I create a trip with four stops and passenger "akon" addition is set to "true" and the trip is configured to be viewable by an observer
    When I navigate to view schedule trips under the respective passenger
    And I should be able to view and remove the person from the trip


#    skip a day whilst in trip creation
  @Trip
  Scenario: i should be able to create a trip with skip dates
    Given I am logged in as an operator and a trip skip dates set to "true" has been recently created with passenger "passengerone"
    Then I should be confrim that the trip has been created only for a single day

#  trip exclusion dates
  @test
  Scenario: i should be able to exclude trip dates post creation of trip schedule
    Given I am logged in as an operator and a trip skip dates set to "false" has been recently created with passenger "rupa"
    When I navigate to the edit trip schedule and add exclusion dates
    Then I should be able to delete all the trips from the trip calendar
    And I should be able to confirm tht the trip is deleted under the passenger trip schedule
    And the booking table should show manifest the changes for passenger "rupa"

#  org admin notifications to - all observers and targeted observer
  @Trip
  Scenario: i should be able to check sending notifications to all observers as well as to targeted observer
    Given I am logged in as an org admin with userName "uatkuratesting+orgadmin@gmail.com"
    And I create a trip with four stops with "passengerone", "passengertwo", "lisa" and "stefanie" on board with onlyOnePassengerOnBoardAtAnInstance set to "true" and time to trip 90
    And simultaneously login as "ben"
    When I send a notification to "All Observers"
    Then all the observers should receive the parent notification
    And when I send notification to particular observer
    Then only that observer should receive notification

#    org admin - create trips and send down stream notifications
  @Trip
  Scenario: as an org admin i would like to create a trip and add own supplier and perform the trip.
  the org user should be able to login view the trip and send notifications to observer and the driver app
    Given I am logged in as an org admin with userName "uatkuratesting+orgadmin@gmail.com"
    And I create a trip with four stops with "lisa", "passengerone", "passengertwo" and "stefanie" on board with onlyOnePassengerOnBoardAtAnInstance set to "true" and time to trip 60
    And I add a supplier "OrgCreatedForAutoTest2" and driver "orgdriver" with capacity "10"
    And I launch kura mobile app
    And  set geo location for maidenhead road
    And login as the driver "uatkuratesting+orgdriver@gmail.com"
    And navigate to the particular trip
    And simultaneously login as "ben"
    And board "lisa" at maidenhead road with passenger search set to "false" and unexpectedBoard set to "false"
    And  set geo location for maidenhead bridge
    And board "passengerone" and deboard "lisa" at maidenhead bridge
    And close the parent app
    And set geo location for croydon arena
    And board "passengertwo" and deboard "passengerone" at croydon arena
    And open parent app
    When I send a notification to "Downstream Observers" via trip page
    Then only that observer should receive notification

  #org admin check permissions
  @Trip
  Scenario: org user permissions: bookings, maps and reporting
    Given as a coachhire user i issue permissions to org user
    Then the org user should be able to access bookings, maps and reporting
    When I revoke permissions
    Then the org user should not be able to access bookings, maps and reporting

#    #=unexpected passenger at stop boarded on route
  @Trip
  Scenario: boarding and deboarding the passengers out side the geo fence
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a single day trip with stops "my home" and "old home", "passengerfive" and contract "notifications" and an extra stop
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    When I launch kura mobile app
    And simultaneously login as "observertwo"
    And  set geo location for maidenhead road
    And login as the driver "uatkuratesting+driver@gmail.com"
    And navigate to the particular trip
    And  set geo location for right outside the boarding point of the geo fence
    And board "passengerfive" at out side the geofence
    Then I should get unexpected boarding notification along with passenger not boarded notification
    And I should be able to see that passenger has boarded on route under journey screen

  @Trip
  Scenario: as an observer i should be able to change the board and deboard sector and re add my passenger to the trip he is already on.
    Given I create a trip with four stops and passenger "ajith" is bording at the first part of the journey
    When I login as the observer "furio"
    Then I should be able to change the route points and rebook the passenger on the same trip
    And I should be able to confirm the changes under the edit trip
    When I remove the passenger off the trip
    Then The passenger should not appear under the obs app

  Scenario: 3 year long trip schedule & assert trips in calander; add supplier and check
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a three year long trip schedule
    Then I should assert that trips are visible for days specified
    Then I should be able to add a supplier for the entire trip schedule
    And assert that the supplier has been added for days specified

  Scenario: 3 year trip schedule with 100 passengers, check passengers added
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a three year long trip schedule
    And wait for "300000"
    And I navigate to the modify trip tab
    When I add hundred passengers to the trip schedule
    And navigate to org page
    Then I should be able to assert that the passenengers are added to the three year trip schedule

  Scenario: 3 year trip schedule with 100 passengers, add route points and check
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a three year long trip schedule
    And wait for "300000"
    And I navigate to the modify trip tab
    And when I add route points to full trip wait "true"
    Then I should be able to assert that the route point is added to three year trip schedule

  @Trip
  Scenario: deleting route point via "edit routepoints" should be manifested under booking summary
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I create a trip schedule with multiple passenger transactions at stops
    When I delete that particular stop
    Then the client booking table should update accordingly
    And I should be able to download the summary in csv file


  Scenario: need to automate bulk upload with external IDs

  @Trip
  Scenario: automate disabling passengers making sure they dont appear on parent app and they gets deleted from kura trips
    Given I am logged in as an operator and create an organisation
    And i navigate to the magic search page and search & select a particular organization
    And create a client booking for the trip via setting the contract to be observable by "NONE"
    When i perform bulk upload and create a trip with passenger "Ali Anderson" and time 100
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10" for randomly created org
    When I perform bulk update to disable the passenger on the trip
    Then the passenger should be removed under the trips

#    disabled passengers should not appear under driver app and driver should see the private way points on the journey page
  @Trip
  Scenario: disabled passengers should not appear under the driver app and the driver should be able to see the private way points on whilst on the journey page
  cenario: automate disabling passengers making sure they are not searchable by drivers, they dont appear on parent app and they gets deleted from kura trips
    Given I am logged in as an operator and create an organisation
    And i navigate to the magic search page and search & select a particular organization
    And create a client booking for the trip via setting the contract to be observable by "NONE"
    When i perform bulk upload and create a trip with passenger "Ali Anderson" and time 90
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10" for randomly created org
    When I perform bulk update to disable the passenger on the trip
    Then the driver should be able to see the private way points and the removed passenger, should not appear under list of passengers on driver app

  @Trip
  Scenario: editing route points for a passenger on a single trip should be manifested under booking summary table
    Given I create a trip with four stops and passenger "passengerfive" addition is set to "true" and the trip is configured to be viewable by an observer
    When I change the route points of the passenger
    Then the changes for the single trip passenger "passengerfive" edit should be manifested under booking summary

  @Trip
  Scenario: link between user profile and cognito account should be broken if the name of the observer is changed via bulk update
    Given I am logged in as an operator and create an organisation
    And i navigate to the magic search page and search & select a particular organization
    And create a client booking for the trip via setting the contract to be observable by "NONE"
    When i perform bulk upload and create a trip with passenger "Gail Green" and time 95
    And link the observer profile to the account
    When I login as the observer i should be able to confirm that i am linked
    And when i perform the bulk update
    Then the observer "green" will not be able to see

  @Trip
  Scenario: search with email (full and partial), first & last name & tokens; updated names for passenger. data should be sorted by role (passenger, observer and admin user)
    Given I am logged in as an org admin with userName "uatkuratesting+orgadmin@gmail.com"
    When I navigate to the profiles page
    Then I should be able to search by email, first and last names, phone numbers and tokens
    Then I should be able to update the name and search for it
    And I should be able to check email and phone links

  @Trip
  Scenario: edit the timings on a trip schedule and assert the changes
    Given I am logged in as an operator and a trip skip dates set to "false" has been recently created with passenger "passengerone"
    When I navigate to the modify trip tab
    And  change the trip schedule timings
    Then I should be able to asset the newly created trip schedule across all the days

  @Trip
  Scenario: disabled passengers should be searchable under magic search
    Given I am logged in as an operator and i have uploaded randomly generated passenger
    And I should be able to search for that passenger
    When I disable the passenger via bulk update
    Then that passenger should be un searchable under profiles
    And the passenger should be searchable in magic search

  @Trip
  Scenario: cannot have overlapping suppliers
    Given I am logged in as an operator and a trip skip dates set to "false" has been recently created with passenger "passengerone"
    When I navigate to the modify trip tab
    Then I should be able to add supplier for the whole of the trip
    And i provide a second supplier with over lapping dates
    Then the error message should say "Suppliers must be in order and not overlapping"

  @Web
  Scenario: bulk upload should not duplicate a passenger profile that has common unique reference number
    Given I am logged in as an operator and create an organisation
    And i navigate to the magic search page and search & select a particular organization
    When I create a passenger profile with a unique reference number
    And i perform bulk upload with a passenger profile matching the earlier created reference number
    Then the passenger profile should not be duplicated but only updated

  @Trip
  Scenario: create a contract with T&Cs, use it to create a trip. login as the observer and assert that T&C agreement is mandatory for the 1st registration and after any
  changes to T&Cs
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    When I create an org and add an observer profile
    And i navigate to the magic search page and search & select a particular organization
    And create a client booking for the trip via setting the contract to be observable by "OBSERVERS"
    And add T&Cs for the contract with pdf "dummy"
    And i create cognito account and link the already created observer profile to the passenger whilst creating a passenger profile with linking multiple observers set to "true"
    And I link up the first observer with the created cognito account
    And I logout and login with operator credentials
    And I should be able to add a trip with passenger addition set to "false"
    And I should be able to sign up to the observerapp on the mobile device
    When I should only be able to add the passenger to the trip only after accepting T&Cs
    And once accepted the observer should not be asked to accept the TandC agreement again
    And once the passenger is removed from the trip and the T&Cs of the very contract used to create the trip earlier are updated with pdf "dummy2"
    Then the agreement kicks in again

  @Web
  Scenario: exporting booking summary data
    Given I am logged in as an operator and i navigate to the booking summary table of a particular organisation
    Then I should be export the data as per the week of my choice

  @Trip
  Scenario: boarding passenger at stop before the geo fence
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And create a trip to be able to board the passenger before the geo fence
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    And simultaneously login as "observertwo"
    When I launch kura mobile app
    And  set geo location for maidenhead road
    And login as the driver "uatkuratesting+driver@gmail.com"
    And navigate to the particular trip
    And I should be able to capture the early scheduled notification on parent app
    And board "passengerfive" at maidenhead road
    And I should get unexpected boarding message on parent app
    And  set geo location for maidenhead bridge
    And I navigate from passengers page to journey page on the app
    And end the journey
    And I should not receive passenger not boarded notification on parent app

  @Trip
  Scenario: flicking observable by to "none" and "observers" should make the trip disappear and appear under parent app
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And i create a four point trip with multiple passengers
    And I set the trip to be observable by observers
    Then I should be able to view the trip on the parent app
    And when i flick the contract to be observable by none
    Then the trip should vanish under parent app
    And if i were to create a trip whilst the contract is set to be observable by none and then flick it back to to observable by observers
    Then I should be able to see both the trips

  @Web
  Scenario: automate booking window closed
    Given a trip has already been created that is scheduled for less then 2 hours from the time of creation
    When I login as the observer "furio"
    And try adding a passenger to the trip
    Then an error message stating "Booking window closed" would be displayed

  @Trip
  Scenario: supplier details of the trips that are part of trip schedule (excluding for today) cannot be edited via edit trip
    Given I am logged in as an operator and a trip skip dates set to "false" has been recently created with passenger "passengerone"
    When i navigate to edit trip of any day and provide supplier details
    Then the error message should say "Trip can not be updated when within schedule"

  @Web
  Scenario: a supplier details for today cannot be added via edit suppliers
    Given I am logged in as an operator and a trip schedule starting "5" minutes ahead from today has been recently created
    When I navigate to the modify trip tab of tomorrow
    And I try adding supplier details for entire duration of trip
    Then the error message should say "Can not add suppliers for past trips"

  @Web
  Scenario: supplier details for the trips in the past are uneditable
    Given I am logged in as an operator and a trip schedule starting "2" minutes ahead from today has been recently created
    And I add a supplier "SupplierForAutoTest" and driver "driverone" with capacity "10"
    Then the error message should say "Trip has already begun or been canceled"

  @Web
  Scenario: private way points should be searchable and are exclusive to the org they are created under
    Given I am logged in as an operator and create an organisation
    When I create a private way point "first waypoint" under postcode "SL1 6EP"
    Then I should be able to see way point "first waypoint"
    And I should be able to prove that the created way point is specific to this particular org only

  @Web
  Scenario: default admin auto group is uneditable
    Given I am logged in as an operator and create an organisation
    When I navigate to admin auth group and try editing it
    Then I should get an error message on edit auth group page stating "Can't edit Admin Security group"

  @Web
  Scenario: user should be able to view all the orgs he is linked to
    Given I am logged in as an operator with userName "rajeshgurrala@coachhire.com"
    And I link up 2 person profiles from 2 different orgs via common cognito account
    When I login as that person profile
    Then I should be able to view the organisations the account is linked to





















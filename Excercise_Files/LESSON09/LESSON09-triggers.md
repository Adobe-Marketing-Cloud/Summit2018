## Exercise 9 - Using Supported Events
===========

## Objective
In this lesson, we will learn how to leverage supported events to alter the channel within the player

## Tasks

Scenario 1:  Matching Events, higher Priorities
![scenario1](../../Resources/Picture59.png)

- Modify all Channels to have the same priority level 2
- Default Channel should have event "Initial Load"
- Happy hour Channel should have event "Timer" +  a schedule "after 5:00PM and before 7:00PM"
- Campaign Channel should have an event "Timer" + a schedule "after 9:00AM and before 10:00AM"

1.1 Click on preview from the location dashboard

Results:
- The initial load event fires when the player loads -->   Default Channel loads
- The initial load event is not present in any other channel assignment --> no matter what other channels have higher priorities, only Default will load
 
1.2 Modify all other channels to have a priority level 3

Result:
- Default channel still loads when the player starts




1. Re-order the priorities of your primary display
 ![primary channel schedule](../../Resources/Picture54.png)
 - Marketing Campaign role should be priority 3 with after 9:00AM and before 10:00AM
 - default role should be priority 2
 - Add a new channel called hangman.  The supported events should be User Interative only
 ![hangman](../../Resources/Picture55.png)

12. From the Locations Dashboard, click on preview to view your content.  While it is playing, click on the screen to trigger the interactive trigger

 **Note**
 the idle channel should play by default
 when a user clicks on the screen, the User Interaction trigger fires & switches channel to the hangman application.
 You can interact with the screen to play hangman

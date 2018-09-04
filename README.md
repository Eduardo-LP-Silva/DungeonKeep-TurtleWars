# TurtleWars

## Important

This README is outdated. This branch served as a mid point assessement and since then various changes were made. The updated version can be found in the finalRelease branch.

### UML Class and Package Diagram (Changes might occur in the future)

![turtlewarsstarteruml](https://user-images.githubusercontent.com/32617691/39407842-9e12988a-4bc4-11e8-9151-e1b7a548b763.png)

### State Diagram

Upon start, the application will be set to the Menu State.

![twstate](https://user-images.githubusercontent.com/32617691/39408569-56b7acf8-4bd0-11e8-8995-3b08654fff38.png)

### Design Pattern - MVC (Model - View - Controller)

We chose this design pattern beacuse our game will not only involve physics but also an elaborate logic (as well as various visual representations). Thus, this pattern will allow us to better manage all these aspects.
The implementation will be made through 3 packages, each representing one of 3 modules. Each package will have several classes, being one of them (usually the one started with Game) the main class in the package. This class will have the principal functions regarding the core functionalities of the package. There will be, however, a set of classes outside of this pattern which will serve other non-core functionalities, such as starting the application.

### GUI

* Main Menu

![mainmenu](https://user-images.githubusercontent.com/32617691/39411065-99a2ebf2-4bfa-11e8-938d-54b74e8ba056.png)

* Options Menu

![optionsmenu](https://user-images.githubusercontent.com/32617691/39411068-a673a65a-4bfa-11e8-9fdd-0ad94ec6b6c2.png)

* Character Selection Screen


![characterselectionmenu](https://user-images.githubusercontent.com/32617691/39411078-bdb03a2c-4bfa-11e8-9b54-ce2cb5d9be95.jpg)

* 2D View of arena and players
* Possible buttons on the corner of the screen for Android Input
* Health and Name bars on the top corners of the screen for each player

![gamedemo](https://user-images.githubusercontent.com/32617691/39411083-d3172362-4bfa-11e8-93b5-8e172c38a9dc.png)

### Test Design

* Movement Test - Gives every motion input (left, right, jump) and checks if the x/y coordinates changed appropriately.
* Collision Test - Pushes a turtle towards another turtle (without attacking) and sees if the x coordinate of the first doesn't go beoynd the second one.
* Shots Fired Test - Fires a missile towards a turtle a checks if this one took the appropriate damage.
* Melee Test - Makes a turtle perfom 2 melee attacks, one further from another turtle and another right next to it, checking if the second turtle took damage the 2nd time but not the first.
* GameOver Test - Continually pushes a turtle towards a cactus and checks if after some times has passed the game ended (in game over fashion).
* Victory Test - Makes the player's turtle kill a weaker turtle (one shot) and checks if victory condition was achieved.
* Character Selection Test - Checks if the type of turtle spawned in the world corresponds with the one choose in the character menu.

Further tests can be implemented in the future if the need arises.

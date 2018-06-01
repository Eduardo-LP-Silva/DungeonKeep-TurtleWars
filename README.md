# TurtleWars

## Setup

### Project

1. Download project from GitHub.
2. Import project in Android Studio.
3. When prompted to update project upon start, **don't!**

----- To test the desktop version and unit tests---------

4. Go to Edit Configurations, click on the green plus sign and select application.
5. On the Main class field select the DesktopLauncher class.
6. On the Working directory field, select ...\TurtleWars\android\assets.
7. Finnaly, select the classpath of the desktop and click apply and ok.


### Application

1. Make sure your phone allows installation of unsigned APKs.
2. Download APK to you phone.
3. Install APK.

## UML Diagram

### Model Package

![model](https://user-images.githubusercontent.com/32617691/40853358-b3a71318-65c5-11e8-836b-5340ce41a03d.png)

### View Package

![view](https://user-images.githubusercontent.com/32617691/40853364-b6183f0a-65c5-11e8-961c-b40bfcddc99d.png)

### Controller Package

![controller](https://user-images.githubusercontent.com/32617691/40853602-9b82e8ce-65c6-11e8-8635-59185be3d61d.png)

### Turtle Wars Class

![turtlewars](https://user-images.githubusercontent.com/32617691/40853605-9cc05a78-65c6-11e8-894f-2343c6e35aa2.png)

Documentation available at: https://web.fe.up.pt/~up201603135/

## State Diagram

Upon start, the application will be set to the Menu State.

![statediagram](https://user-images.githubusercontent.com/32617691/40849896-1d72b7ee-65bb-11e8-862a-cec17c87812c.png)

## Design Pattern - MVC (Model - View - Controller)

We chose this design pattern beacuse our game will not only involve physics but also an elaborate logic (as well as various visual representations). Thus, this pattern will allow us to better manage all these aspects.
The implementation will be made through 3 packages, each representing one of 3 modules. Each package will have several classes, being one of them (usually the one started with Game) the main class in the package. This class will have the principal functions regarding the core functionalities of the package. There will be, however, a set of classes outside of this pattern which will serve other non-core functionalities, such as starting the application.

## Major Difficulties

Along the realization of the project, we faced some difficulties that we had to overcome. 
Some of the biggest adversities that we faced were the creation of our sprites (since we wanted to use semi-original ones) and the implementation of the artificial intelligence regarding the enemy turtle.
The implementation of the JUnit tests proved to be difficult as well, since we made use of several components that required an actual application to be running (such as Timer Tasks). However, since the tests don't have this capability, we had to go for more "manual" yet less efective solutions.
We also tried to implement networking and social media integration, however we couldn't finish these on time.

## Lessons Learned

We succesfully learned how to work with the LibGdx physics engine (Box-2D) and how to develop basic Android Apps.

## Work Distribution

To deliver the project before the deadline, we had to distribute the work more or less evenly. We had to create the animation sprites, implement the MVC design and develop the game itself. If we had to divide the contribution of each element, it would be:

* Eduardo Silva - 55%
* Tomás Novo - 45%

## User Manual

The game starts with the Main Menu, where the user can choose an option:

![image](https://user-images.githubusercontent.com/32617691/40850464-93bde15c-65bc-11e8-8b05-ef07989d0455.png)

* “New Game” moves the user to the next Menu, where he can choose his character and start our game.
* “Exit” closes the app.

If the user pressed the first option, the game moves to the next menu: 

![image](https://user-images.githubusercontent.com/32617691/40850954-dbe59a3c-65bd-11e8-976e-60b84eb3bc86.png)

In this Menu, the user can choose his character and then press the “New Game” button to start playing.


![image](https://user-images.githubusercontent.com/32617691/40851004-0165d286-65be-11e8-87d6-b8b525e7d373.png)

The user begins in the left side of the map and he has to deplete all of the AI turtle’s health to win. 

The game can end with a victory...

![image](https://user-images.githubusercontent.com/32617691/40851047-17c2a32e-65be-11e8-916e-a2cb3ba11900.png)

... or with a defeat.

![image](https://user-images.githubusercontent.com/32617691/40851074-27d3417e-65be-11e8-9d8b-c0f814b609b8.png)

### Controls

There are different ways to make the player's turtle to move and attack according to the platform where our game is running.

1. Android
   * To control the turtle’s movement, the user must lean the phone left or right.

![phone](https://user-images.githubusercontent.com/32617691/40852277-d36ba53c-65c1-11e8-972d-7fbd5e5e065b.png)

   * In case the user wants to jump, he has to press the jump button on the lower left corner or double tap the screen (not as responsive as the button).

   * If the user wants to attack (bite and/or shoot), he has to press the two buttons on the lower right corner.

2. Desktop

   * To control the turtle’s movement, the user must press “A” key to go left and “D” key to go right.

   * To jump, the user must press the “W” key.

   * If the user wants to attack (bite and/or shoot), he has to press the “E” and “Q” keys, respectively.
  
Note: The PC users can also make use of the buttons directed towards the mobile users.



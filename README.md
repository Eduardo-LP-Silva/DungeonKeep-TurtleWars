# Dungeon Keep
Dungeon Keep is a 2D desktop mini-game in which the main character has to escape a dungeon patroled by a guard and 
guarded by several ogres over the course of two levels. It contains several features such as difficulty settings (either by number of enemies or different AI types), save and load mechanics and a level editor.

## Controls and Movement
The player can move with the WASD/arrow keys or using the respective buttons of the interface. Each time the player moves the enemy may also move (depending on certain conditions).

![image](https://user-images.githubusercontent.com/32617691/52183591-273e8d80-2801-11e9-9c3f-7f70f8b85050.png)

## Level 1
In the first level, the player must get past a patrolling guard and exit through one of the red doors. Initially, these are closed and can only be opened through a lever.

![image](https://user-images.githubusercontent.com/32617691/52183598-3de4e480-2801-11e9-9989-15eb62613dcd.png)

Before the start of the game, the player may choose the level of difficulty of the guard amongst 3 possibilities:

* **Rookie** - This guard follows the predetermined path rigorously, moving each time the player moves, in the same direction.
* **Drunken** - This guard will randomly fall asleep (thus becoming harmless), each time taking 3 turns to wake up. When he does, he may switch moving direction. 

![image](https://user-images.githubusercontent.com/32617691/52183620-78e71800-2801-11e9-8db3-52ea36a00289.png)

* **Suspicious** - This guard will randomly start to move in the opposite direction from time to time.

If the guard and player stand adjacent to each other (and the guard is active), the player will loose.

## Level 2
This level consists of a room with at least one ogre, depending on the player's choice at the beggining of the game (number of simultaneous ogres is limited to 8). Here the player must collect a key in order to open a red door and escape. However, unlike in the previous level, the path taken by the ogres is random at all times. These also posess a club which they swing in a random direction.

![image](https://user-images.githubusercontent.com/32617691/52183632-987e4080-2801-11e9-83f5-e717376daf5a.png)

If the player becomes adjacent to the club, they will loose. On the other hand, if they become adjacent to the ogre itself, they will stun him for a certain amount of turns.

![image](https://user-images.githubusercontent.com/32617691/52183648-c6638500-2801-11e9-8098-edc59e199826.png)

## Level Editor

Here the user can create custom maps for level 2, being able to choose its size and the different elements positions.

![image](https://user-images.githubusercontent.com/32617691/52183664-f743ba00-2801-11e9-8b69-284c5684e12d.png)

## EclEmma Coverage

![coverage](https://user-images.githubusercontent.com/32617691/38221676-b48a6cf6-36d8-11e8-98aa-d39a0824dd1a.png)

## PIT Test Coverage Report

![pit](https://user-images.githubusercontent.com/32617691/38221748-0e6c769c-36d9-11e8-8ee6-e5786050b32f.png)


Eduardo Luís Pinheiro da Silva | up201603135 | eduardo__lps@hotmail.com

Tomás Nuno Fernandes Novo | up201604503 | tomasnovo98@gmail.com

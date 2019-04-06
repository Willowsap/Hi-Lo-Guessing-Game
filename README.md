# Hi-Lo-Guessing-Game

Steps to run

  1: Clone the project.

  2: run "javac HiLoGame.java" in the project folder

  3: run "java HiLoGame" in the project folder


Optional command line arguments:
  1: specify number of games to run

  2: specify whether you would like a printout of how many times each number appeared per level

  3: specify whether you would like a printout of the ratio of how many times each number appeared per level

  4: specify a limit of how many levels deep these two printouts should go


Example:
java HiLoGame 1000000 true true 20


This will simulate one million games, display the number of times each number and the ratio of each number per level to a depth of 20 levels.


The reason to limit the depth of those printouts is that the deep levels will not have any significant meaning since they will be reached so rarely.


Without any arguments, the program will simulate one million games and display neither of the optional printouts.

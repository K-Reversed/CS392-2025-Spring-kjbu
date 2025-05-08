# **CS392 Final Exam Problems**

Problem 1:
## [NQueensPuzzle.java](MySolution/EightQueensPuzzle.java)
In order to not run in a Stack Overflow Error, please adjust the stack allocation to at least 4 mB.

Problem 2:
## [DFSforCS392.java](MySolution/DFSforCS392.java)
## [BFSforCS392.java](MySolution/BFSforCS392.java)

Problem 3:

Depth First Search (DFS) can be used to solve the N-Queens problem by iterating through every move a queen can take in a row. If a position is conflicts with the path of another queen (either vertically or diagonally) it backtracks up to a previous queen and continues until a valid placement for the queen is found. This continues until the last queen on the board (in this case the first row) arrives at the last valid placement.

Problem 4:
## [GameOf24.java](MySolution/GameOf24.java)

Depth First Search (DFS) can be used to solve a Game of 24 by iterating through all possible combinations of numbers. The search runs through all possible combinations of addition, subtraction, multiplication, and division. If a iteration returns with a 24, it is printed as a solution and continues. The search terminates once all possible combinations are iterated through. 


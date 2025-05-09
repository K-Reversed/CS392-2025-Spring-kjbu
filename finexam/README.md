# **CS392 Final Exam Problems**

Java Version: 23

Problem 1:
## [NQueensPuzzle.java](MySolution/EightQueensPuzzle.java)
In order to not run in a Stack Overflow Error, please adjust the stack allocation to at least 4 mB in the VM options/command line (-Xss4m). 

Problem 2:
## [DFSforCS392.java](MySolution/DFSforCS392.java)
## [BFSforCS392.java](MySolution/BFSforCS392.java)

Problem 3:

Depth First Search (DFS) can be used to solve the N-Queens problem by iterating through every move a queen can take in a row. If a position is conflicts with the path of another queen (either vertically or diagonally) it backtracks up to a previous queen and continues until a valid placement for the queen is found. This continues until the last queen on the board (in this case the first row) arrives at the last valid placement. The number of solutions is then printed.

My implementation of this problem can be found in the method depthFirstSearchNQueens found within [DFSforCS392.java](MySolution/DFSforCS392.java) which takes in an array that stores the positions of N-queens by row and returns the number of distinct solutions found. An int solutions is then initialized to count the number of distinct solutions. Then a new int array initialized with {0, 0} is then pushed into a stack. This {0, 0} represents the row and column of a queen's position. A while loop that checks that the stack isn't empty then does the following:
1. Takes out the last item in the stack and uses it to initialize an int tile.
2. Separates tiles into row (tile[0]) and column (tile[1])
3. checks if column is less than N, or length of the board.
   * If true, it then checks if the position is safe vertically and diagonally;
     * If true:
       1. it increases solution by one if row + 1 equals the length of the board
       2. Changes the position of the queen on the row to the new column.
       3. Pushes a new int initialized with {row + 1, 0} into the stack, and starts checking the next row.
     * If false, it pushes a new int initialized with {row, column + 1} into the stack, checking if the next column is safe
   * If false, it checks if the row is more than 0.
     * If true, it moves back to a previous row by pushing a new int initialized with {row - 1, arr[row - 1] + 1} into the stack.
     * If false, the while loop ends
At the end of the while loop, it returns the number of distinct solutions.

Note: A similar implementation can be done for Breadth First Search (BFS), just replace stack with queue, push with enqueue, and pop with dequeue.

Problem 4:
## [GameOf24.java](MySolution/GameOf24.java)

Depth First Search (DFS) can be used to solve a Game of 24 by iterating through all possible combinations of numbers. The search runs through all possible combinations of addition, subtraction, multiplication, and division, starting with the last integer of the array. If an iteration returns with a 24, it is printed as a solution and continues. The search terminates once all possible combinations are iterated through. 

My implementation starts by initializing int solutions to 0. A stack is then initialized to take in double arrays and the int array of inputs are converted into a double array (to prevent ClassCastException). The converted array is then pushed into the stack and precision is set for 1e-6 (within ±0.000001 of 24). A while loop then loops through the following while the stack has items:
1. Creates a double array preciseCalc from the top-most Stack item.
2. int start is initialized with the length of preciseCalc
3. if start is less than or equal to 1, it then checks if it equals 24±1e-6, if it does, solutions increases by 1, then resets the loop.
4. 2 nested for loops are used, the first loop i begins at 0 and ends at "start", the second loop j begins at i and also ends at "start". 
   * A double array NewCalc is created in loop j to store each upcoming operation in an individual array.
   * The for loop k removes any number equal to i or k from the newCalc array.
   * A new double array results store all operations done to i and j on the preciseCalc array.
   * An enhanced for loop moves all the results into their own array and then pushes it into the stack.
6. The method returns solutions at the end.

Note: The implementation above still has some major bugs, it returns too many duplicate solutions and sometimes returns solutions for inputs that do not have any. Unsure how to resolve this bug.

Last updated: 08 May 2025
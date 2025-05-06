public class EightQueensPuzzle {
    public static void main(String[] args) {
//        0, 4, 7, 1, 6, 2, 5, 3
        int[] rowPos = {0, 1, 2, 3, 4, 5, 6, 7};
        var q8 = new EightQueensPuzzle(rowPos);

        q8.printBoard();
        q8.solveBoard();
    }
    private final int[] queensPos;

    public EightQueensPuzzle(int[] rowPos) {
        queensPos = rowPos;
    }

    public void solveBoard() {
        solveBoard(new int[8], 0, 0, 0);
    }

    private int solveBoard(int[] qPos, int row, int column, int solutions) {
        if (column < 8) {
            System.out.println(row + ", " + column);
            if (checkSafe(qPos, row, column, row - 1)) {
                final int[] newPos = boardSet(qPos, row, column);
                if (row + 1 == 8) {
                    System.out.println("Solution #" + (solutions + 1)  + ":");
                    printBoard(newPos, 0);
                    return solveBoard(qPos, row, column + 1, solutions + 1);
                } else {
                    return solveBoard(newPos, row + 1, 0, solutions);
                }
            } else {
                return solveBoard(qPos, row, column + 1, solutions);
            }
        } else if (row > 0) {
            return solveBoard(qPos, row - 1, boardGet(qPos, row - 1) + 1, solutions);
        } else {
            return solutions;
        }
    }

    private int boardGet(int[] board, int row) {
        if (row >= 0 && row < 8) {
            return board[row];
        } else {
            return 0;
        }
    }

    private int[] boardSet(int[] board, int row, int newColumn) {
        board[row] = newColumn;
        return board;
    }

    private boolean checkSafe(int[] qPos, int row, int column, int i) {
        if (i >= 0) {
            if (check(qPos, row, column, i)) {
                checkSafe(qPos, row, column, i - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean check(int[] qPos, int row, int column, int i) {
        return qPos[i] != column && Math.abs(row - i) != Math.abs(column - qPos[i]);
    }

    public void printBoard() {
        printBoard(queensPos, 0);
    }

    private void printBoard(int[] positions, int row) {
        if (row >= 8) {
            System.out.println();
            return;
        }
        printRow(positions, row, 0);
        row++;
        printBoard(positions, row);
    }

    private void printRow(int[] positions, int row, int column){
        if (column >= 8) {
            System.out.println();
            return;
        }
        if (positions[row] == column) {
            System.out.print("[Q]");
        } else {
            System.out.print("[ ]");
        }
        column++;
        printRow(positions, row, column);
    }
}

public class EightQueensPuzzle {
    public static void main(String[] args) {
//        0, 4, 7, 1, 6, 2, 5, 3
        int[] rowPos = {0, 1, 2, 3, 4, 5, 6, 7};
        var q8 = new EightQueensPuzzle(rowPos);

        q8.printBoard();
        q8.solveBoard();
    }
    private final int[] queensPos;
    private final int N = 8;

    public EightQueensPuzzle(int[] rowPos) {
        queensPos = rowPos;
    }

    public void solveBoard() {
        solveBoard(new int[N], 0, 0, 0);
    }

    private int solveBoard(int[] qPos, int row, int column, int solutions) {
        if (column < N) {
            if (checkSafe(qPos, row, column, row - 1)) {
                final int[] newPos = boardSet(qPos, row, column);
                if (row + 1 == N) {
                    System.out.println("Solution #" + (solutions + 1)  + ":");
                    printBoard(newPos);
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
        if (row >= 0 && row < N) {
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
            if (checkVertical(qPos, row, column, i)) {
                return checkSafe(qPos, row, column, i - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean checkVertical(int[] qPos, int row, int column, int i) {
        return qPos[i] != column && Math.abs(row - i) != Math.abs(column - qPos[i]);
    }

    public void printBoard() {
        printBoard(queensPos);
    }

    private void printBoard(int[] positions) {
        printRow(positions[0]);
        printRow(positions[1]);
        printRow(positions[2]);
        printRow(positions[3]);
        printRow(positions[4]);
        printRow(positions[5]);
        printRow(positions[6]);
        printRow(positions[7]);
    }

    private void printRow(int rows){
        printSquares(rows);
        System.out.print("[Q]");
        printSquares(N - rows - 1);
        System.out.println();
    }

    private void printSquares(int squares) {
        if (squares > 0) {
            System.out.print("[ ]");
            printSquares(squares - 1);
        }
    }
}

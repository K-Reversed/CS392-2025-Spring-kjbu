/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 08 May 2025
 */
public class NQueensPuzzle {
    private final int[] queensPos;
    private final int N = 8;

    public NQueensPuzzle(int[] rowPos) {
        queensPos = rowPos;
    }

    public void solveBoardDFS() {
        DFSforCS392<Integer> dfs = new DFSforCS392<>();
        solveBoardDFS(new int[N], dfs);
    }

    public int solveBoardDFS(int[] qPos, DFSforCS392<Integer> dfs) {
        int solutions = dfs.depthFirstSearchNQueens(qPos);
        System.out.print(solutions + " distinct solutions");
        return solutions;
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
        int[] temp = board;
        temp[row] = newColumn;
        return temp;
    }

    private boolean checkSafe(int[] qPos, int row, int column, int i) {
        if (i >= 0) {
            if (check(qPos, row, column, i)) {
                return checkSafe(qPos, row, column, i - 1);
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
        System.out.println();
    }

    private void printRow(int rows){
        printTiles(rows);
        System.out.print("[Q]");
        printTiles(N - rows - 1);
        System.out.println();
    }

    private void printTiles(int tiles) {
        if (tiles > 0) {
            System.out.print("[ ]");
            printTiles(tiles - 1);
        }
    }

    public static void main(String[] args) {
//        0, 4, 7, 1, 6, 2, 5, 3
        int[] rowPos = {0, 1, 2, 3, 4, 5, 6, 7};
        var nQ = new NQueensPuzzle(rowPos);

        nQ.printBoard();
        //nQ.solveBoard();
        nQ.solveBoardDFS();
    }
}

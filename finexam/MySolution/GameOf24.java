/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 08 May 2025
 */
import java.util.InputMismatchException;

public class GameOf24 {

    public GameOf24() {}

    public void input(int[] inputs) {
        if (inputs.length != 4) throw new InputMismatchException("There should only be 4 integers.");
        DFSforCS392<Integer> dfs = new DFSforCS392<>();
        solveDFS(inputs, dfs);
    }

    private int solveDFS(int[] inputs, DFSforCS392<Integer> dfs) {
        int solutions = dfs.depthFirstSearchG24(inputs);
        System.out.println(solutions + " distinct solutions");
        return solutions;
    }

    public static void main(String[] args) {
        int[] nums = {10, 10, 4, 4};
        var g24 = new GameOf24();
        g24.input(nums);
    }
}

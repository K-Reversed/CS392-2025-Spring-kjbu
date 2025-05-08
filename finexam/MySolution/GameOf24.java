import java.util.InputMismatchException;

public class GameOf24 {

    public GameOf24() {}

    public void input(int[] inputs) {
        if (inputs.length != 4) throw new InputMismatchException("There should only be 4 integers.");
        iterate(inputs, inputs.length, 0);
    }

    private static void iterate(int[] inputs, int start, int solutions) {
        String function = "(";

        if (start <= 1) {
            return;
        }
        for (int i = 0; i < start; i++) {
            for (int j = i; j < start; j++) {
                int[] permeations = new int[start - 1];
                for (int k = 0, index = 0; k < start; k++) {

                }
            }
        }
    }

    public static boolean dfs(int[] nums, int n) {
        if (n == 1) {
            return Math.abs(nums[0] - 24) < 1e-6;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] newNums = new int[n - 1];
                for (int k = 0, index = 0; k < n; k++) {
                    if (k != i && k != j) {
                        newNums[index++] = nums[k];
                    }
                }
                for (int k = 0; k < 4; k++) {
                    if (k < 2 && j > i) continue;
                    switch (k) {
                        case 0:
                            newNums[n - 2] = nums[i] + nums[j];
                            break;
                        case 1:
                            newNums[n - 2] = nums[i] * nums[j];
                            break;
                        case 2:
                            newNums[n - 2] = nums[i] - nums[j];
                            break;
                        case 3:
                            if (nums[j] != 0) {
                                newNums[n - 2] = nums[i] / nums[j];
                            } else {
                                continue;
                            }
                            break;
                        }
                    if (dfs(newNums, n - 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {4, 1, 8, 7};
        var g24 = new GameOf24();
        //g24.input(nums);
        g24.
    }
}

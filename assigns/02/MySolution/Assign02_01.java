/**
 * @author Kevin Jiang (kjbu@bu.edu), Hongwei Xi
 * @version 1.0, 20 Feb 2025
 */
public class Assign02_01 {
    /*
      HX-2025-02-13: 10 points
      The 'int' type in Java is for integers of some fixed precision.
      More precisely, each integer of the type int is represented a sequence of bits
      of some fixed length. Please write a program that finds this length. Your program
      is expected return the correct answer instantly. Note that you can only use arithmetic
      operations here. In particular, no bit-wise operations are allowed.
     */
    public static void main(String[] argv) {
	// Please give your implementation here
        int num = Integer.MIN_VALUE;
        int power = 0;
        int bitSize = 0;

        System.out.println(num);
        while (num != Integer.MAX_VALUE - 1) {
            num += (int) Math.pow(2, power);
            System.out.println(num);
            power++;
            bitSize++;
        }

        System.out.println("Bit length: " + bitSize);
    }

}

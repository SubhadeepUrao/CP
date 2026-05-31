public class Main {
    private static void solve(FastScanner sc) {
        int target = sc.nextInt();

        int fours = 0;
        int sevens = target / 7;

        if ((target & 1) == 0) { // even --> #7's must be even
            if ((sevens & 1) == 1) // if odd 7s
                --sevens;
        } else { // odd --> #7's must be odd
            if ((sevens & 1) == 0) // if even 7s
                --sevens;
        }

        while (sevens >= 0) {
            int rem = target - 7 * sevens;
            if (rem % 4 == 0) {
                fours = rem / 4;
                break;
            }
            sevens -= 2;
        }

        if (sevens * 7 + fours * 4 == target) {
            while (fours-- > 0)
                System.out.print(4);
            while (sevens-- > 0)
                System.out.print(7);
        } else
            System.out.println(-1);
    }
}
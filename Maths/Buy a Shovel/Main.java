public class Main {
    private static void solve(FastScanner sc) {
        int k = sc.nextInt();
        int r = sc.nextInt();

        for (int n = 1; n <= 10; ++n) {
            int cost = n * k;
            if (cost % 10 == 0 || cost % 10 == r) {
                System.out.println(n);
                return;
            }
        }
    }

    private static void solve(FastScanner sc) {
        int k = sc.nextInt();
        int r = sc.nextInt();

        int last = k % 10;

        // Quick exit: if 1 shovel already hits a 0 or r suffix
        if (last == 0 || last == r) {
            System.out.println(1);
            return;
        }

        // Lookup table for modular inverses modulo 10 (used for odd digits)
        int[] inv10 = new int[10];
        inv10[1] = 1;
        inv10[3] = 7;
        inv10[7] = 3;
        inv10[9] = 9;

        // Lookup table for modular inverses modulo 5 (used for halved even digits)
        int[] inv5 = new int[5];
        inv5[1] = 1;
        inv5[2] = 3;
        inv5[3] = 2;
        inv5[4] = 4;

        if (last == 5) {
            // Since 'last == r' was checked above, r is definitely not 5 here.
            // Any number ending in 5 multiplied by 2 ends in 0.
            System.out.println(2);
        } else if (last % 2 != 0) {
            // Coprime odd numbers: 1, 3, 7, 9
            int ans = (r * inv10[last]) % 10;
            System.out.println(ans);
        } else {
            // Even numbers: 2, 4, 6, 8
            if (r % 2 == 0) {
                // Scale down the equation by dividing the elements by GCD (2)
                int scaledK = last / 2;
                int scaledR = r / 2;

                int ans = (scaledR * inv5[scaledK]) % 5;
                System.out.println(ans);
            } else {
                // Even numbers multiplied by any integer can never yield an odd 'r'.
                // They must default to 5 shovels to force a '0' suffix.
                System.out.println(5);
            }
        }
    }
}
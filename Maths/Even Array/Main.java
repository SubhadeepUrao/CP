public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] num = new int[n];
            for (int i = 0; i < n; ++i)
                num[i] = sc.nextInt();

            int even = 0, odd = 0;
            for (int i = 0; i < n; ++i) {
                if ((i & 1) == 0 && (num[i] & 1) == 1) // even index <= odd number
                    ++even;
                else if ((i & 1) == 1 && (num[i] & 1) == 0) // odd index <= even number
                    ++odd;
            }
            System.out.println(even == odd ? even : -1);
        }
    }
}
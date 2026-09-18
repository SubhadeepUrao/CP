public class Main {

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        int[] A = new int[n];
        int ones = 0;
        for (int i = 0; i < n; ++i) {
            A[i] = sc.nextInt();
            if (A[i] > 0)
                ++ones;
        }

        int maxGain = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = 0; i < n; ++i) {
            int val = A[i] == 1 ? -1 : 1; // flip
            currSum = Math.max(val, currSum + val);
            maxGain = Math.max(maxGain, currSum);
        }
        System.out.println(ones + maxGain);
    }
}
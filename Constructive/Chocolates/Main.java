public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int[] choco = new int[n];
        for (int i = 0; i < n; ++i)
            choco[i] = sc.nextInt();

        long bought = choco[n - 1];
        long prev = bought;

        for (int i = n - 2; prev > 0 && i >= 0; --i) {
            prev = Math.min(choco[i], prev - 1);
            bought += prev;
        }

        System.out.println(bought);
    }
}
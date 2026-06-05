public class Main {
    private static void solve(FastScanner sc) {
        int[] secret = new int[4];
        int maxi = 0;
        for (int i = 0; i < 4; ++i) {
            secret[i] = sc.nextInt();
            maxi = Math.max(maxi, secret[i]);
        }

        for (int i = 0; i < 4; ++i)
            if (secret[i] != maxi)
                System.out.println(maxi - secret[i]);

    }
}
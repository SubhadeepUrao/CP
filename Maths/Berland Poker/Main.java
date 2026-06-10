public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();

            int cards = n / k;
            if (m <= cards)
                System.out.println(m);
            else {
                int rem_jokers = m - cards;
                System.out.println(cards - (rem_jokers + k - 2) / (k - 1));
            }
        }
    }
}
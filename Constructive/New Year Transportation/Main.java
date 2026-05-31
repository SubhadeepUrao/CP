public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int t = sc.nextInt();
        int[] cell = new int[n + 1];

        for (int i = 1; i < n; ++i)
            cell[i] = sc.nextInt();

        int curr = 1;
        while (curr < t) {
            curr += cell[curr];
        }
        if (curr == t) System.out.println("YES");
        else System.out.println("NO");
    }
}
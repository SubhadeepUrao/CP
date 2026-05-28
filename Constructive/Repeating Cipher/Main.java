public class Main {
    private static void solve(FastScanner sc) {
        int N = sc.nextInt();
        char[] str = sc.next().toCharArray();

        for (int i = 0, j = 0; i < N; ++j, i += j) System.out.print(str[i]);
    }
}
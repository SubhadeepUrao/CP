public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            char[] str = sc.next().toCharArray();
            int x = str[0] - '0';
            int n = str.length;
            int res = (x - 1) * 10 + (n * (n + 1) >> 1);
            System.out.println(res);
        }
    }
}
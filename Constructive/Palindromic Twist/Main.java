public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int N = sc.nextInt();
            char[] str = sc.next().toCharArray();

            int i = 0, j = N - 1;

            while (i < j) {
                int x = str[i] - 'a';
                int y = str[j] - 'a';
                if (x == y) {
                    ++i;
                    --j;
                    continue;
                }

                if (x > y) {
                    int temp = x;
                    x = y;
                    y = temp;
                }

                if ((x + 1 != y - 1)) {
                    System.out.println("NO");
                    break;
                }
                ++i;
                --j;
            }
            if (i > j)
                System.out.println("YES");
        }
    }
}
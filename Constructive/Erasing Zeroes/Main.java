public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            char[] str = sc.next().toCharArray();
            int N = str.length;
            int i = 0;
            while (i < N && str[i++] == '0');

            int count = 0, total = 0;
            while (i < N) {
                if (str[i++] == '1') {
                    total += count;
                    count = 0;
                } else
                    ++count;
            }

            System.out.println(total);
        }
    }
}
public class Main {
    private static void solve(FastScanner sc) {
        int N = sc.nextInt();
        char[] str = sc.next().toCharArray();

        int[] freq = new int[26];

        for (int i = 0; i < N; ++i) {
            Arrays.fill(freq, 0);
            int maxlen = 0;
            for (int j = i; j < N; ++j) {
                int thresh = (j - i + 1) >> 1;
                maxlen = Math.max(++freq[str[j] - 'a'], maxlen);
                if (maxlen <= thresh) {
                    System.out.println("YES");
                    System.out.println(new String(str, i, j - i + 1));
                    return;
                }
            }
        }
        System.out.println("NO");
    }
}
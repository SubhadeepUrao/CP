public class Main {
    private static void solve(FastScanner sc) {
        int k = sc.nextInt();
        char[] str = sc.next().toCharArray();

        if (k == 1) {
            System.out.println(str);
            return;
        }

        int N = str.length;
        int[] freq = new int[26];

        for (int i = 0; i < N; ++i)
            ++freq[str[i] - 'a'];

        char[] st = new char[N / k];
        int p = 0;

        for (int i = 0; i < 26; ++i) {
            if (freq[i] % k == 0) {
                for (int j = freq[i] / k; j > 0; --j)
                    st[p++] = (char) ('a' + i);
            } else {
                System.out.println(-1);
                return;
            }
        }
        for (int i = 0; i < k; ++i)
            System.out.print(st);
    }
}
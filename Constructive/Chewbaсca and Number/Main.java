public class Main {
    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        int N = str.length;

        int i = 0;
        if (str[0] == '9') ++i;

        for (; i < N; ++i) {
            int ch = str[i] - 48;
            if (ch > 4) str[i] = (char) (57 - ch);
        }

        System.out.println(String.valueOf(str));
    }
}
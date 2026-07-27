class Main {
    public static char[] transform(char[] s) {
        int n = s.length;
        char[] str = new char[2 * n + 1];
        str[0] = '#';
        for (int i = 0; i < n; ++i) {
            str[2 * i + 1] = s[i];
            str[2 * i + 2] = '#';
        }
        return str;
    }

    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;
        StringBuilder res = new StringBuilder();
        while ((s = br.readLine()) != null) {
            if (s.isEmpty())
                continue;

            char[] str = transform(s.toCharArray());
            int n = str.length;

            int[] P = new int[n];
            P[0] = 0;
            int l = 0, r = 0;
            int maxlen = 0;

            for (int i = 1; i < n; ++i) {
                int k;

                if (r < i) {
                    k = 1;
                } else {
                    int j = l + r - i;
                    k = Math.min(P[j] + 1, r - i + 1);
                }

                while (k <= i && i + k < n && str[i - k] == str[i + k])
                    ++k;
                --k;
                P[i] = k;

                if (r < i + k) {
                    l = i - k;
                    r = i + k;
                }

                if (i + k == n - 1 && maxlen < k)
                    maxlen = k;
            }
            StringBuilder palin = new StringBuilder(s.substring(0, s.length() - maxlen)).reverse();
            res.append(s).append(palin).append('\n');
        }
        System.out.println(res);
    }
}
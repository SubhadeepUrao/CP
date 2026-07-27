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
        int t = Integer.parseInt(br.readLine());

        StringBuilder res = new StringBuilder();
        while (t-- > 0) {
            String s = br.readLine();
            char[] str = transform(s.toCharArray());
            int n = str.length;

            int[] P = new int[n];
            P[0] = 0;
            int l = 0, r = 0;
            int maxlen = 0, count = 1;

            for (int i = 1; i < n; ++i) {
                int k;
                if (r < i) {
                    k = 1;
                } else {
                    int j = l + r - i; // mirrir image of i within the boundary [l, r]
                    k = Math.min(r - i + 1, P[j] + 1);
                }

                while (0 <= i - k && i + k < n && str[i - k] == str[i + k])
                    ++k;
                --k;
                P[i] = k;

                if (r < i + k) {
                    l = i - k;
                    r = i + k;
                }

                if (maxlen < k) {
                    maxlen = k;
                    count = 1;
                } else if (maxlen == k)
                    ++count;
            }
            res.append(maxlen).append(' ').append(count).append('\n');
        }
        System.out.println(res);
    }
}
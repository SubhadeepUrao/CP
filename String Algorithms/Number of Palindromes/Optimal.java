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
        String s = br.readLine();

        char[] str = transform(s.toCharArray());
        int n = str.length;

        // stores the length it can expand towards left and right
        int[] P = new int[n];
        P[0] = 0;
        int l = 0, r = 0;
        int count = 0;

        for (int i = 1; i < n; ++i) {
            int k;
            if (r < i) {
                k = 1;
            } else {
                int j = l + r - i; // mirror image of i between [l, r]

                // expansion of palindrome centered at j is greater than l
                if (l < j - P[j]) {
                    P[i] = P[j];
                    count += (P[i] + 1) / 2;
                    continue;
                } else {
                    k = r - i + 1;
                }
            }

            while (0 <= i - k && i + k < n && str[i - k] == str[i + k]) {
                ++k;
            }
            --k;
            P[i] = k;

            // expansion of i exceeds right boundary then update the old boundary
            if (r < i + k) {
                l = i - k;
                r = i + k;
            }

            // P[i] is the radius in the transformed string
            count += (P[i] + 1) / 2;
        }
        System.out.println(count);
    }
}
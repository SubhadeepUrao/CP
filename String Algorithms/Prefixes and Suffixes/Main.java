public class Main {
    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        int n = str.length;

        // compute LPS
        int[] LPS = new int[n];
        LPS[0] = 0; // base case
        int i = 1;
        int len = 0;

        while (i < n) {
            if (str[len] == str[i]) {
                ++len;
                LPS[i] = len;
                ++i;
            } else {
                if (len > 0)
                    len = LPS[len - 1];
                else
                    ++i;
            }
        }

        int[] count = new int[n + 1];
        // each prefix exist atleast once
        Arrays.fill(count, 1);

        for (int j = n; j > 0; --j) {
            if (LPS[j - 1] > 0)
                count[LPS[j - 1]] += count[j];
        }

        List<Integer> valid = new ArrayList<>();
        int curr = n;
        while (curr > 0) {
            valid.add(curr);
            curr = LPS[curr - 1];
        }

        StringBuilder res = new StringBuilder();

        int total = valid.size();
        res.append(total).append('\n');

        for (int j = total - 1; j >= 0; --j) {
            int k = valid.get(j);
            res.append(k).append(' ').append(count[k]).append('\n');
        }

        System.out.println(res);
    }
}
public class Main {
    private static long BASE = 131;
    private static long MOD = 1_000_000_007;

    private static long computeHash(char[] str) {
        long forwardHash = 0;
        // forward hash in reverse == backward hash
        for (int i = str.length - 1; i >= 0; --i) {
            forwardHash = (forwardHash * BASE + str[i]) % MOD;
        }
        return forwardHash;
    }

    private static long computeRangeHash(long[] hash, long[] power, int l, int r) {
        long rangeHash = (hash[r] - (hash[l] * power[r - l]) % MOD) % MOD;
        if (rangeHash < 0)
            rangeHash += MOD;
        return rangeHash;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        char[] cipher = sc.next().toCharArray();

        HashMap<Long, Integer> map = new HashMap<>();

        int m = sc.nextInt();
        String[] dict = new String[m];
        for (int i = 0; i < m; ++i) {
            dict[i] = sc.next();
            long hash = computeHash(dict[i].toLowerCase().toCharArray());
            map.put(hash, i);
        }

        // precompute powers of BASE and prefix hashes of cipher
        long[] power = new long[n + 1];
        long[] hash = new long[n + 1];
        power[0] = 1;

        for (int i = 0; i < n; ++i) {
            hash[i + 1] = (hash[i] * BASE + cipher[i]) % MOD;
            power[i + 1] = (power[i] * BASE) % MOD;
        }

        // dp[i] stores the index of word ending at cipher index i-1
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0; // base case

        for (int i = 0; i < n; ++i) {
            // Only extend from valid/reachable prefixes
            if (dp[i] == -1)
                continue;

            int maxlen = Math.min(n, i + 1000);
            for (int j = i + 1; j <= maxlen; ++j) {
                long rangeHash = computeRangeHash(hash, power, i, j);

                if (map.containsKey(rangeHash))
                    dp[j] = map.get(rangeHash);
            }
        }

        List<String> sentenceRev = new ArrayList<>();
        int curr = n;

        while (curr > 0) {
            int idx = dp[curr];
            String word = dict[idx];
            sentenceRev.add(word);
            curr -= word.length();
        }

        StringBuilder res = new StringBuilder();
        for (int i = sentenceRev.size() - 1; i >= 0; --i) {
            res.append(sentenceRev.get(i)).append(' ');
        }
        System.out.println(res);
    }
}
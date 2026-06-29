public class Main {

    private static int ans = 0;
    private static final int MAX = 2_00_000;
    private static List<Integer>[] primeFactors, adj;
    private static int[] A;

    @SuppressWarnings("unchecked")
    private static void preCompute() {
        int[] spf = new int[MAX + 1];
        primeFactors = new ArrayList[MAX + 1];

        // fast sieve
        for (int i = 1; i <= MAX; ++i) {
            if ((i & 1) == 0)
                spf[i] = 2;
            else
                spf[i] = i;
        }
        for (int p = 3; p * p <= MAX; p += 2) {
            if (spf[p] == p) {
                int stride = p << 1;
                for (int i = p * p; i <= MAX; i += stride) {
                    if (spf[i] == i)
                        spf[i] = p;
                }
            }
        }

        // find all prime factors
        for (int n = 1; n <= MAX; ++n) {
            primeFactors[n] = new ArrayList<>();

            int temp = n;
            while (temp > 1) {
                int p = spf[temp];
                primeFactors[n].add(p);
                do {
                    temp /= p;
                } while (temp % p == 0);
            }
        }
    }

    private static class Pair {
        private int prime, maxlen;

        Pair(int prime, int maxlen) {
            this.prime = prime;
            this.maxlen = maxlen;
        }
    }

    private static void dfs(int u, int p, List<Pair>[] dp) {
        // populate current node's DP states
        for (int prime : primeFactors[A[u]]) {
            dp[u].add(new Pair(prime, 1));
            ans = Math.max(ans, 1);
        }

        for (int v : adj[u]) {
            if (v == p)
                continue;

            dfs(v, u, dp);

            // Merge child v's valid paths into parent u
            for (Pair parent : dp[u]) {
                for (Pair child : dp[v]) {
                    if (parent.prime == child.prime) {
                        // Max path combining parent path and current child branch
                        ans = Math.max(ans, parent.maxlen + child.maxlen);
                        // Update the longest downward branch tracking
                        parent.maxlen = Math.max(parent.maxlen, child.maxlen + 1);
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void solve(FastScanner sc) {

        preCompute();

        int n = sc.nextInt();
        A = new int[n + 1];
        for (int i = 1; i <= n; ++i)
            A[i] = sc.nextInt();

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i)
            adj[i] = new ArrayList<>();
        for (int i = 1; i < n; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        List<Pair>[] dp = new ArrayList[n + 1];
        for (int i = 1; i <= n; ++i)
            dp[i] = new ArrayList<>();

        dfs(1, 0, dp);

        System.out.println(ans);
    }
}
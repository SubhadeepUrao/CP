public class Main {
    private static int MOD = 1_000_000_007;

    private static long power(long base, long exp) {
        long res = 1l;
        while (exp > 0) {
            if ((exp & 1) == 1) // odd
                res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

    private static void solve(FastScanner sc) {
        int x = sc.nextInt();
        long n = sc.nextLong();

        List<Integer> primes = new ArrayList<>();

        // find all prime factors of x
        int temp = x;
        if ((temp & 1) == 0) {
            primes.add(2);
            do {
                temp >>= 1;
            } while ((temp & 1) == 0);
        }
        for (int p = 3; p * p <= x; p += 2) {
            if (temp % p == 0) {
                primes.add(p);
                do {
                    temp /= p;
                } while (temp % p == 0);
            }
        }
        if (temp > 1)
            primes.add(temp);

        long ans = 1l;

        for (long p : primes) {
            long curr = n;
            long exp = 0;
            while (curr > 0) {
                curr /= p;
                exp += curr;
            }
            ans = (ans * power(p, exp)) % MOD;
        }
        System.out.println(ans);
    }
}
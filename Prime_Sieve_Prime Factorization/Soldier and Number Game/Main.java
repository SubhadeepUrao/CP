public class Main {
    private static void solve(FastScanner sc) {
        int LIMIT = 5_000_000;
        // find all primes <= LIMIT
        boolean[] isComposite = new boolean[LIMIT / 2 + 1];
        int maxfactor = (int) Math.sqrt(LIMIT);

        for (int p = 3; p <= maxfactor; p += 2) {
            if (!isComposite[p >> 1]) {
                int stride = p << 1;
                for (int i = p * p; i <= LIMIT; i += stride)
                    isComposite[i >> 1] = true;
            }
        }

        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (a == 1) {
                out.println(0);
                return;
            }

            int round = 0;
            for (int curr = a; curr > b; --curr) {
                int temp = curr;
                while (temp % 2 == 0) {
                    ++round;
                    temp /= 2;
                }
                for (int p = 3; p * p <= temp && temp > 0; p += 2) {
                    if (!isComposite[p >> 1] && temp % p == 0) {
                        do {
                            ++round;
                            temp /= p;
                        } while (temp % p == 0);
                    }
                }
                if (temp > 2 && !isComposite[temp >> 1])
                    ++round;
            }
            out.println(round);
        }
        out.flush();
    }
}
import java.util.*;
import java.io.*;

class Main {
    private static final int MAX_LIMIT = 31622; // sqrt(10^9)
    private static List<Integer> primes = new ArrayList<>();

    // Step 1: Precompute primes up to sqrt(10^9) once
    private static void precomputePrimes() {
        boolean[] isPrime = new boolean[MAX_LIMIT + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int p = 2; p * p <= MAX_LIMIT; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= MAX_LIMIT; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        for (int i = 2; i <= MAX_LIMIT; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
    }

    // Step 2: Use a Segmented Sieve for the given range [m, n]
    private static void printPrimeRange(int m, int n, PrintWriter out) {
        if (m < 2) m = 2; // 0 and 1 are not primes
        if (m > n) return;

        int rangeSize = n - m + 1;
        boolean[] isPrimeRange = new boolean[rangeSize];
        Arrays.fill(isPrimeRange, true);

        // Map every prime factor to its first crossing multiple inside [m, n]
        for (int prime : primes) {
            if ((long) prime * prime > n) break;

            // Find the smallest multiple of 'prime' that is >= m
            int start = (m / prime) * prime;
            if (start < m) {
                start += prime;
            }
            // Ensure we don't accidentally mark the prime itself as composite
            if (start == prime) {
                start += prime;
            }

            // Mark all multiples in the range [m, n]
            for (long j = start; j <= n; j += prime) {
                isPrimeRange[(int) (j - m)] = false;
            }
        }

        // Output all numbers that remained true
        for (int i = 0; i < rangeSize; i++) {
            if (isPrimeRange[i]) {
                out.println(m + i);
            }
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        // Fast I/O is highly recommended since SPOJ has massive output data
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        precomputePrimes();

        String line = br.readLine();
        if (line == null) return;
        int t = Integer.parseInt(line.trim());

        while (t-- > 0) {
            line = br.readLine();
            while (line != null && line.trim().isEmpty()) {
                line = br.readLine(); // Skip any blank lines
            }
            if (line == null) break;

            String[] parts = line.trim().split("\\s+");
            int m = Integer.parseInt(parts[0]);
            int n = Integer.parseInt(parts[1]);

            printPrimeRange(m, n, out);
            
            // SPOJ requires an empty line between test case outputs
            if (t > 0) {
                out.println();
            }
        }
        out.flush(); // Crucial to actually print everything out safely
    }
}
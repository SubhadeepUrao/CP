import java.io.*;
import java.util.*;

class Main {
    // The 5,000,000th prime is 86,028,121
    private static final int LIMIT = 86028122;
    private static final int MAX_K = 5000000;

    // Store the actual primes (1-indexed: primes[1] = 2, primes[2] = 3...)
    private static final int[] primes = new int[MAX_K + 1];

    private static void precomputePrimes() {
        int size = LIMIT / 2;
        boolean[] isComp = new boolean[size];
        int maxFact = (int) Math.sqrt(LIMIT);

        // Standard Odd-Only Sieve Core
        for (int p = 3; p <= maxFact; p += 2) {
            if (!isComp[p >> 1]) {
                int stride = p << 1;
                for (int i = p * p; i < LIMIT; i += stride) {
                    isComp[i >> 1] = true;
                }
            }
        }

        // Populate our lookup array
        primes[1] = 2;
        int count = 1;

        for (int i = 1; i < size && count < MAX_K; i++) {
            if (!isComp[i]) {
                count++;
                primes[count] = (i << 1) + 1; // Reconstruct odd prime
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // Initialize Fast I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        // Step 1: Run the sieve ONCE before answering queries
        precomputePrimes();

        // Step 2: Parse total queries
        String line = br.readLine();
        if (line == null)
            return;

        StringTokenizer st = new StringTokenizer(line);
        if (!st.hasMoreTokens())
            return;
        int q = Integer.parseInt(st.nextToken());

        // Step 3: Handle all 50,000 queries in O(1) time each
        for (int i = 0; i < q; i++) {
            line = br.readLine();
            if (line == null)
                break;
            st = new StringTokenizer(line);
            if (st.hasMoreTokens()) {
                int k = Integer.parseInt(st.nextToken());
                // Instantly fetch from precomputed array
                out.println(primes[k]);
            }
        }

        // Flush output buffer to prevent missing text
        out.flush();
    }
}
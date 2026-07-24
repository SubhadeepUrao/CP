import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;
 
public class Main {
    // Two large primes for modular arithmetic to avoid collisions
    static final long MOD1 = 1_000_000_007L;
    static final long MOD2 = 1_000_000_009L;
    
    static final long BASE1 = 31L;
    static final long BASE2 = 37L;
 
    static long[] pow1;
    static long[] pow2;
 
    // Custom class to store Double Hash pair
    static class HashPair {
        long h1, h2;
 
        HashPair(long h1, long h2) {
            this.h1 = h1;
            this.h2 = h2;
        }
 
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HashPair hashPair = (HashPair) o;
            return h1 == hashPair.h1 && h2 == hashPair.h2;
        }
 
        @Override
        public int hashCode() {
            return Objects.hash(h1, h2);
        }
    }
 
    public static void main(String[] args) {
        FastScanner sc = new FastScanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
 
        // Precompute powers of base
        int MAX_LEN = 600_005;
        pow1 = new long[MAX_LEN];
        pow2 = new long[MAX_LEN];
        pow1[0] = 1;
        pow2[0] = 1;
 
        for (int i = 1; i < MAX_LEN; i++) {
            pow1[i] = (pow1[i - 1] * BASE1) % MOD1;
            pow2[i] = (pow2[i - 1] * BASE2) % MOD2;
        }
 
        HashSet<HashPair> dictHashes = new HashSet<>();
 
        // Store hashes of all dictionary strings
        for (int i = 0; i < n; i++) {
            char[] str = sc.next().toCharArray();
            dictHashes.add(computeHash(str));
        }
 
        StringBuilder sb = new StringBuilder();
 
        // Process queries
        while (m-- > 0) {
            char[] str = sc.next().toCharArray();
            HashPair originalHash = computeHash(str);
            boolean found = false;
 
            // Try changing 1 character at position j
            for (int j = 0; j < str.length && !found; j++) {
                char origChar = str[j];
 
                for (char ch = 'a'; ch <= 'c'; ch++) {
                    if (ch == origChar) continue;
 
                    // Calculate hash shift in O(1)
                    long diff = (ch - origChar);
 
                    long newH1 = (originalHash.h1 + diff * pow1[j]) % MOD1;
                    if (newH1 < 0) newH1 += MOD1;
 
                    long newH2 = (originalHash.h2 + diff * pow2[j]) % MOD2;
                    if (newH2 < 0) newH2 += MOD2;
 
                    if (dictHashes.contains(new HashPair(newH1, newH2))) {
                        found = true;
                        break;
                    }
                }
            }
 
            if (found) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
 
        System.out.print(sb);
    }
 
    private static HashPair computeHash(char[] str) {
        long h1 = 0, h2 = 0;
        for (int i = 0; i < str.length; i++) {
            h1 = (h1 + (str[i] - 'a' + 1) * pow1[i]) % MOD1;
            h2 = (h2 + (str[i] - 'a' + 1) * pow2[i]) % MOD2;
        }
        return new HashPair(h1, h2);
    }
 
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
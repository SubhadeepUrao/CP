import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        // Using Fast Scanner to handle large inputs efficiently
        FastScanner sc = new FastScanner();
        solve(sc);
    }

    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int U = sc.nextInt();
            int R = sc.nextInt();
            int D = sc.nextInt();
            int L = sc.nextInt();

            int remSlot = n - 2;
            boolean possible = false;

            for (int mask = 0; mask < 16; ++mask) {
                int currU = U;
                int currR = R;
                int currD = D;
                int currL = L;

                if ((mask & 1) > 0) {
                    --currU;
                    --currL;
                }
                if ((mask & 2) > 0) {
                    --currU;
                    --currR;
                }
                if ((mask & 4) > 0) {
                    --currR;
                    --currD;
                }
                if ((mask & 8) > 0) {
                    --currD;
                    --currL;
                }

                if (currU >= 0 && currU <= remSlot &&
                        currR >= 0 && currR <= remSlot &&
                        currD >= 0 && currD <= remSlot &&
                        currL >= 0 && currL <= remSlot) {
                    possible = true;
                    break;
                }
            }

            if (possible)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    // Fast I/O Utility Class
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        boolean hasNext() {
            while (!st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null)
                        return false;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return false;
                }
            }
            return true;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
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
        int q = sc.nextInt();
        StringBuilder str = new StringBuilder();
        while (q-- > 0) {
            long u = sc.nextLong();
            long v = sc.nextLong();

            if (u > v)
                str.append("NO").append('\n');
            else {
                int countU = 0, countV = 0;
                boolean possible = true;
                while (v > 0) {
                    if ((u & 1) == 1)
                        ++countU;
                    if ((v & 1) == 1)
                        ++countV;
                    if (countU < countV) {
                        possible = false;
                        break;
                    }
                    u >>>= 1;
                    v >>>= 1;
                }
                if (possible)
                    str.append("YES").append('\n');
                else
                    str.append("NO").append('\n');
            }
        }
        System.out.println(str);
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
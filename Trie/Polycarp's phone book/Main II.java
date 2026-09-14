import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        // Using Fast Scanner to handle large inputs efficiently
        FastScanner sc = new FastScanner();
        solve(sc);
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        String[] phoneNumbers = new String[n];

        Map<String, Integer> freq = new HashMap<>();
        Set<String> unique = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            phoneNumbers[i] = sc.next();

            for (int l = 0; l < 9; ++l) {
                for (int r = l; r < 9; ++r) {
                    unique.add(phoneNumbers[i].substring(l, r + 1));
                }
            }

            for (String substring : unique)
                freq.put(substring, freq.getOrDefault(substring, 0) + 1);

            unique.clear();
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < n; ++i) {
            String best = phoneNumbers[i];
            boolean foundBest = false;
            for (int len = 1; len <= 9; ++len) {
                for (int start = 0; start <= 9 - len; ++start) {
                    String subString = phoneNumbers[i].substring(start, start + len);
                    if (freq.get(subString) == 1) {
                        best = subString;
                        foundBest = true;
                        break;
                    }
                }
                if (foundBest)
                    break;
            }
            res.append(best).append('\n');
        }
        System.out.println(res);
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
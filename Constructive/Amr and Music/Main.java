import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        // Using Fast Scanner to handle large inputs efficiently
        FastScanner sc = new FastScanner();
        solve(sc);
    }

    private static class Instrument {
        int index;
        int days;

        Instrument(int index, int days) {
            this.days = days;
            this.index = index;
        }
    }

    private static void solve(FastScanner sc) {
        int N = sc.nextInt();
        int k = sc.nextInt();

        List<Instrument> instruments = new ArrayList<>();
        for (int i = 1; i <= N; ++i)
            instruments.add(new Instrument(i, sc.nextInt()));

        // Collections.sort(instruments, (i, j) -> i.days == j.days ? 0 : i.days > j.days ? 1 : -1);
        Collections.sort(instruments, (i, j) -> Integer.compare(i.days, j.days));
        // Collections.sort(instruments, Comparator.comparingInt(i -> i.days));

        Instrument curr = instruments.get(0);
        int total = k;
        int count = 0;
        while (count < N) {
            curr = instruments.get(count);
            if (curr.days <= total)
                total -= curr.days;
            else
                break;
            ++count;
        }

        System.out.println(count);
        if (count > 0) {
            System.out.print(instruments.get(0).index);
            for (int i = 1; i < count; ++i) {
                System.out.print(" " + instruments.get(i).index);
            }
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
public class Main {
    static class Skyscraper {
        long count;
        long height;

        Skyscraper(long count, long height) {
            this.count = count;
            this.height = height;
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        long[] m = new long[n];

        for (int i = 0; i < n; ++i)
            m[i] = sc.nextLong();

        Stack<Skyscraper> stack = new Stack<>();

        // generate prefix sums
        long[] prefix = new long[n];
        long sum = 0l;
        for (int i = 0; i < n; ++i) {
            long curr = m[i];
            long count = 1;
            while (!stack.isEmpty()) {
                Skyscraper top = stack.peek();
                if (top.height > curr) {
                    stack.pop();
                    sum -= top.count * top.height;
                    count += top.count;
                } else
                    break;
            }

            sum += count * curr;
            prefix[i] = sum;
            stack.push(new Skyscraper(count, curr));
        }

        stack.clear();
        sum = 0l;

        // generate suffix sums
        long[] suffix = new long[n];
        for (int k = n - 1; k >= 0; --k) {
            long curr = m[k];
            long count = 1;
            while (!stack.isEmpty()) {
                Skyscraper top = stack.peek();
                if (top.height > curr) {
                    stack.pop();
                    sum -= top.count * top.height;
                    count += top.count;
                } else
                    break;
            }

            sum += count * curr;
            suffix[k] = sum;
            stack.push(new Skyscraper(count, curr));
        }

        // find the optimal peak m[i] i.e. skyscraper
        long maxsum = 0l;
        int peak = 0; // tracks peak optimal skyscraper
        for (int i = 0; i < n; ++i) {
            long currsum = prefix[i] + suffix[i] - m[i]; // reducing m[i] bcoz of double counting
            if (currsum > maxsum) {
                maxsum = currsum;
                peak = i;
            }
        }

        // construct the result
        long[] res = new long[n];
        res[peak] = m[peak];
        for (int i = peak - 1; i >= 0; --i)
            res[i] = Math.min(m[i], res[i + 1]);
        for (int k = peak + 1; k < n; ++k)
            res[k] = Math.min(m[k], res[k - 1]);

        for (int i = 0; i < n; ++i)
            System.out.print(res[i] + " ");
    }
}
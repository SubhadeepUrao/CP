public class Main {
    private static int powerBase2(int exp) {
        int res = 1;
        int base = 2;
        while (exp > 0) {
            if ((exp & 1) == 1)
                res *= base;
            base *= base;
            exp >>= 1;
        }
        return res;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] freq = new int[30];

        int temp = n;
        int bitIndex = 0, count = 0;
        while (temp > 0) {
            bitIndex = Integer.numberOfTrailingZeros(temp);
            ++freq[bitIndex];
            temp &= temp - 1;
            ++count;
        }

        if (count > k) {
            System.out.println("NO");
            return;
        }
        while (count < k) {
            if (bitIndex == 0) {
                System.out.println("NO");
                return;
            }
            if (freq[bitIndex] > 0) {
                --freq[bitIndex];
                freq[bitIndex - 1] += 2;
                ++count;
            } else
                --bitIndex;
        }

        StringBuilder str = new StringBuilder();
        str.append("YES\n");
        for (int i = bitIndex; i >= 0; --i) {
            if (freq[i] > 0) {
                int power = powerBase2(i);
                for (int j = 0; j < freq[i]; ++j)
                    str.append(power).append(' ');
            }
        }
        System.out.println(str);
    }
}
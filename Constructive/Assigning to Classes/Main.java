import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static void solve(FastScanner sc) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int N = n << 1;
            int[] nums = new int[N];
            for (int i = 0; i < N; ++i)
                nums[i] = sc.nextInt();

            Arrays.sort(nums);

            System.out.println(nums[n] - nums[n - 1]);
        }
    }
}
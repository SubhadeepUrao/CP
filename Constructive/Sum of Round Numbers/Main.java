public class Main {
    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        int N = str.length;

        int count = 0;
        for (int d : str)
            if (d > '0')
                ++count;
        System.out.println(count);

        char[] nums = new char[N];
        Arrays.fill(nums, '0');
        
        for (int i = 0; i < N; ++i) {
            char d = str[i];
            if (d > '0') {
                nums[i] = d;
                System.out.print(new String(nums, i, N - i));
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
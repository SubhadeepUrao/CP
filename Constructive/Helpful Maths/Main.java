public class Main {
    private static void solve(FastScanner sc) {
        char[] str = sc.next().toCharArray();
        int N = str.length;
        char[] nums = new char[N + 1 >> 1];

        for (int i = 0, j = 0; i < N; i += 2, ++j)
            nums[j] = str[i];

        Arrays.sort(nums);

        for (int i = 0, j = 0; i < N; i += 2, ++j)
            str[i] = nums[j];

        System.out.println(str);
    }
}
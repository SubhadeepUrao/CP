public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; ++i)
            num[i] = sc.nextInt();

        Arrays.sort(num);

        // swapping last and second last
        int temp = num[n - 1];
        num[n - 1] = num[n - 2];
        num[n - 2] = temp;

        if (num[n - 2] < num[n - 3] + num[n - 1]) {
            System.out.println("YES");
            System.out.print(num[0]);
            for (int i = 1; i < n; ++i)
                System.out.print(" " + num[i]);
        } else
            System.out.println("NO");
    }
}
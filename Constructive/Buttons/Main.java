public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int sum = 0;
        for (int i = n - 1; i >= 0; --i)
            sum += i * (n - i);
        sum += n;
        System.out.println(sum);
    }
}
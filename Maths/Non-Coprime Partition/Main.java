public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        if (n < 3)
            System.out.println("No");
        else {
            System.out.println("Yes");

            // print even numbers
            System.out.print(n >> 1);
            for (int i = 2; i <= n; i += 2)
                System.out.print(" " + i);
            System.out.println();

            // print odd numbers
            System.out.print(n - (n >> 1));
            for (int i = 1; i <= n; i += 2)
                System.out.print(" " + i);
        }
    }
}
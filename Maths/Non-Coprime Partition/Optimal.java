public class Optimal {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        if (n < 3)
            System.out.println("No");
        else {
            System.out.println("Yes");

            // print even numbers
            System.out.println(1 + " " + n);

            // print odd numbers
            System.out.print(n - 1);
            for (int i = 1; i < n; ++i)
                System.out.print(" " + i);
        }
    }
}
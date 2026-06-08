public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        while (n-- > 0) {
            char[] num = sc.next().toCharArray();

            int sum = 0;
            int zeros = 0;
            int evens = 0; // Track ALL even digits (including 0)

            for (char ch : num) {
                int digit = ch - '0';
                sum += digit;

                if (digit == 0) zeros++;
                if (digit % 2 == 0) evens++;
            }

            // Conditions for "red" (divisible by 60):
            // 1. Sum is divisible by 3
            // 2. Contains at least one '0'
            // 3. Contains at least two even digits (e.g., '0' and '2', or '0' and another '0')
            if (sum % 3 == 0 && zeros > 0 && evens >= 2) System.out.println("red");
            else System.out.println("cyan");
        }
    }
}
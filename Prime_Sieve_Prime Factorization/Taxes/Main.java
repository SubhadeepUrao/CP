// Goldbach's Conjecture
public class Main {
    private static boolean isPrimeModified(int n) {
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();

        if (n % 2 == 0 && n > 2) { // even => sum of 2 primes i.e. prime + prime
            System.out.println(2);
            return;
        }

        if (isPrimeModified(n)) {
            System.out.println(1);
        } else {
            if (isPrimeModified(n - 2)) // odd and composite => n = 2 + (n-2) => if (n-2) is prime => 2 + prime
                System.out.println(2);
            else // odd and composite => n = 3 + (n-3) => (n-3) is even => 2 + prime + prime
                System.out.println(3);
        }
    }
}
// TLE

public class Main {
    private static void populatePrimeFactors(int[] A, boolean[] isComposite, int[] primefactor_freq) {
        int n = A.length;
        for (int i = 0; i < n; ++i) {
            int temp = A[i];
            while (temp % 2 == 0) {
                ++primefactor_freq[0];
                temp /= 2;
            }
            for (int p = 3; p * p <= temp; p += 2) {
                if (!isComposite[p >> 1] && temp % p == 0) {
                    do {
                        ++primefactor_freq[p >> 1];
                        temp /= p;
                    } while (temp % p == 0);
                }
            }
            if (temp > 1)
                ++primefactor_freq[temp >> 1];
        }
    }

    private static int power(int base, int exp) {
        int res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) // is odd
                res *= base;
            base *= base;
            exp >>= 1; // half
        }
        return res;
    }

    private static int findMaxExp(int base) {
        return (int) (Math.log(1_0_000_000) / Math.log(base));
    }

    private static void addToList(List<Integer> list, int base, int exp) {
        int maxExp = findMaxExp(base);
        int rem = exp % maxExp;
        list.add(power(base, rem));

        exp /= maxExp;
        if (exp == 0)
            return;

        int value_exp10 = power(base, maxExp);
        while (exp > 0) {
            list.add(value_exp10);
            --exp;
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int m = sc.nextInt();

        int maxi = 0;
        int[] A = new int[n];
        for (int i = 0; i < n; ++i) {
            A[i] = sc.nextInt();
            maxi = Math.max(maxi, A[i]);
        }
        int[] B = new int[m];
        for (int i = 0; i < m; ++i) {
            B[i] = sc.nextInt();
            maxi = Math.max(maxi, B[i]);
        }

        int SIZE = maxi / 2 + 1;

        boolean[] isComposite = new boolean[SIZE];
        int maxfactor = (int) Math.sqrt(maxi);
        for (int p = 3; p <= maxfactor; p += 2) {
            if (!isComposite[p >> 1]) {
                int stride = p << 1;
                for (int i = p * p; i <= maxi; i += stride)
                    isComposite[i >> 1] = true;
            }
        }

        int[] A_primefactor_freq = new int[SIZE];
        int[] B_primefactor_freq = new int[SIZE];

        populatePrimeFactors(A, isComposite, A_primefactor_freq);
        populatePrimeFactors(B, isComposite, B_primefactor_freq);

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        listA.add(1);
        listB.add(1);

        if (!isComposite[0]) {
            if (A_primefactor_freq[0] < B_primefactor_freq[0]) {
                int exp = B_primefactor_freq[0] - A_primefactor_freq[0];
                addToList(listB, 2, exp);
            } else if (A_primefactor_freq[0] > B_primefactor_freq[0]) {
                int exp = A_primefactor_freq[0] - B_primefactor_freq[0];
                addToList(listA, 2, exp);
            }
        }
        for (int p = 3; p <= maxi; p += 2) {
            int i = p >> 1;
            if (!isComposite[i]) {
                if (A_primefactor_freq[i] < B_primefactor_freq[i]) {
                    int exp = B_primefactor_freq[i] - A_primefactor_freq[i];
                    addToList(listB, p, exp);
                } else if (A_primefactor_freq[i] > B_primefactor_freq[i]) {
                    int exp = A_primefactor_freq[i] - B_primefactor_freq[i];
                    addToList(listA, p, exp);
                }
            }
        }

        System.out.println(listA.size() + " " + listB.size());
        listA.forEach(num -> System.out.print(num + " "));
        System.out.println();
        listB.forEach(num -> System.out.print(num + " "));
    }
}
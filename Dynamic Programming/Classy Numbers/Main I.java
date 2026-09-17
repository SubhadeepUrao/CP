public class Main {
    
    static List<Long> classy = new ArrayList<>();

    // Backtracking to generate all numbers up to 18 digits with <= 3 non-zero digits
    private static void generate(int pos, int count, long currentNum) {
        if (pos == 18) {
            classy.add(currentNum);
            return;
        }

        // Option 1: Place digit '0'
        generate(pos + 1, count, currentNum * 10);

        // Option 2: Place digits '1' through '9' if count < 3
        if (count < 3) {
            for (int digit = 1; digit <= 9; digit++) {
                generate(pos + 1, count + 1, currentNum * 10 + digit);
            }
        }
    }

    // Custom upper_bound implementation (counts elements <= target)
    private static int countLessOrEqual(long target) {
        int low = 0, high = classy.size() - 1;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (classy.get(mid) <= target) {
                ans = mid + 1; // 1-based count of valid numbers
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // Step 1: Generate all classy numbers
        generate(0, 0, 0L);
        
        // Include 10^18 explicitly as a boundary
        classy.add(1000000000000000000L);

        // Step 2: Sort and remove duplicate entries (from trailing zeros)
        Collections.sort(classy);
        List<Long> uniqueClassy = new ArrayList<>();
        for (long num : classy) {
            if (uniqueClassy.isEmpty() || uniqueClassy.get(uniqueClassy.size() - 1) != num) {
                uniqueClassy.add(num);
            }
        }
        classy = uniqueClassy;

        // Step 3: Process queries efficiently
        FastScanner sc = new FastScanner();
        int t = sc.nextInt();
        StringBuilder res = new StringBuilder();

        while (t-- > 0) {
            long L = sc.nextLong();
            long R = sc.nextLong();

            int countR = countLessOrEqual(R);
            int countL = countLessOrEqual(L - 1);

            res.append(countR - countL).append('\n');
        }
        System.out.print(res);
    }
}
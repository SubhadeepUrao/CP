public class Main {
    private static double getMaxTemp(double V) {
        double totalEnergy = 0d;
        double currV = 0d;
        for (int i = n - 1; i >= 0; --i) {
            double take = Math.min(capacity[i], V - currV);
            totalEnergy += take * temp[i];
            currV += take;
            if (currV >= V) break;
        }
        return totalEnergy / V;
    }

    private static double getMinTemp(double V) {
        double totalEnergy = 0d;
        double currV = 0d;
        for (int i = 0; i < n; ++i) {
            double take = Math.min(capacity[i], V - currV);
            totalEnergy += take * temp[i];
            currV += take;
            if (currV >= V) break;
        }
        return totalEnergy / V;
    }

    private static boolean isValid(double V) {
        // Guard against division by zero
        if (V < 1e-6) return true;

        double maxTemp = getMaxTemp(V);
        double minTemp = getMinTemp(V);

        return minTemp <= T && T <= maxTemp;
    }

    private static int[] capacity, temp;
    private static int n, T;

    private static void solve(FastScanner sc) {
        n = sc.nextInt();
        T = sc.nextInt();

        long maxVolume = 0L;

        capacity = new int[n];
        for (int i = 0; i < n; ++i) {
            capacity[i] = sc.nextInt();
            maxVolume += capacity[i];
        }
        temp = new int[n];
        for (int i = 0; i < n; ++i)
            temp[i] = sc.nextInt();

        long[] combined = new long[n];
        for (int i = 0; i < n; ++i)
            combined[i] = ((long) temp[i] << 32) | (capacity[i] & 0xFFFF_FFFFL);

        Arrays.sort(combined);

        for (int i = 0; i < n; ++i) {
            temp[i] = (int) (combined[i] >> 32);
            capacity[i] = (int) (combined[i] & 0xFFFF_FFFFL);
        }

        double low = 0d;
        double high = maxVolume;

        while (high - low > 1e-6) {
            double mid = (low + high) / 2;
            if (isValid(mid)) low = mid;
            else high = mid;
        }
        System.out.println(low <= maxVolume ? low : 0);
    }
}
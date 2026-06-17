public class Optimal {
    private static void reverseArrays(int[] capacity, int[] relative_temp, int start, int end) {
        while (start < end) {
            int X = capacity[start];
            capacity[start] = capacity[end];
            capacity[end] = X;

            X = relative_temp[start];
            relative_temp[start] = relative_temp[end];
            relative_temp[end] = X;

            ++start;
            --end;
        }
    }

    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int T = sc.nextInt();

        double volume = 0d;

        int[] capacity = new int[n];
        for (int i = 0; i < n; ++i)
            capacity[i] = sc.nextInt();
        int[] relative_temp = new int[n];
        long[] combined = new long[n];
        for (int i = 0; i < n; ++i) {
            relative_temp[i] = sc.nextInt() - T;
            combined[i] = ((long) relative_temp[i] << 32) | (capacity[i] & 0xFFFF_FFFFL);
        }

        Arrays.sort(combined);

        int hotIdx = n, neutralIdx = n;
        long hotEnergy = 0l, coldEnergy = 0l;
        long hotVolume = 0l, coldVolume = 0l;

        // fill vessel with max capacity at temp 0
        // segregate combined array
        for (int i = n - 1; i >= 0; --i) {
            relative_temp[i] = (int) (combined[i] >> 32);
            capacity[i] = (int) (combined[i] & 0xFFFF_FFFFl);

            if (relative_temp[i] == 0) {
                neutralIdx = i;
                volume += capacity[i];
            } else if (relative_temp[i] > 0) {
                hotIdx = i;
                hotEnergy += (long) capacity[i] * relative_temp[i];
                hotVolume += capacity[i];
            } else {
                relative_temp[i] = -relative_temp[i];
                coldEnergy += (long) capacity[i] * relative_temp[i];
                coldVolume += capacity[i];
            }
        }

        // GUARD: If we have no hot taps or no cold taps, we cannot balance energy.
        // We can only use the neutral taps (which are already added to 'volume').
        if (hotVolume == 0 || coldVolume == 0) {
            System.out.printf("%.15f\n", volume);
            return;
        }

        long energyLimit = 0l;
        int begin = 0, end = 0;
        if (coldEnergy < hotEnergy) {
            volume += coldVolume;
            energyLimit = coldEnergy;
            begin = hotIdx;
            end = n;
        } else {
            volume += hotVolume;
            energyLimit = hotEnergy;
            begin = 0;
            // If there were no neutral taps, the cold block ends at hotIdx
            end = (neutralIdx == n) ? hotIdx : neutralIdx;

            // reverse cold taps for generalization
            reverseArrays(capacity, relative_temp, 0, end - 1);
        }

        long currEnergy = 0l;
        for (int i = begin; i < end; ++i) {
            long maxEnergy = capacity[i] * relative_temp[i];
            long energy = currEnergy + maxEnergy;
            if (energy <= energyLimit) {
                volume += capacity[i];
                currEnergy = energy;
            } else {
                double neededEnergy = energyLimit - currEnergy;
                volume += neededEnergy / relative_temp[i];
                break;
            }
        }
        System.out.printf("%.15f\n", volume);
    }
}
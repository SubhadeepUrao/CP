public class Optimal {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int T = sc.nextInt();

        double volume = 0d;

        int[] capacity = new int[n];
        for (int i = 0; i < n; ++i) {
            capacity[i] = sc.nextInt();
        }

        long[] combined = new long[n];
        for (int i = 0; i < n; ++i) {
            int relative_temp = sc.nextInt() - T;
            // Pack relative_temp in upper 32 bits and capacity in lower 32 bits
            combined[i] = ((long) relative_temp << 32) | (capacity[i] & 0xFFFF_FFFFL);
        }

        // Sort primarily by relative_temp (ascending order: cold -> neutral -> hot)
        Arrays.sort(combined);

        long hotEnergy = 0L, coldEnergy = 0L;
        long hotVolume = 0L, coldVolume = 0L;

        // Calculate initial total capacities and energies
        for (int i = 0; i < n; ++i) {
            int relTemp = (int) (combined[i] >> 32);
            long cap = combined[i] & 0xFFFF_FFFFL;

            if (relTemp == 0) {
                volume += cap; // Neutral taps are always fully utilized
            } else if (relTemp > 0) {
                hotEnergy += cap * relTemp;
                hotVolume += cap;
            } else {
                coldEnergy += cap * (-relTemp); // Use absolute value for energy calculation
                coldVolume += cap;
            }
        }

        // GUARD: If we have no hot taps or no cold taps, we cannot balance energy.
        // We can only use the neutral taps (which are already added to 'volume').
        if (hotVolume == 0 || coldVolume == 0) {
            System.out.printf("%.15f\n", volume);
            return;
        }

        // If cold energy is less, keep all cold water and trim the hot water
        if (coldEnergy < hotEnergy) {
            volume += coldVolume;
            long energyLimit = coldEnergy;
            long currEnergy = 0L;

            // Scan hot taps from left to right (smallest positive relative_temp to largest)
            for (int i = 0; i < n; ++i) {
                int relTemp = (int) (combined[i] >> 32);
                long cap = combined[i] & 0xFFFF_FFFFL;
                if (relTemp <= 0)
                    continue; // skip cold and neutral

                long maxEnergy = cap * relTemp;
                if (currEnergy + maxEnergy <= energyLimit) {
                    volume += cap;
                    currEnergy += maxEnergy;
                } else {
                    double neededEnergy = energyLimit - currEnergy;
                    volume += neededEnergy / relTemp;
                    break;
                }
            }
        }
        // If hot energy is less (or equal), keep all hot water and trim the cold water
        else {
            volume += hotVolume;
            long energyLimit = hotEnergy;
            long currEnergy = 0L;

            // Scan cold taps from right to left (least negative relative_temp to most
            // negative)
            for (int i = n - 1; i >= 0; --i) {
                int relTemp = (int) (combined[i] >> 32);
                long cap = combined[i] & 0xFFFF_FFFFL;
                if (relTemp >= 0)
                    continue; // skip hot and neutral

                long absTemp = -relTemp; // Work with absolute value
                long maxEnergy = cap * absTemp;
                if (currEnergy + maxEnergy <= energyLimit) {
                    volume += cap;
                    currEnergy += maxEnergy;
                } else {
                    double neededEnergy = energyLimit - currEnergy;
                    volume += neededEnergy / absTemp;
                    break;
                }
            }
        }

        System.out.printf("%.15f\n", volume);
    }
}
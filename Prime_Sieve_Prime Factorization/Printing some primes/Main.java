import java.io.*;

public class Main {
    public static void main(String[] args) {
        int LIMIT = 100000000; // 10^8
        
        // Since we only track odd numbers, index 'i' represents the number (2 * i + 1)
        // Max index needed: (100,000,000 / 2) = 50,000,000
        int size = LIMIT / 2;
        boolean[] isComposite = new boolean[size]; 

        // Sieve only up to sqrt(10^8) = 10,000
        int maxFact = (int) Math.sqrt(LIMIT);
        for (int p = 3; p <= maxFact; p += 2) {
            // p >> 1 is a fast bitwise way of writing p / 2
            if (!isComposite[p >> 1]) {
                int stride = p << 1; // 2 * p (to skip even multiples)
                for (int i = p * p; i < LIMIT; i += stride) {
                    isComposite[i >> 1] = true;
                }
            }
        }

        // Fast I/O for massive output data
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        
        int count = 1; // 1st prime is 2
        out.println(2);

        // Loop through odd numbers starting from 3 (index 1 maps to 2*1 + 1 = 3)
        for (int i = 1; i < size; i++) {
            if (!isComposite[i]) {
                count++;
                // Print only the 1st, 101st, 201st... primes
                if (count % 100 == 1) {
                    out.println((i << 1) + 1); // Reconstruct actual odd number: 2 * i + 1
                }
            }
        }
        
        out.flush(); // Clean up the output stream buffer
    }
}
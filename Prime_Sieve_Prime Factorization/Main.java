import java.io.*;
import java.util.*;

/**
 * IMPORTANT FOR SPOJ: 
 * 1. Your class MUST be named 'Main'. Do not use public if it causes issues, but 'class Main' is standard.
 * 2. Do not include a 'package' statement at the top.
 */
class Main {

    // Fast I/O components
    private static BufferedReader br;
    private static StringTokenizer st;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        // Initialize input and output streams
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new BufferedOutputStream(System.out));

        // ---------------------------------------------------------
        // YOUR CODE LOGIC GOES HERE
        // ---------------------------------------------------------
        
        // Example: Reading an integer 't' for testcases
        String line = nextLine();
        if (line != null && !line.trim().isEmpty()) {
            int t = Integer.parseInt(line.trim());
            
            while (t-- > 0) {
                solve();
            }
        }

        // ---------------------------------------------------------
        
        // CRITICAL: Always flush the output buffer at the very end
        out.flush();
    }

    private static void solve() throws IOException {
        // CODE GOES HERE
    }

    // ==========================================
    // FAST I/O HELPER METHODS
    // ==========================================
    
    // Reads the next string token (separated by spaces)
    private static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = br.readLine();
            if (line == null) return null;
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }

    // Reads the next integer safely
    private static int nextInt() throws IOException {
        String token = next();
        if (token == null) throw new NoSuchElementException("No more tokens available");
        return Integer.parseInt(token);
    }

    // Reads the next long safely
    private static long nextLong() throws IOException {
        String token = next();
        if (token == null) throw new NoSuchElementException("No more tokens available");
        return Long.parseLong(token);
    }

    // Reads a whole line (useful if formatting contains spaces or empty lines)
    private static String nextLine() throws IOException {
        st = null; // Clear tokenizer buffer
        return br.readLine();
    }
}
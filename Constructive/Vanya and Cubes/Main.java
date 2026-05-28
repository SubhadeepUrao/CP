import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static void solve(FastScanner sc) {
        int cubes = sc.nextInt();
        int i = 1;
        int sum = 1;
        while (sum <= cubes) {
            cubes -= sum;
            sum += ++i;
        }
        System.out.println(i - 1);
    }
}
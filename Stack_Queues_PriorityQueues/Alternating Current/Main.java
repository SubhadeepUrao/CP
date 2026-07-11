import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static void solve(FastScanner sc) {
        char[] stack = new char[1_00_000];
        int top = -1;
        char[] str = sc.next().toCharArray();

        for (char ch : str) {
            if (top < 0)
                stack[++top] = ch;
            else {
                if (stack[top] == ch)
                    --top;
                else
                    stack[++top] = ch;
            }
        }
        System.out.println(top < 0 ? "Yes" : "No");
    }
}
// O(nlogn)
// a element can be part of the smaller segment/half atmost log n times

public class Main {
    private static void solve(FastScanner sc) {
        int n = sc.nextInt();
        int[] p = new int[n];
        int[] pos = new int[n + 1]; // maps p[i] -> i

        for (int i = 0; i < n; ++i) {
            p[i] = sc.nextInt();
            pos[p[i]] = i;
        }

        int[] stack = new int[n];
        int top = -1;

        // compute left boundary
        int[] left = new int[n];
        for (int i = 0; i < n; ++i) {
            int curr = p[i];
            while (top >= 0 && p[stack[top]] < curr) {
                --top;
            }
            left[i] = top >= 0 ? stack[top] : -1;
            stack[++top] = i;
        }

        top = -1; // reset stack;

        // compute right boundary
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            int curr = p[i];
            while (top >= 0 && p[stack[top]] < curr) {
                --top;
            }
            right[i] = top >= 0 ? stack[top] : n;
            stack[++top] = i;
        }

        int segment = 0;

        for (int mid = 0; mid < n; ++mid) {
            int leftLen = mid - 1 - left[mid]; // left half/segment length
            int rightLen = right[mid] - 1 - mid; // right half/segment length

            if (leftLen < rightLen) {
                // loop through left side segment since its small
                for (int i = left[mid] + 1; i < mid; ++i) {
                    int target = p[mid] - p[i];
                    int targetPos = pos[target];
                    if (mid < targetPos && targetPos < right[mid])
                        ++segment;
                }
            } else {
                // loop through right side segment since its small
                for (int i = mid + 1; i < right[mid]; ++i) {
                    int target = p[mid] - p[i];
                    int targetPos = pos[target];
                    if (left[mid] < targetPos && targetPos < mid)
                        ++segment;
                }
            }
        }

        System.out.println(segment);
    }
}
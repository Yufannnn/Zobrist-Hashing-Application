import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    public static long countSwaps(int[] arr) {
        return helper(arr, 0, arr.length - 1);
    }

    public static long helper(int[] arr, int start, int end) {
        long count = 0;
        if (start < end) {
            int mid = (start + end) / 2;
            count += helper(arr, start, mid);
            count += helper(arr, mid + 1, end);
            count += mergeAndCount(arr, start, mid, mid + 1, end);
        }
        return count;
    }

    public static long mergeAndCount(int[] arr, int left1, int right1, int left2, int right2) {
        int[] tmp = new int[right2 - left1 + 1];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = arr[i + left1];
        }
        int leftLen = right1 - left1 + 1;
        int rightLen = right2 - left2 + 1;

        long swaps = 0;
        int i = 0;
        int j = leftLen;
        int k = left1;
        while (i < leftLen && j < rightLen + leftLen) {
            if (tmp[i] <= tmp[j])
                arr[k++] = tmp[i++];
            else {
                arr[k++] = tmp[j++];
                swaps += left2 - (left1 + i);
            }
        }
        while (i < leftLen)
            arr[k++] = tmp[i++];
        while (j < rightLen + leftLen)
            arr[k++] = tmp[j++];
        return swaps;
    }

    public static void solveHelper(int[] a, int[] b, int[] I, int leftA, int rightA, int leftB, int rightB) {
        if (leftB >= rightB) { return; }

        int midB = (leftB + rightB) / 2;
//        System.out.println(leftA + " " + rightA + " " + leftB + " " + rightB);
//        System.out.println("midB is : " + midB);
//        System.out.println("b[midB] is : " + b[midB]);

        // scan through Array A from leftA to rightA
        long bestCount = 0;
        long count = 0;
        int midA = leftA;
        for (int i = leftA; i < rightA; i++) {
            if (b[midB] < a[i]) {
                count++;
            } else {
                count--;
            }

            if (count < bestCount) {
                bestCount = count;
                midA = i + 1;
            }
        }

        I[midB] = midA;
//        System.out.println("midA : " + midA);
//        System.out.println(Arrays.toString(I));
//        System.out.println();
//        System.out.println("recurse left");
        solveHelper(a, b, I, leftA, midA, leftB, midB);
//        System.out.println("recurse right");
        solveHelper(a, b, I, midA, rightA, midB+1, rightB);
    }

    // merge array a and b with the given position I
    public static int[] merge(int[] a, int[] b, int[] I) {
        int len = a.length;
        int[] res = new int[2 * len];
        int i = 0;
        int j = 0;

        for (int k = 0; k < 2 * len; k++) {
            if (I[j] <= i) {
                res[k] = b[j];
                j++;
            } else {
                res[k] = a[i];
                i++;
            }
        }
        return res;
    }

    public static long solve(int[] a, int[] b) {
        // find the number of inversion among the numbers in a
        int len = a.length;
        Arrays.sort(b);
        int[] I = new int[a.length];

        solveHelper(a, b, I, 0, len, 0, len);
        return countSwaps(merge(a, b, I));
    }

    public static void main(String[] args) throws IOException {
//        Reader s = new Reader();
//        int n = s.nextInt();
//        int[] a = new int[n];
//        int[] b = new int[n];
//        for(int i=0; i<n; i++) {
//            a[i] = s.nextInt();
//        }
//        for(int i=0; i<n; i++) {
//            b[i] = s.nextInt();
//        }

        int[] a = new int[]{5,4,1,2};
        int[] b = new int[]{8,3,6,7};

        System.out.println(solve(a, b));
    }
}
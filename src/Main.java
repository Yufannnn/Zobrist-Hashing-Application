import java.util.HashMap;
import java.util.Random;

public class Main {

    // Function to find the maximum integer in the array 'arr'
    public static int findMaxValue(int[] arr) {
        int maxValue = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > maxValue) {
                maxValue = num;
            }
        }
        return maxValue;
    }

    // Function to calculate the number of ways to choose two elements from 'count'
    public static long calculateCombinations(long count) {
        if (count < 2) {
            return 0;
        }
        return (count - 1) * count / 2;
    }

    // Function to find the number of balanced intervals in the array 'arr'
    public static long findBalancedIntervals(int[] arr) {
        Random rand = new Random();
        int maxValue = findMaxValue(arr);
        long[] transpositionTable = new long[maxValue + 1];

        // Initialize the transpositionTable with random values
        for (int i = 0; i <= maxValue; i++) {
            transpositionTable[i] = rand.nextLong();
        }

        long zobristHash = 0L;
        HashMap<Long, Long> countsMap = new HashMap<>();
        countsMap.put(zobristHash, 1L); // Initial empty subarray has a count of 1

        // Calculate Zobrist Hash for each subarray and maintain counts in the countsMap
        for (int num : arr) {
            zobristHash ^= transpositionTable[num];
            if (countsMap.containsKey(zobristHash)) {
                countsMap.put(zobristHash, countsMap.get(zobristHash) + 1);
            } else {
                countsMap.put(zobristHash, 1L);
            }
        }

        long result = 0L;

        // Calculate the number of balanced intervals using the countsMap
        for (long count : countsMap.values()) {
            result += calculateCombinations(count);
        }

        return result;
    }
}

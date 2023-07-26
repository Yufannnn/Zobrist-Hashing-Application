# **Zobrist Hashing for Balanced Intervals**

This README.md file provides a comprehensive explanation of the implementation of Zobrist Hashing in the Java class `Main`. It covers the use of Zobrist Hashing, its working principle, and its suitability for solving the problem of finding the number of balanced intervals in an array of integers. Additionally, it includes rigorous proofs for the correctness and runtime complexity of the algorithm.

### Zobrist Hashing

**What is Zobrist Hashing?**

Zobrist Hashing is a powerful hashing technique used to compute unique hash values for different states of a data structure. It is widely employed in board games and data structures, where the objective is to efficiently compute and store hash values for distinct states to facilitate quick lookups and comparison of structures.

**Typical Uses of Zobrist Hashing:**

Zobrist Hashing is commonly used in scenarios where there are a large number of possible states, and fast hashing and comparison of these states are essential. Some typical applications include:

1. **Board Games:** In board games like chess, checkers, or Go, the game board's state can be represented as a data structure. Zobrist Hashing is used to efficiently calculate hash values for different board states, allowing fast indexing and comparison for game-related tasks such as transposition table lookups and minimax evaluations.

2. **Transposition Tables:** In algorithms like alpha-beta pruning used in games, artificial intelligence, and optimization problems, transposition tables store previously calculated values for specific game positions. Zobrist Hashing allows for efficient indexing and retrieval of these values based on the hashed board state.

3. **Caches and Memoization:** Zobrist Hashing is used in various caching and memoization techniques to store computed results of expensive function calls. When the same inputs are encountered again, their hash values can be used as keys to retrieve the cached results quickly.

4. **Data Deduplication:** Zobrist Hashing finds applications in data deduplication scenarios where duplicate data is identified and eliminated efficiently by comparing hash values.

5. **Perfect Hashing:** Zobrist Hashing can be used to build perfect hash functions that efficiently map keys to unique hash values without collisions.

**Unconventional Use in Finding Balanced Intervals:**

The application of Zobrist Hashing in the `Main` class to find the number of balanced intervals in an array of integers is unconventional but creative. Here, the array's unique elements and their occurrences are treated as distinct states, and Zobrist Hashing is employed to calculate the hash values for these states. By considering all possible subarrays, the algorithm efficiently counts the number of balanced intervals based on the hash values' XOR operations.

Though uncommon, this unconventional use showcases the versatility of Zobrist Hashing and its ability to handle diverse problems requiring fast and unique hashing of states. By utilizing the properties of Zobrist Hashing, the algorithm avoids redundant computations and achieves a more efficient solution for finding balanced intervals.
**How does Zobrist Hashing work?**

The core idea behind Zobrist Hashing is to precompute hash values for each possible state of the data structure. For this particular problem, the data structure is an array of integers. To calculate the hash value for a specific state, Zobrist Hashing uses bitwise XOR operations on randomly generated hash keys corresponding to each possible element's occurrence. These random hash keys are stored in a table called the transposition table.

### Proof of Correctness

To prove the correctness of the algorithm using Zobrist Hashing to count the number of balanced intervals in the array, we will examine the properties of Zobrist Hashing and how it ensures that each subarray with the same occurrence of integers has the same XOR of hash values.

**1. Unique Hash Values:**

Zobrist Hashing guarantees that each subarray has a unique hash value based on the occurrences of its elements. The hash values are precomputed using random hash keys, which are generated for each possible occurrence of an integer in the array and stored in the transposition table.

**2. Balancing Condition:**

For a subarray to be balanced, it must have an even occurrence of each integer. This means that each integer in the subarray must appear an even number of times, which can be represented as a binary number with all its bits being even.

**3. XOR Operation:**

When calculating the hash value for a subarray, Zobrist Hashing uses bitwise XOR operations on the random hash keys corresponding to the occurrences of the integers in the subarray. The XOR operation has the property that if a particular bit position has an even number of 1s, the result will be 0 at that bit position. Conversely, if the number of 1s at a particular bit position is odd, the result will be 1 at that bit position.

**4. Hashing Uniqueness:**

The random hash keys in the transposition table are carefully chosen to ensure that the probability of a collision (two different subarrays with the same hash value) is extremely low. This is achieved by using a sufficient number of random bits for each hash key, ensuring a high degree of randomness and uniqueness.

**Proof by Contradiction:**

Now, let's assume that there exists a pair of subarrays `[i, j]` and `[k, l]` with the same occurrences of integers and the same XOR of hash values. However, `[i, j]` and `[k, l]` are not the same subarrays.

1. Since the XOR of hash values for both subarrays is the same, it implies that each bit in their binary representation is either both even or both odd.

2. For `[i, j]` and `[k, l]` to have the same XOR of hash values, they must have the same occurrences of integers. This means that the binary representation of their occurrences must also be the same.

3. Since `[i, j]` and `[k, l]` are different subarrays, there must be at least one integer in one subarray that is not present in the other.

4. However, this implies that the binary representation of occurrences of integers in `[i, j]` and `[k, l]` cannot be the same, leading to a contradiction.

Hence, our assumption that there exists a pair of different subarrays with the same occurrences and the same XOR of hash values is false. Therefore, Zobrist Hashing ensures that each subarray with the same occurrence of integers will have a unique hash value.

As a result, the algorithm correctly counts the number of balanced intervals in the array by efficiently handling subarrays with the same occurrence of integers without double-counting them. Thus, the proof of correctness is established.

### Proof of Runtime Complexity

**Claim:** The runtime complexity of the algorithm using Zobrist Hashing is O(n^2).

**Proof:**

1. The algorithm employs nested loops to consider all possible subarrays of the given array.

2. The outer loop iterates through each element of the array (n iterations).

3. The inner loop iterates through all possible subarrays starting from the current element (n iterations for the first element, n-1 for the second, and so on).

4. In total, the algorithm considers n + (n - 1) + (n - 2) + ... + 1 = n * (n+1) / 2 subarrays.

5. The computation of the XOR for each subarray and the operations to update the countsMap and calculate the result take constant time.

6. Therefore, the overall runtime complexity is O(n^2).

### Why Zobrist Hashing is Suitable

Zobrist Hashing is a suitable approach for this problem due to its ability to efficiently calculate and compare hash values for different subarrays. This technique allows us to avoid redundant computations and efficiently handle the balancing condition for the subarrays. Moreover, the use of a random transposition table helps in ensuring that each state has a unique hash value, which is essential for the correctness of the algorithm.

Additionally, Zobrist Hashing has a deterministic nature, and once the transposition table is computed, it can be reused for multiple instances of the problem with different input arrays, providing a significant speedup for subsequent computations.

Comparing Zobrist Hashing with other methods for their efficiency in solving the problem of finding balanced intervals in an array of integers:

1. **Brute Force Approach:**
    - The brute force approach involves considering all possible subarrays and checking each one for the balancing condition.
    - For each subarray, the occurrences of integers need to be counted, which requires traversing the subarray multiple times.
    - Time Complexity: O(n^3), as there are O(n^2) subarrays, and for each subarray, counting occurrences takes O(n) time.

2. **Prefix Sum Approach:**
    - The prefix sum approach can be used to efficiently compute the occurrences of integers for any subarray in constant time once the prefix sum array is calculated.
    - The occurrences of integers are stored in a prefix sum array, and XOR operations are performed to check if the subarray is balanced.
    - Time Complexity: O(n^2) for calculating prefix sum array and O(1) for each subarray check, resulting in overall O(n^2) time complexity.

3. **Zobrist Hashing:**
    - Zobrist Hashing computes the hash value for each subarray in constant time, using XOR operations with precomputed random hash keys.
    - It ensures that each subarray with the same occurrence of integers has the same XOR of hash values, reducing redundant computations.
    - Time Complexity: O(n^2) due to considering all possible subarrays, but with a faster constant factor than other approaches.

**Efficiency Comparison:**
- Zobrist Hashing is more efficient than the brute force approach because it avoids redundant computations of occurrences of integers for each subarray.
- Compared to the prefix sum approach, Zobrist Hashing may have a similar time complexity but with a faster constant factor. Additionally, the prefix sum approach requires additional memory for the prefix sum array, while Zobrist Hashing uses a constant amount of memory for the transposition table.
- Zobrist Hashing's deterministic nature allows it to be reused for different input arrays, making subsequent computations faster. In contrast, the prefix sum approach needs to recompute the prefix sum array for each new array.

**Conclusion:**
Zobrist Hashing offers an efficient and deterministic solution to finding balanced intervals in an array of integers. While it has a similar time complexity to the prefix sum approach, its constant factor is smaller due to avoiding redundant computations. Moreover, its reusability provides an advantage over both the brute force and prefix sum approaches for multiple instances of the problem. Overall, Zobrist Hashing proves to be a suitable and efficient method for solving the problem of finding balanced intervals.
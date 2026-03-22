package com.practice.hackerrank.datastructures;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ArrayManipulationResult {

  public static long arrayManipulation(int n, List<List<Integer>> queries) {
    // Create difference array with one extra element for boundary
    long[] diff = new long[n + 1];
    // Process each query using difference array technique
    for (List<Integer> query : queries) {
      int a = query.get(0);
      int b = query.get(1);
      int k = query.get(2);
      // Add k at the start of range (convert to 0-based index)
      diff[a - 1] += k;
      // Subtract k at the position after the end of range (if within bounds)
      if (b < n) {
        diff[b] -= k;
      }
    }
    // Compute prefix sum and find maximum value
    long max = 0;
    long current = 0;

    for (int i = 0; i < n; i++) {
      current += diff[i];  // Accumulate to get actual value
      if (current > max) {
        max = current;
      }
    }

    return max;
  }
}

/**
 * Problem: Array Manipulation
 * <p>
 * Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value
 * to each array element between two given indices, inclusive. Once all operations have been
 * performed, return the maximum value in the array.
 * <p>
 * Example: n = 10 queries = [[1, 5, 3], [4, 8, 7], [6, 9, 1]]
 * <p>
 * Queries are interpreted as follows: a b k 1 5 3 4 8 7 6 9 1
 * <p>
 * Add the values of k between the indices a and b inclusive: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0] [3, 3,
 * 3, 3, 3, 0, 0, 0, 0, 0]  (after [1, 5, 3]) [3, 3, 3, 10, 10, 7, 7, 7, 0, 0] (after [4, 8, 7]) [3,
 * 3, 3, 10, 10, 8, 8, 8, 1, 0] (after [6, 9, 1])
 * <p>
 * The largest value is 10 after all operations are performed.
 * <p>
 * Function Description: Complete the function arrayManipulation with the following parameters: int
 * n: the number of elements in the array int queries[q][3]: a two dimensional array of queries
 * where each queries[i] contains three integers, a, b, and k.
 * <p>
 * Returns: long: the maximum value in the resultant array
 * <p>
 * Input Format: The first line contains two space-separated integers n and q, the size of the array
 * and the number of queries. Each of the next q lines contains three space-separated integers a, b
 * and k, the left index, right index and number to add.
 * <p>
 * Constraints: 3 <= n <= 10^7 1 <= m <= 2 * 10^5 1 <= a <= b <= n 0 <= k <= 10^9
 */
public class ArrayManipulation {

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int m = Integer.parseInt(firstMultipleInput[1]);

    List<List<Integer>> queries = new ArrayList<>();

    IntStream.range(0, m).forEach(i -> {
      try {
        queries.add(
            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList())
        );
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    long result = ArrayManipulationResult.arrayManipulation(n, queries);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}

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

/**
 * Problem: Find the maximum hourglass sum in a 2D array
 * 
 * An hourglass is defined as a pattern in a 2D array with the following shape:
 * a b c
 *   d
 * e f g
 * 
 * Constraints: -9 <= arr[i][j] <= 9 (values range from -9 to 9)
 * Array size: 6x6 (0 <= i, j <= 5)
 * 
 * Sample Input:
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 2 4 4 0
 * 0 0 0 2 0 0
 * 0 0 1 2 4 0
 * 
 * Sample Output: 19
 * 
 * Explanation: The hourglass with maximum sum (19) is:
 * 2 4 4
 *   2
 * 1 2 4
 * 
 * Algorithm approach:
 * 1. Iterate through all possible top-left corners of hourglasses
 * 2. For a 6x6 array, valid top-left positions range from [0][0] to [3][3]
 * 3. Calculate sum of each hourglass: top row (3 elements) + middle (1 element) + bottom row (3 elements)
 * 4. Track the maximum sum found
 * 5. Handle negative values by initializing max sum appropriately
 */
public class TwoDimensionArrayHourGlass {

  static class Result {

    /*
     * Complete the 'hourglassSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */
    public static int hourglassSum(List<List<Integer>> arr) {
      int maxSum = Integer.MIN_VALUE;

      for (int i = 0; i <= 3; i++) {
        for (int j = 0; j <= 3; j++) {
          int currentSum =
              arr.get(i).get(j) + arr.get(i).get(j + 1) + arr.get(i).get(j + 2)
                  + arr.get(i + 1).get(j + 1)
                  + arr.get(i + 2).get(j) + arr.get(i + 2).get(j + 1) + arr.get(i + 2)
                  .get(j + 2);

          if (currentSum > maxSum) {
            maxSum = currentSum;
          }
        }
      }
      return maxSum;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    List<List<Integer>> arr = new ArrayList<>();

    IntStream.range(0, 6).forEach(i -> {
      try {
        arr.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt).collect(toList()));
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });
    int result = Result.hourglassSum(arr);
    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();
    bufferedReader.close();
    bufferedWriter.close();
  }
}

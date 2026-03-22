package com.practice.hackerrank.datastructures;

import static java.util.stream.Collectors.joining;
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
 * Problem: Dynamic Array
 * 
 * Create a list, seqList, of N empty sequences. Let lastAnswer = 0.
 * 
 * There are two types of queries:
 * 
 * 1. Query: 1 x y
 *    - Find the sequence, seq, at index ((x XOR lastAnswer) % N).
 *    - Append integer y to sequence seq.
 * 
 * 2. Query: 2 x y
 *    - Find the sequence, seq, at index ((x XOR lastAnswer) % N).
 *    - Find the value of element y % size(seq) in seq (where size(seq) is the size of sequence seq).
 *    - Assign this value to lastAnswer.
 *    - Print the new value of lastAnswer.
 * 
 * Input Format:
 * - The first line contains two space-separated integers, N (the number of sequences) and Q (the number of queries).
 * - The next Q lines contain queries in the format described above.
 * 
 * Constraints:
 * - 1 ≤ N, Q ≤ 10^5
 * - 0 ≤ x, y ≤ 10^9
 * - It is guaranteed that query type 2 will never query an empty sequence.
 * 
 * Output Format:
 * - For each type 2 query, print the updated value of lastAnswer on a new line.
 */
public class TwoDimensionArrayDynamic {

  static class Result {

    /*
     * Complete the 'dynamicArray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
      // Write your code here
      List<Integer> results = new ArrayList<>();
      List<List<Integer>> seqList = new ArrayList<>();
      int lastAnswer = 0;

      for (int i = 0; i < n; i++) {
        seqList.add(new ArrayList<>());
      }

      for (List<Integer> query : queries) {
        int type = query.get(0);
        int x = query.get(1);
        int y = query.get(2);

        int seq = (x ^ lastAnswer) % n;

        if (type == 1) {
          seqList.get(seq).add(y);
        } else if (type == 2) {
          int size = seqList.get(seq).size();
          lastAnswer = seqList.get(seq).get(y % size);
          results.add(lastAnswer);
        }
      }
      return results;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int q = Integer.parseInt(firstMultipleInput[1]);

    List<List<Integer>> queries = new ArrayList<>();

    IntStream.range(0, q).forEach(i -> {
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

    List<Integer> result = Result.dynamicArray(n, queries);

    bufferedWriter.write(
        result.stream()
            .map(Object::toString)
            .collect(joining("\n"))
            + "\n"
    );

    bufferedReader.close();
    bufferedWriter.close();
  }
}

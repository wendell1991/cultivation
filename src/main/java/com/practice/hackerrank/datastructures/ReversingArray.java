package com.practice.hackerrank.datastructures;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Problem: Reverse an array of integers
 * <p>
 * Given an array of integers, write a function to reverse the order of elements. For example, if
 * input array is [1, 2, 3, 4, 5], the output should be [5, 4, 3, 2, 1].
 * <p>
 * This class demonstrates two different approaches to solve this problem: 1. Using CollectionUtils
 * approach (Collections.reverse) 2. Using two-pointer approach
 */
public class ReversingArray {

  /**
   * Solution 1: Using CollectionUtils (Collections.reverse) Time Complexity: O(n) Space Complexity:
   * O(n) - due to conversion to List
   */
  public static int[] reverseUsingCollections(int[] array) {
    // Convert primitive array to Integer array for Collections usage
    Integer[] integerArray = Arrays.stream(array).boxed().toArray(Integer[]::new);

    // Convert to List and reverse using Collections.reverse
    List<Integer> list = Arrays.asList(integerArray);
    Collections.reverse(list);

    // Convert back to primitive array
    return list.stream().mapToInt(Integer::intValue).toArray();
  }

  /**
   * Solution 2: Using two-pointer approach Time Complexity: O(n) Space Complexity: O(1) - in-place
   * reversal
   */
  public static int[] reverseUsingTwoPointers(int[] array) {
    int left = 0;
    int right = array.length - 1;

    while (left < right) {
      // Swap elements at left and right pointers
      int temp = array[left];
      array[left] = array[right];
      array[right] = temp;

      // Move pointers towards center
      left++;
      right--;
    }
    return array;
  }

  /**
   * Helper method to print array for testing
   */
  public static void printArray(int[] array) {
    System.out.println(Arrays.toString(array));
  }

  /**
   * Main method to test both approaches
   */
  public static void main(String[] args) {
    int[] originalArray = {1, 2, 3, 4, 5};

    System.out.println("Original array:");
    printArray(originalArray);

    // Test CollectionUtils approach
    int[] reversed1 = reverseUsingCollections(originalArray.clone());
    System.out.println("Reversed using Collections:");
    printArray(reversed1);

    // Test two-pointer approach
    int[] reversed2 = reverseUsingTwoPointers(originalArray.clone());
    System.out.println("Reversed using two-pointers:");
    printArray(reversed2);
  }
}

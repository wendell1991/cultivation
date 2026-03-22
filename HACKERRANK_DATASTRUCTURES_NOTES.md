# Hackerrank Data Structures Notes

## Table of Contents

1. [Array Left Rotation](#array-left-rotation)
   - Problem Description
   - Example & Input/Output
   - Algorithm Strategy
   - Implementation
   - Time & Space Complexity
   - Key Insights

2. [Dynamic Array](#dynamic-array)
   - Problem Description
   - Query Types
   - Key Concepts (XOR Operation)
   - Algorithm Steps
   - Implementation
   - Time & Space Complexity
   - Important Notes

3. [2D Array Hour Glass Sum](#2d-array-hour-glass-sum)
   - Problem Description
   - Hourglass Pattern
   - Example & Input/Output
   - Key Insights (Boundary Constraints)
   - Algorithm Steps
   - Implementation
   - Time & Space Complexity
   - Important Considerations

4. [Array Reversing](#array-reversing)
   - Problem Description
   - Algorithm Approaches
   - Two-Pointer Technique Explained
   - When to Use Each Approach
   - Common Pitfalls
   - Best Practices

5. [Array Manipulation](#array-manipulation)
   - Problem Description
   - Key Concept: Difference Array Technique
   - Algorithm Steps
   - Implementation
   - Time & Space Complexity
   - Why This Works: Visual Example
   - Key Insights
   - Common Pitfalls
   - Best Practices

---

## Array Left Rotation

### Problem
Given an array of n integers and a number d, perform d left rotations on the array. A left rotation means each element in the array shifts to the left by one position, and the first element moves to the end of the array.

### Example
```
Array: [1, 2, 3, 4, 5]
After 1 left rotation: [2, 3, 4, 5, 1]
After 2 left rotations: [3, 4, 5, 1, 2]
After 4 left rotations: [5, 1, 2, 3, 4]
```

### Input Format
- First line: n d (where n is array size, d is number of rotations)
- Second line: n space-separated integers (the array elements)

### Constraints
- 1 ≤ n ≤ 10^5
- 1 ≤ d ≤ n
- 1 ≤ arr[i] ≤ 10^6

### Output Format
- Print the array after performing d left rotations

### Algorithm Strategy
**Split array into two parts and reorder**
- Part 1: Elements from index d to end (these move to front)
- Part 2: Elements from index 0 to d (these move to back)

**Visual Example:**
```
Original: [1, 2, 3, 4, 5], d = 2
Part 1: [3, 4, 5] (elements from index 2 onwards)
Part 2: [1, 2]    (elements from index 0 to 1)
Result:  [3, 4, 5, 1, 2] (Part 1 + Part 2)
```

### Implementation (Java)
```java
public static List<Integer> rotateLeft(int d, List<Integer> arr) {
    List<Integer> rotated = new ArrayList<>();
    
    // Add elements from index d to end (move to front)
    rotated.addAll(arr.subList(d, arr.size()));
    
    // Add elements from index 0 to d (move to back)  
    rotated.addAll(arr.subList(0, d));
    
    return rotated;
}
```

### Alternative: Array Implementation
If using `int[] arr` instead of `List<Integer>`:

```java
public static int[] rotateLeft(int d, int[] arr) {
    int n = arr.length;
    int[] rotated = new int[n];
    
    // Copy elements from index d to end
    for (int i = 0; i < n - d; i++) {
        rotated[i] = arr[i + d];
    }
    
    // Copy elements from index 0 to d
    for (int i = 0; i < d; i++) {
        rotated[n - d + i] = arr[i];
    }
    
    return rotated;
}
```

### Time & Space Complexity
- **Time Complexity**: O(n) - optimal
- **Space Complexity**: O(n) - creates new array/list

### Key Insights
- Two-pointer technique doesn't work for rotation (it's for reversing)
- Sublist approach is simple, readable, and efficient
- Strategy: `elements[d:] + elements[0:d]`

---

## Dynamic Array

### Problem
Create a list, seqList, of N empty sequences. Let lastAnswer = 0. Process two types of queries on these sequences.

### Query Types

#### Type 1: Append Operation
**Query: 1 x y**
- Find the sequence, seq, at index `((x XOR lastAnswer) % N)`
- Append integer y to sequence seq

#### Type 2: Retrieve Operation  
**Query: 2 x y**
- Find the sequence, seq, at index `((x XOR lastAnswer) % N)`
- Find the value of element `y % size(seq)` in seq
- Assign this value to lastAnswer
- Print the new value of lastAnswer

### Input Format
- First line contains two space-separated integers, N (number of sequences) and Q (number of queries)
- Next Q lines contain queries in the format described above

### Constraints
- 1 ≤ N, Q ≤ 10^5
- 0 ≤ x, y ≤ 10^9
- It is guaranteed that query type 2 will never query an empty sequence

### Output Format
- For each type 2 query, print the updated value of lastAnswer on a new line

### Key Concepts

#### XOR Operation
- Symbol: `^` in Java
- Creates "pseudo-random" sequence index
- Properties: commutative, self-inverse, identity with 0
- Used to mix `x` and `lastAnswer` for varied sequence selection

#### Query Format Understanding
Each query has 3 elements: `[type, x, y]`
- `queries[i][0]` = type (1 or 2)
- `queries[i][1]` = x value
- `queries[i][2]` = y value

### Algorithm Steps
1. Initialize `seqList` with N empty ArrayLists
2. Initialize `lastAnswer = 0` and results list
3. For each query:
   - Extract type, x, y
   - Calculate sequence index: `seq = (x ^ lastAnswer) % n`
   - Handle based on query type
4. Return collected results

### Implementation (Java)
```java
public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
    List<Integer> results = new ArrayList<>();
    List<List<Integer>> seqList = new ArrayList<>();
    int lastAnswer = 0;

    // Initialize with n empty sequences
    for (int i = 0; i < n; i++) {
        seqList.add(new ArrayList<>());
    }

    // Process each query
    for (List<Integer> query : queries) {
        int type = query.get(0);
        int x = query.get(1);
        int y = query.get(2);

        // Calculate sequence index
        int seq = (x ^ lastAnswer) % n;

        if (type == 1) {
            // Type 1: Append y to sequence
            seqList.get(seq).add(y);
        } else if (type == 2) {
            // Type 2: Get value and update lastAnswer
            int size = seqList.get(seq).size();
            lastAnswer = seqList.get(seq).get(y % size);
            results.add(lastAnswer);
        }
    }
    return results;
}
```

### Time & Space Complexity
- **Time Complexity**: O(Q) - where Q is number of queries
- **Space Complexity**: O(N + Q) - sequences storage + results

### Important Notes
- `% n` ensures sequence index stays within bounds [0, n-1]
- XOR operation ensures same x can point to different sequences as lastAnswer changes
- Type 2 queries guaranteed to access non-empty sequences

---

## 2D Array Hour Glass Sum

### Problem
Find the maximum hourglass sum in a 6x6 2D array. An hourglass is a specific pattern that spans 3 rows and 3 columns.

### Hourglass Pattern
```
a b c
  d
e f g
```

### Example
```
Input:
1 1 1 0 0 0
0 1 0 0 0 0
1 1 1 0 0 0
0 0 2 4 4 0
0 0 0 2 0 0
0 0 1 2 4 0

Output: 19

Explanation: The hourglass with maximum sum (19) is:
2 4 4
  2
1 2 4
```

### Constraints
- Array size: 6x6 (0 ≤ i, j ≤ 5)
- Values range: -9 ≤ arr[i][j] ≤ 9
- Fixed array size (always 6x6)

### Input Format
- 6 lines, each containing 6 space-separated integers

### Output Format
- Single integer representing the maximum hourglass sum

### Key Insights

#### Boundary Constraints
- In a 6x6 array, hourglass can only start at positions 0-3 for both rows and columns
- Hourglass occupies 3x3 space, needs 2 more rows/columns from starting position
- Creates a 4x4 grid of possible hourglass positions = 16 total hourglasses

#### Hourglass Structure
```
arr[i][j]   arr[i][j+1]   arr[i][j+2]    <- top row (3 elements)
              arr[i+1][j+1]              <- middle (1 element)
arr[i+2][j] arr[i+2][j+1] arr[i+2][j+2] <- bottom row (3 elements)
```

### Algorithm Steps
1. Initialize maxSum with smallest possible value (Integer.MIN_VALUE)
2. Iterate through all possible top-left corners (rows 0-3, columns 0-3)
3. For each position, calculate hourglass sum:
   - Top row: 3 elements
   - Middle: 1 element  
   - Bottom row: 3 elements
4. Track maximum sum found
5. Return maxSum

### Implementation (Java)
```java
public static int hourglassSum(List<List<Integer>> arr) {
    int maxSum = Integer.MIN_VALUE;

    // Iterate through possible starting positions (0-3 for both rows and columns)
    for (int i = 0; i <= 3; i++) {
        for (int j = 0; j <= 3; j++) {
            // Calculate hourglass sum
            int currentSum = 
                arr.get(i).get(j) + arr.get(i).get(j + 1) + arr.get(i).get(j + 2) + // top row
                arr.get(i + 1).get(j + 1) + // middle
                arr.get(i + 2).get(j) + arr.get(i + 2).get(j + 1) + arr.get(i + 2).get(j + 2); // bottom row

            // Update maximum if current is larger
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
    }
    return maxSum;
}
```

### Time & Space Complexity
- **Time Complexity**: O(1) - always 16 hourglasses (constant for 6x6)
- **Space Complexity**: O(1) - only uses a few variables

### Important Considerations
- Initialize maxSum with Integer.MIN_VALUE due to negative values
- Handle List<List<Integer>> access with .get() methods
- Ensure boundary conditions (i ≤ 3, j ≤ 3)

---

## Array Reversing

### Problem
Given an array of integers, reverse the order of elements.

### Example
```
Input:  [1, 2, 3, 4, 5]
Output: [5, 4, 3, 2, 1]
```

### Input Format
- Array of integers (size varies by problem)

### Output Format
- Reversed array

### Algorithm Approaches

#### Approach 1: Using Collections.reverse()
```java
public static int[] reverseUsingCollections(int[] array) {
    // Convert primitive array to Integer array
    Integer[] integerArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
    
    // Convert to List and reverse
    List<Integer> list = Arrays.asList(integerArray);
    Collections.reverse(list);
    
    // Convert back to primitive array
    return list.stream().mapToInt(Integer::intValue).toArray();
}
```

#### Approach 2: Two-Pointer Technique (Recommended)
```java
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
```

### Time & Space Complexity

#### Collections Approach
- **Time Complexity**: O(n)
- **Space Complexity**: O(n) - due to array conversions

#### Two-Pointer Approach
- **Time Complexity**: O(n)
- **Space Complexity**: O(1) - in-place reversal

### Two-Pointer Technique Explained

#### Concept
- Use two pointers: `left` (start) and `right` (end)
- Swap elements at these positions
- Move pointers toward center until they meet

#### Visual Example
```
[1, 2, 3, 4, 5]
 ^           ^
 left       right

Step 1: Swap 1 ↔ 5
[5, 2, 3, 4, 1]
   ^        ^
   left    right

Step 2: Swap 2 ↔ 4  
[5, 4, 3, 2, 1]
      ^   ^
     left right

Step 3: Pointers meet/cross - done
```

#### Key Points
- **Condition**: `while (left < right)` ensures we don't over-swap
- **Termination**: When left ≥ right, array is fully reversed
- **In-place**: No extra space needed beyond a few variables

### When to Use Each Approach

#### Collections.reverse()
- When working with Lists already
- When simplicity is preferred over space efficiency
- When array size is small

#### Two-Pointer
- When space efficiency matters
- When working with primitive arrays
- When performance is critical (no conversions needed)

### Common Pitfalls
- **Off-by-one errors**: Ensure correct loop condition (`left < right`)
- **Array bounds**: Don't access beyond array boundaries
- **Odd vs even length**: Works for both (middle element stays in place for odd length)

### Best Practice
**Two-pointer approach is preferred** for:
- Better space complexity (O(1))
- No type conversions needed
- More efficient for large arrays

---

## Array Manipulation

### Problem
Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each array element between two given indices, inclusive. Once all operations have been performed, return the maximum value in the array.

### Example
```
n = 10
queries = [[1, 5, 3], [4, 8, 7], [6, 9, 1]]

Queries are interpreted as follows:
a b k
1 5 3
4 8 7
6 9 1

Add the values of k between the indices a and b inclusive:
[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
[3, 3, 3, 3, 3, 0, 0, 0, 0, 0]  (after [1, 5, 3])
[3, 3, 3, 10, 10, 7, 7, 7, 0, 0] (after [4, 8, 7])
[3, 3, 3, 10, 10, 8, 8, 8, 1, 0] (after [6, 9, 1])

The largest value is 10 after all operations are performed.
```

### Input Format
- First line contains two space-separated integers n and q, the size of the array and the number of queries
- Each of the next q lines contains three space-separated integers a, b and k, the left index, right index and number to add

### Constraints
- 3 ≤ n ≤ 10^7
- 1 ≤ m ≤ 2 * 10^5
- 1 ≤ a ≤ b ≤ n
- 0 ≤ k ≤ 10^9

### Output Format
- Single integer representing the maximum value in the resultant array

### Key Concept: Difference Array Technique

#### What is a Difference Array?
A difference array stores **changes between consecutive elements** instead of actual values.

#### Normal Array vs Difference Array:
```
Normal Array:    [3, 3, 3, 10, 10, 7, 7, 7, 0, 0]
Difference:      [3, 0, 0, 7, 0, -3, 0, 0, -7, 0, 0]
```

#### Why Use Difference Array?
- **Range Update in O(1)**: Instead of updating every element in range, just mark start and end points
- **Efficient Recovery**: Single pass to compute all values
- **Space Efficient**: Only need O(n) extra space

### Algorithm Steps

#### Step 1: Create Difference Array
```java
long[] diff = new long[n + 1];  // Extra element for boundary handling
```

#### Step 2: Process Each Query
For each query `[a, b, k]`:
```java
diff[a - 1] += k;        // Start adding k at position a-1 (0-based)
diff[b] -= k;            // Stop adding k after position b-1
```

#### Step 3: Compute Prefix Sum & Find Maximum
```java
long max = 0;
long current = 0;
for (int i = 0; i < n; i++) {
    current += diff[i];   // Accumulate to get actual value
    max = Math.max(max, current);
}
```

### Implementation (Java)
```java
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
```

### Time & Space Complexity
- **Time Complexity**: O(n + m) - optimal for large inputs
- **Space Complexity**: O(n) - difference array storage

### Why This Works: Visual Example

#### Query Processing:
```
Query [1, 5, 3]: diff[0] += 3, diff[5] -= 3
Query [4, 8, 7]: diff[3] += 7, diff[8] -= 7
Query [6, 9, 1]: diff[5] += 1, diff[9] -= 1

Final diff: [3, 0, 0, 7, 0, -2, 0, 0, -7, -1, 0]
```

#### Prefix Sum Recovery:
```java
i=0: current = 0 + 3 = 3, max = 3
i=1: current = 3 + 0 = 3, max = 3
i=2: current = 3 + 0 = 3, max = 3
i=3: current = 3 + 7 = 10, max = 10  ← Both ranges overlap!
i=4: current = 10 + 0 = 10, max = 10
i=5: current = 10 + (-2) = 8, max = 10  ← Stop first range
i=6: current = 8 + 0 = 8, max = 10
i=7: current = 8 + 0 = 8, max = 10
i=8: current = 8 + (-7) = 1, max = 10  ← Stop second range
i=9: current = 1 + (-1) = 0, max = 10  ← Stop third range
```

#### Final Result:
```
Actual array: [3, 3, 3, 10, 10, 8, 8, 8, 1, 0]
Maximum: 10 ✅
```

### Key Insights

#### The "Start/Stop" Technique
- `diff[a-1] += k` = "Start adding k from here"
- `diff[b] -= k` = "Stop adding k after here"

#### Prefix Sum Magic
The prefix sum `current += diff[i]` maintains the **running total** of all active additions at each position, effectively reconstructing the actual array values without storing them.

#### Boundary Handling
```java
if (b < n) {
    diff[b] -= k;  // Only if b is within array bounds
}
```
Prevents array overflow when `b = n`.

### Common Pitfalls

#### 1. Off-by-One Errors
- Array is 1-indexed in problem, 0-indexed in code
- Remember to convert: `a-1` and `b` for 0-based indexing

#### 2. Boundary Conditions
- Need extra element in diff array: `new long[n + 1]`
- Check bounds before subtracting: `if (b < n)`

#### 3. Data Type Overflow
- Use `long` for diff array and current value
- Maximum possible sum: `10^9 × 2×10^5 = 2×10^14` (fits in long)

#### 4. Initialization
- Initialize `max = 0` (array starts with zeros)
- Initialize `current = 0` for running total

### Alternative Approaches (Not Recommended)

#### Brute Force Approach
```java
long[] array = new long[n];
for (List<Integer> query : queries) {
    int a = query.get(0) - 1;
    int b = query.get(1) - 1;
    int k = query.get(2);
    
    for (int i = a; i <= b; i++) {
        array[i] += k;  // O(n × m) - too slow!
    }
}
```
**Time Complexity**: O(n × m) = 2×10^12 operations ❌

#### Segment Tree Approach
- Complex implementation
- Overkill for this problem
- Still O((n + m) log n) ❌

### Best Practice
**Difference array technique is optimal** for this problem because:
- Handles range updates in O(1)
- Single pass to compute results
- Minimal space usage
- Simple implementation
- Handles large constraints efficiently

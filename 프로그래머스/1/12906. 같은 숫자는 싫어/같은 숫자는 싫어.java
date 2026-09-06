import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        stack.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == stack.peek())
                continue;
            
            stack.push(arr[i]);
        }
        
        int size = stack.size();
        int[] answer = new int[size];
        for (int i = 0; i < size; i++){
            answer[i] = stack.pollLast();
        }

        return answer;
    }
}
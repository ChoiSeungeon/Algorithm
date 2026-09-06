import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int size = elements.length;
        
        Set<Integer> nums = new HashSet<>();
        
        for(int len = 1; len <= size; len++){
            for(int i = 0; i < size; i++){
                int sum = 0;
                for(int j = 0; j < len; j++){
                    sum += elements[(i + j) % size];
                }
                nums.add(sum);
            }
        }

        return nums.size();
    }
}
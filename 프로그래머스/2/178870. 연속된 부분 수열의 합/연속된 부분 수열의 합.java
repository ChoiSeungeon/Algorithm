import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int idx1 = 0, idx2 = 0, sum = sequence[idx1], len = 0;
        int minLen = Integer.MAX_VALUE;
        while(true){
            len = idx2 - idx1 + 1;
            
             if (sum == k){
                if (len < minLen){
                    minLen = len;
                    answer[0] = idx1;
                    answer[1] = idx2;
                }
                 
                sum -= sequence[idx1++];
                 
            } else if (sum > k) {
                sum -= sequence[idx1++];
            } else {
                if (idx2 == sequence.length - 1){
                    break;
                }
                 
                sum += sequence[++idx2];
            }
              
        }
        
        return answer;
    }
}
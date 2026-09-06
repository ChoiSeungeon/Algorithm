import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Deque<Integer> que1 = new ArrayDeque<>();
        Deque<Integer> que2 = new ArrayDeque<>();
        
        long sum1 = 0, sum2 = 0;        
        for (int i = 0; i < queue1.length; i++){
            sum1 += queue1[i];
            que1.add(queue1[i]);
            
            sum2 += queue2[i];
            que2.add(queue2[i]);
        }
        
        int answer = 0;
        while(true) {
            
            int num = 0;
            if (sum1 > sum2) {
                num = que1.poll();
                sum1 -= num;
                
                que2.add(num);
                sum2 += num;
            } else if (sum1 < sum2) {
                num = que2.poll();
                sum2 -= num;
                
                que1.add(num);
                sum1 += num;
            } else {
                break;
            }
            
            if (answer > queue1.length * 3){
                answer = -1;
                break;
            }
            
            answer++;
        }
        
        return answer;
    }
}
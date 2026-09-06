import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        LinkedList<Integer> list = new LinkedList<>();
        
        for (int i = 0; i < people.length; i++){
            list.add(people[i]);
        }
        
        Collections.sort(list);
        
        while(!list.isEmpty()){
            int w1 = list.peekFirst();
            int w2 = list.peekLast();
            
            if (w1 + w2 <= limit){
                answer++;
                list.pollFirst();
                list.pollLast();
            } else {
                answer++;
                list.pollLast();
            }
        }
        
        return answer;
    }
}
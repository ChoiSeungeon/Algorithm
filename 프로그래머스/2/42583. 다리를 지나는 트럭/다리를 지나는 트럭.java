import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge = new ArrayDeque<>();
        
        for (int i = 0; i < bridge_length; i++){
            bridge.add(0);
        }
        
        int currWeight = 0, idx = 0;
        int answer = 0;
        while(!bridge.isEmpty()){
            answer++;
            
            currWeight -= bridge.poll();
            if (idx == truck_weights.length)
                continue;
            
            if (currWeight + truck_weights[idx] > weight) {
                bridge.add(0);
                continue;
            }
            
            bridge.add(truck_weights[idx]);
            currWeight += truck_weights[idx++];
        }
        
        return answer;
    }
}
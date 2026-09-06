import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] pTime = new int[progresses.length];
        
        for (int i = 0; i < progresses.length; i++) {
            int time =  (100 - progresses[i]) % speeds[i] == 0 ? (100 - progresses[i]) / speeds[i] : (100 - progresses[i]) / speeds[i] + 1;
            pTime[i] = time;
        }
        
        int preTime = pTime[0], job = 1;
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < pTime.length; i++){
            if (preTime >= pTime[i]){
                job++;
            } else {
                preTime = pTime[i];
                ans.add(job);
                job = 1;
            }
        }
        ans.add(job);
        
        int[] answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}
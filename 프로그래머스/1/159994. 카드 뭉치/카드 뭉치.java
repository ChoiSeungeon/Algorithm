class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int idx1 = 0, idx2 = 0;
        String answer = "Yes";
        for (int i = 0; i < goal.length; i++){
            if (cards1.length > idx1 && cards1[idx1].equals(goal[i])){
                idx1++;
            } else if (cards2.length > idx2 && cards2[idx2].equals(goal[i])) {
                idx2++;
            } else {
                answer = "No";
                break;
            }
        }
        
        return answer;
    }
}
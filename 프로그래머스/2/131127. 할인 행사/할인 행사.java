class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < want.length; j++){
                if(want[j].equals(discount[i])){
                    number[j]--;
                }
            }
        }
        
        boolean firstFlag = true;
        for (int j = 0; j < want.length; j++){
            if (number[j] != 0){
                firstFlag = false;
                break;
            }
        }
        
        if (firstFlag)
            answer++;
        
        for (int i = 0; i < discount.length - 10; i++){
            boolean flag = true;
            
            for (int j = 0; j < want.length; j++){
                if(want[j].equals(discount[i])){
                    number[j]++;
                    break;
                }
            }
            
            for (int j = 0; j < want.length; j++){
                if(want[j].equals(discount[i + 10])){
                    number[j]--;
                    break;
                }
            }
            
            for (int j = 0; j < want.length; j++){
                if (number[j] != 0){
                    flag = false;
                    break;
                }
            }
            
            if (flag){
                answer++;
            }
        }
        
        return answer;
    }
}
import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Deque<Character> stack = new ArrayDeque<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++){
            char c = s.charAt(i); 
            
            if(c == ')'){
                if (stack.isEmpty()){
                    answer = false;
                    break;
                }
                
                stack.pop();
            } else {
                stack.push(c);
            }          
        }
                    
        if (!stack.isEmpty())
            answer = false;

        return answer;
    }
}
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i < moves.length; i++){
            
            int line = moves[i] - 1, pick = 0;
            for(int j = 0; j < board.length; j++){
                if (board[j][line] == 0)
                    continue;
                
                pick = board[j][line];
                board[j][line] = 0;
                break;
            }
            
            if (pick == 0)
                continue;
            
            if (!stack.isEmpty() && stack.peek() == pick){
                answer += 2;
                stack.pop();
            } else {
                stack.push(pick);
            }
        }
        
        return answer;
    }
}
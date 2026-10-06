/*
 * 이전에 풀었던 삼각달팽이에서 해결 방법 착안
 * 삼각 달팽이에서와 같이 지정된 영역의 테두리를 이동하면서
 * 숫자들을 이동 시키고 최소값을 탐색
 * 
 * 최대 실행 시간: 11.33ms
 * 최대 메모리: 96.3MB
 */

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        
    	// 우, 하, 좌, 상
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        // 초기 행렬 생성
        int num = 1;
        int[][] map = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                map[i][j] = num++;
            }
        }
        
        
        int N = queries.length;
        int[] answer = new int[N];
        for(int n = 0; n < N; n++){
            int x1 = queries[n][0] - 1, y1 = queries[n][1] - 1, x2 = queries[n][2] - 1, y2 = queries[n][3] - 1;
            
            int x = x1, y = y1;
            int temp1 = map[x][y], temp2;
            int min = temp1;
            for(int i = 0; i < 4; i++){
                while(true){
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    
                    // 지정된 영역을 벗어나면 방향 전환
                    if(nx < x1 || nx > x2 || ny < y1 || ny > y2)
                        break;
                    
                    x = nx;
                    y = ny;
                    
                    // 숫자 이동
                    temp2 = map[x][y];
                    map[x][y] = temp1;
                    temp1 = temp2;
                    
                    // 최소값 찾기
                    min = Math.min(min, temp1);
                }
            }
            
            answer[n] = min;
        }
        
        return answer;
    }
}
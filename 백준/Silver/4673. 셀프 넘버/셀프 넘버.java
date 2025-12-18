public class Main {
    public static void main(String[] args) {
        boolean[] ans = new boolean[10001];
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < 10001; i++){
            int n = 10;
            int sum = 0;

            sum += i;
            sum += i % 10;
            while(i >= n){
                sum += (i / n) % 10;
                n *= 10;
            }

            if(sum > 10000)
                continue;

            ans[sum] = true;
        }

        for(int i = 1; i < 10001; i++){
            if(!ans[i])
                sb.append(i).append("\n");
        }

        System.out.println(sb);
    }
}

import java.util.*;

// DP top down approach with O(k*m*n) time complexity
public class Task6A {

    // memoization with recurrence relation 
    // max(calculateProfit(m, n, k-1, prices, maxDiff, dp) - prices[i][n],maxDiff[k][i] ) and max(prices[i][n] + maxDiff[k][i],maximum)
    static int  calculateProfit(int m, int n, int k, int[][] prices, int[][] maxDiff, int[][] dp ){
        if(dp[k][n] == -1)
        {
            int maximum = calculateProfit(m, n-1, k,prices, maxDiff, dp);
            for(int i=0; i<m; i++){
                maxDiff[k][i] = Math.max(calculateProfit(m, n, k-1, prices, maxDiff, dp) - prices[i][n],maxDiff[k][i] );
                maximum = Math.max( prices[i][n] + maxDiff[k][i],maximum);
        }
        dp[k][n] = maximum;
        return maximum;
        }
    return dp[k][n];
    }

    // backtrack solution to get buy sell and stock index values
    static ArrayList<Integer> calculateBuyandSell(int m, int n ,int k , int[][] prices, int [][]dp){
        // intialize array list
        ArrayList<Integer> list = new ArrayList<>();
        int inc=k, day = n-1;
        while(true){
            if( inc ==0 || day ==0)
            break;
            if(dp[inc][day] == dp[inc][day-1])
            day = day -1;
            else {
                boolean flag = false;
                for (int i =0; i<m; i++){
                    int profit = dp[inc][day] - prices[i][day];
                    for(int j = day-1; j>=0; j--)
                    {
                        // add into the list only when transaction gives maximum profit that is filled in dp array
                        if((dp[inc-1][j] - prices[i][j]) == profit)
                        {
                            list.add(i+1);
                            list.add(j+1);
                            list.add(day+1);
                            day=j;
                            inc = inc-1;
                            flag = true;
                            break;
                        }
                    }
                    if(flag){
                        break;
                    }
                }
            }
        }
        return list;
    }

    static int maxProfit(int m, int n , int k, int[][] prices){
        int[][] dp = new int[k+1][n];

        // intialize dp table with -1 & 0th row and column elements 0.
        for( int[] i : dp){
            Arrays.fill(i,-1);
        } 
        for (int i=0; i <n; i++){
            dp[0][i] = 0;
        }
        for (int i=0; i<k+1; i++){
            dp[i][0] = 0;
        }

        // intialize maxDiff to 0 then all rows first element to -prices[i][0]
        int[][] maximumDiff = new int[k+1][m];
        for(int[] e : maximumDiff){
            Arrays.fill(e,0);
        }
        for (int j=0; j<k+1;j++){
            for(int i=0; i < m; i++){
                maximumDiff[j][i] = -prices[i][0];
            }
        }

        int maximumProfit = calculateProfit(m, n-1, k, prices, maximumDiff, dp);
        ArrayList<Integer> indices = calculateBuyandSell(m,n,k,prices,dp);

        // print indices stock, buy and sell indices
        for(int i = indices.size() -1; i>=0;i=i-3){
            System.out.println(indices.get(i-2) + " " + indices.get(i-1) + " " + indices.get(i));
        }
        return maximumProfit;

    }
    public void task6a() {
        Scanner scanner = new Scanner(System.in);
		int k = scanner.nextInt();
        int stocks = scanner.nextInt();
        int days= scanner.nextInt();
        
        int[][] prices = new int[stocks][days]; 

        // initialise price matrix with m stocks and n days
        for(int i=0;i<stocks;i++){
            for(int j=0; j<days;j++){
                prices[i][j] = scanner.nextInt();
            }
        }
          maxProfit(stocks,days,k, prices);
        }
}

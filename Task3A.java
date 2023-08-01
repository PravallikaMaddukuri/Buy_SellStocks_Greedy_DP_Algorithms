import java.util.Scanner;

// DP algorithm with memoization with time complexity O(m*n)
public class Task3A {
// recurssive call to calculate maxProfit using memoization
public int max_profit_calc(int[][] dp_max_profit, int[][] prices, int m, int n)
{
    if(dp_max_profit[m][n] == -1)
    {
        // fill dp aray considering nth, n-1 and recurssive call
        // recurrence relation is max(max_profit(dp_max_profit,prices,m,n-1) + prices[m][n] - prices[m][n-1], 0)
        dp_max_profit[m][n] = Math.max(max_profit_calc(dp_max_profit,prices,m,n-1) + prices[m][n] - prices[m][n-1], 0);
    }
    return dp_max_profit[m][n];
}

public void task3a()
{
    Scanner scanner = new Scanner(System.in);
    int stocks = scanner.nextInt();
    int days= scanner.nextInt();
    
    int[][] prices = new int[stocks][days];
    int[][] dp_max_profit = new int[stocks][days]; 
    
    // initialise price matrix with m stocks and n days
    // initialise dp[][] to -1
    for(int i=0;i<stocks;i++){
        for(int j=0; j<days;j++){
            prices[i][j] = scanner.nextInt();
            dp_max_profit[i][j]=-1;
        }
    }

    Task3A t3a = new Task3A();

    for(int i = 0; i < stocks; i++)
    {
        dp_max_profit[i][0]=0;
        // recursive call starting from last day
        t3a.max_profit_calc(dp_max_profit, prices, i, days-1);
    }

    int max_profit = 0;
    int stock_idx = 0;
    int buy_day = 0;
    int sell_day = 0;
    int buy_idx = 0;

    // iterating to get indices of buy, sell and the stock
    for(int i = 0 ; i < stocks ; i++)
    {
        buy_idx = 0;

        for( int j = 1; j < days; j++)
        {
            if(dp_max_profit[i][j]==0)
            {
                buy_idx = j;
            }
            if(dp_max_profit[i][j]>max_profit)
            {

                max_profit = dp_max_profit[i][j];
                stock_idx = i;
                sell_day = j;
                buy_day = buy_idx;
            }
            
        }
    }
    System.out.println(""+(stock_idx+1)+ " "+(buy_day+1)+ " "+(sell_day+1));
}
}

import java.util.Scanner;

// Greedy algorithm time complexity with O(m*n)
public class Task2
{
	public void task2() {
		Scanner scanner = new Scanner(System.in);
        int stocks = scanner.nextInt();
        int days= scanner.nextInt();
        
        // initialise price matrix with m stocks and n days
        int[][] prices = new int[stocks][days]; 
        
        for(int i=0;i<stocks;i++){
            for(int j=0; j<days;j++){
                prices[i][j] = scanner.nextInt();
            }
        }
        
        // initialise maxProfit to 0 to calculate maximum profit in a row
        int maxProfit = 0;
        int stock_idx=-1, buyDay_idx_var=-1, buyDay_idx=-1, sellDay_idx=-1;
        
        for (int i=0; i< stocks; i++){
            int buyPrice = prices[i][0];
            buyDay_idx_var = 0;
            for(int j=1;j< days; j++){
               // replace buy price of the stock when (buyPrice > prices[i][j])
               if(buyPrice > prices[i][j]){
                    buyPrice = prices[i][j];
                    buyDay_idx_var = j;
               }

               // check to calculate maxProfit
               else if(prices[i][j]-buyPrice > maxProfit){
                   maxProfit = prices[i][j]-buyPrice;
                   stock_idx = i;
                   buyDay_idx = buyDay_idx_var;
                   sellDay_idx = j;
               }
            }
        }
        
        System.out.println(""+(stock_idx+1)+ " "+(buyDay_idx+1)+ " "+(sellDay_idx+1));
	}
}
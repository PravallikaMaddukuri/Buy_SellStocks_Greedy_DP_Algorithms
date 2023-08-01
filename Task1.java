import java.util.Scanner;

// Brute force algorithm time complexity with O(m*n^2)
class Task1 {
    public void task1() {
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
        int stock_idx=-1, buyDay_idx=-1, sellDay_idx=-1;
        
        for (int i=0; i< stocks; i++){
            for(int j=0;j< days; j++){
                for(int k=j+1;k<days;k++){
                    int profit;
                    // check if selling price is more than buy price in a row
                    if(prices[i][j]<prices[i][k]){
                        profit = prices[i][k] - prices[i][j];

                    // if profit is more than maxProfit replace maxProfit
                    if(profit>maxProfit){
                        maxProfit = profit;
                        stock_idx = i;
                        buyDay_idx = j;
                        sellDay_idx = k;
                    }
                    }
                }
            }
        }
        
        System.out.println(""+(stock_idx +1)+ " "+(buyDay_idx+1)+ " "+(sellDay_idx+1));
    }
}
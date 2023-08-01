import java.util.Scanner;

// DP algorithm with bottom-up approach with time complexity O(m*n)
public class Task3B
{
	public void task3b() {
		Scanner scanner = new Scanner(System.in);
        int stocks = scanner.nextInt();
        int days= scanner.nextInt();
        
        int[][] prices = new int[stocks][days]; 

        // initialise price matrix with m stocks and n days
        for(int i=0;i<stocks;i++){
            for(int j=0; j<days;j++){
                prices[i][j] = scanner.nextInt();
            }
        }
        
        int maxProfitValue =0, stock_idx=-1, buyDay_idx=-1, sellDay_idx=-1, temp_sell_idx=-1;
        
        int tmp=0;
        for(int k=0;k<stocks;k++){
            int[] ans=new int[days];
            for(int i=1;i<days;i++){
                // filling up ans profit value at a sell index and finding maxprofit in a particular row
                ans[i]=Math.max(prices[k][i]-prices[k][i-1]+ans[i-1],prices[k][i]-prices[k][i-1]);
                if(tmp<ans[i]){
                    tmp=ans[i];
                    temp_sell_idx=i;
                }
            }
            // to calculate max profit in all rows
            if(tmp>maxProfitValue){
                maxProfitValue=tmp;
                stock_idx=k;
                sellDay_idx=temp_sell_idx;
            }
        }
        
        // find buy index in O(n)
        for(int i=0;i<days;i++){
            if(prices[stock_idx][i]==(prices[stock_idx][sellDay_idx]-maxProfitValue)){
                buyDay_idx=i;
            }
        }
        
        System.out.println(""+(stock_idx+1)+" "+(buyDay_idx+1)+" "+(sellDay_idx+1));
        
	}
}

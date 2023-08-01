import java.util.*;
public class Task4 {
    //Recursive function to obtain maximum profit.
    public int maxStockProfit(int prices[][],int i,int buy,int k)
    {
        int m=prices.length;
        int n=prices[0].length;   
        //Base case 
        if((i==n)||k==0)
        {
            if((buy==-1) && k==0)
               return 0;
            else 
               return -214748364;
        }
        int result=-214748364; 

        // if a stock is not bought on the given day
        if(buy==-1)
        {
            for(int u=0;u<m;u++)
            {
                //Maximum profit until this day
                result=Math.max(result,maxStockProfit(prices,i+1,u,k)-prices[u][i]);
            }
        }
        else
        {
            //If stock is bought
            for(int u=0;u<m;u++)
            {
                if(u!=buy)
                {
                    //Maximum profit before the given day + price of the stock on the given day - price of stock on a buy date
                    result=Math.max(result,maxStockProfit(prices,i+1,u,k-1)-prices[u][i]+prices[buy][i]);
                }
            }
            //
            result=Math.max(result,maxStockProfit(prices,i+1,-1,k-1)+prices[buy][i]);
        }
        result=Math.max(result,maxStockProfit(prices,i+1,buy,k));
        return result;
    }
    
    public void task4() {
        Scanner scanner = new Scanner(System.in);
        int k= scanner.nextInt();
        int stocks = scanner.nextInt();
        int days= scanner.nextInt();
        
        int[][] prices = new int[stocks][days]; 
        
        for(int i=0;i<stocks;i++){
            for(int j=0; j<days;j++){
                prices[i][j] = scanner.nextInt();
            }
        }
        // initialise price matrix with m stocks and n days
        Task4 st=new Task4();
        int maxProfit=st.maxStockProfit(prices,0,-1,k);
        System.out.println(maxProfit);
    }
}
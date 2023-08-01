import java.util.*;

// DP bottom up approach with O(k*m*n) complexity
public class Task6B
{
    public int maxProfit(int prices[][], int K) {
        if (K == 0 || prices.length == 0) {
            return 0;
        }
        int T[][] = new int[K+1][prices[0].length];
        
        // filling T[][] table with max profit that can be achieved at ith index in all the m rows
        for (int i = 1; i < T.length; i++) {
            int maxDiff[] = new int[prices.length];
            // initialize max profit with first price element in every row consider if we bought first
            for (int l = 0; l < prices.length; l++)
            {
                maxDiff[l] = -prices[l][0];
            }
            for (int j = 1; j < T[0].length; j++) {
                T[i][j] = T[i][j-1];
                for(int k = 0; k < prices.length; k++)
                {
                    T[i][j] = Math.max(T[i][j], prices[k][j] + maxDiff[k]);
                    maxDiff[k] = Math.max(maxDiff[k], T[i-1][j] - prices[k][j]);
                }
                
            }
        }
        printActualSolution(T, prices);
        return T[K][prices.length-1];
    }

    public void printActualSolution(int T[][], int prices[][]) {
        int i = T.length - 1;
        int j = T[0].length - 1;
        Deque<Integer> stack = new LinkedList<>();
        Deque<Integer> stock_ind = new LinkedList<>();
        while(true) {
            if(i == 0 || j == 0) {
                break;
            }
            if (T[i][j] == T[i][j-1]) {
                j = j - 1;
            } else {
                stack.addFirst(j);
                
                int maxDiff[] = new int[prices.length];
                for (int l = 0; l < prices.length; l++)
                {
                    maxDiff[l] = T[i][j] - prices[l][j];
                }
                
                int flag = 0;
                for (int k = j-1; k >= 0; k--) 
                    {
                        for (int p = 0; p < prices.length; p++)
                        {
                            for(int l = 0; l< prices.length; l++)
                            {
                                // check if the transaction in a row gives maxProfit as filled in table and check validity of transaction
                                if ((T[i-1][k] - prices[p][k] == maxDiff[l]) && ((T[i-1][k] + prices[p][j]- prices[p][k])==T[i][j])) {
                                    i = i - 1;
                                    j = k;
                                    stack.addFirst(j);
                                    stock_ind.addFirst(l);
                                    flag = 1;
                                    break;
                                }
                            }
                            if( flag == 1)
                                break;
                        }
                        if( flag == 1)
                            break;
                    }
                
            }
        }

        // print indices from the stack
        while(!stack.isEmpty()) {
            int st_ind = stock_ind.pollFirst();
            System.out.println((st_ind+1)+ " "+(stack.pollFirst()+1)+" "+(stack.pollFirst()+1));
        }

    }
	public void Task6b() {
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
        
        Task6B st = new Task6B();
        st.maxProfit(prices,k);
	}
}

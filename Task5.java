import java.util.*;
// DP bottom up approach with O(k*m*n^2) complexity
public class Task5
{
    public int maxProfitSolution(int prices[][], int K) {
        if (K == 0 || prices.length == 0) {
            return 0;
        }
        int T[][] = new int[K+1][prices[0].length];

        // filling T[][] table with max profit that can be achieved at ith index in all the m rows
        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                int maxVal = 0;
                for (int l = 0; l < prices.length; l++) {
                    for (int m = 0; m < j; m++)
                    {
                        maxVal = Math.max(maxVal, prices[l][j] - prices[l][m] + T[i-1][m]);
                    }
                }
                T[i][j] = Math.max(T[i][j-1], maxVal);
            }
        }
        printActualSolution(T, prices);
        return T[K][prices.length - 1];
    }

    // back tracking bottom-up DP table 
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

                // initialize maxDiff for each row
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

        // print indices
        while(!stack.isEmpty()) {
            int st_ind = stock_ind.pollFirst();
            System.out.println((st_ind+1)+ " "+(stack.pollFirst()+1)+" "+(stack.pollFirst()+1));
        }

    }
	public void task5() {
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
        Task5 st = new Task5();
        st.maxProfitSolution(prices,k);
	}
}

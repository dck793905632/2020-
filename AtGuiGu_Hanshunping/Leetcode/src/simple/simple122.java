package simple;

import org.junit.Test;

public class simple122 { @Test
    public void test(){
        int[] price = new int[]{1,2,3,4,5};
        System.out.println(maxProfit(price));
    }
    public int maxProfit(int[] prices) {
        int max=0;
        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i+1]-prices[i]>0){
                max+=prices[i+1]-prices[i];
            }
        }
        return max;
    }
}

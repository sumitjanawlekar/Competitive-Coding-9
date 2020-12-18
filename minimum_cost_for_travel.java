// Time Complexity : O(1), dp array and set will be of size max 365
// Space Complexity :O(1), dp array and set will be of size max 365
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

//Three liner approach of your code in plain English
//1. add all the elements from the days array to a set for checking in constant time
//2. create a dp array of size equal to the last day of travel(in the days array) +1.
//3. To fill out the dp array, traverse starting from 1st index of dp array and do following
        //1. calculate the cost for 1 day pass (dp[i-1] + costs[0])
        //2. calculate the cost for 1 week pass (dp[i-7] (or dp[0]) + costs[1])
        //3. calculate the cost for 1 month pass (dp[i-30] (or dp[0]) + costs[2])
        //4. update the current cell with the minimum of above 3
// Return the last cell of the dp array, representing the minimum cost of total travel

// Your code here along with comments explaining your approach

class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        //edge case
        if(days.length == 0 || costs.length == 0) return 0;
        //put all the days in a set
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<days.length; i++){
            set.add(days[i]);
        }
        //dp array of size equal to last travel day + 1 
        int []dp = new int[days[days.length-1] + 1];
        
        //fill the dp array
        for(int i=1; i<dp.length; i++){
            //the day is day of travel
            if(set.contains(i)){
                //compare of the costs of 3 types of pass
                //daily pass
                int daily = dp[i-1] + costs[0];
                //weekly pass
                int weekly = (i-7 >= 0 ? dp[i-7] + costs[1] : dp[0] + costs[1]);
                //monthly pass
                int monthly = (i-30 >= 0 ? dp[i-30] + costs[2] : dp[0] + costs[2]);
                
                //update the current day with the minimum costs of above 3 types
                dp[i] = Math.min(daily, Math.min(weekly, monthly));
            }
            else{//not a day of travel
                dp[i] = dp[i-1];
            }
        }
        //last index in the dp array has the total minimum cost for the travel
        return dp[dp.length-1];
    }
}
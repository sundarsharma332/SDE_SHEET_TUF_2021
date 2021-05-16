
/*Q. Given an integer array nums, return all the triplets [nums[i], 
nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + 
nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Example 2:

Input: nums = []
Output: []

Example 3:

Input: nums = [0]
Output: []
*/


import java.util.*;
import java.io.*;

class ThreSumLeetocde
{

	// the Brute force approach 
	// sort the array and use three nested loop to access 3 elements and find the list of integer 
	// that satisfies the conditions and push it into the list<list<integer>> and return it.
	// time complexity is : 0(N^3) + 0(NLOgN) + 0(N) 
	 // sc = o(1) , if the result doesnt consider as space.
	static List<List<Integer>> BruteForceThreeSum(int nums[]){
		Arrays.sort(nums); // 
		int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n - 2; i++){
			for(int j = i + 1; j < n - 1; j++){
				for(int k= j + 1; k < n; k++){
					if(nums[i] + nums[j] + nums[k] == 0){
						List<Integer> cp = new ArrayList<>();
						cp.add(nums[i]);
						cp.add(nums[j]);
						cp.add(nums[k]);
						if(!res.contains(cp)) res.add(cp);
						}
					}
				}
			}
        
		return res;

	}

	// Sort the array 
	// initilize the final List<List<Integer>> resuts Arraylist.
	// Use one for loop and keep the index of it and for the rest of the number use the two pointer
	// approach to find the pair that sums to the target - arr[i];
	// if the above condition satisfies then create a new arraylist and push all the valid integer
	// and then we will push it to final result.
	// for the duplicates value present in the array , to jump the index of the duplicates,  
	// 1. keep the separate variable for the start.
	// 2. untill the next array element encounter is not same , till then perform the operation,
	// in this case start++ and end--;
	// 0(NlogN) + 0(N) * 0(N) -> 0(N^2);
	// SC -> 0(1);
	static List<List<Integer>> OptimalThreeSum(int arr[]){
		List<List<Integer>> results = new ArrayList<>();
		int n = arr.length;
		if(n < 3) return results;
		Arrays.sort(arr); // 0(NLogN) use merge sort
        for (int i = 0; i < n - 2; i++) { // 0(N)
			if (i == 0 || arr[i] > arr[i - 1]) {
				int start = i + 1;
				int end = n - 1;
 
				while (start < end) { // upto 0(N)
					if (arr[i] + arr[start] + arr[end] == 0) {
						List<Integer> cp = new ArrayList<>();
						cp.add(arr[i]);
						cp.add(arr[start]);
						cp.add(arr[end]);
						results.add(cp);
					}
 
					if (arr[i] + arr[start] + arr[end] < 0) {
						int currentStart = start;
						while (arr[start] == arr[currentStart] && start < end) {
							start++;
						}
					} else {
						int currentEnd = end;
						while (arr[end] == arr[currentEnd] && start < end) {
							end--;
                    }
                }
            }
        }
    }
	    return results;
}



	public static void main(String[] args) 
	{

		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int test = 1; test <= tc; test++){
			int n = sc.nextInt();
            int a[] = new int[n];
            for(int i = 0; i < n ; i++){
                a[i] = sc.nextInt();
            }
			List<List<Integer>> ans = BruteForceThreeSum(a);
			List<List<Integer>> ans1 = OptimalThreeSum(a);
			System.out.println(ans);
			System.out.println(ans1);
		}	
	}
}

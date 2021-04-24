import java.util.*;

public class Main {
    static void pa(int a[]){
        for(int num : a){
            System.out.print(a + " ");
        }
        System.out.println();
    }
    static void print(int a){
        System.out.println(a);
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt(); // test case input
        while(tc-- > 0){
            int n = sc.nextInt();
            int a[] = new int[n];
            for(int i = 0; i < n ; i++){
                a[i] = sc.nextInt();
            }
            int ans = BruteForce(a , n);
            int temp[] = new int[n];
            int ans1 = mergesort_optimal(a , temp , 0 , n-1);
            print(ans);
            print(ans1);
        }
    }
// The Brute Force Solution 
// two for loops : linearly traverse through all the elements in the array and find the
// pair that satisfy the follwoing conditions : a[i] > a[j] or the inversions of the array.
// space complexity of the algorithm is 0(1) : beacuse we are not using any extra space.

    static int BruteForce(int a[] , int n){
        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                if(a[i]  > a[j] && i != j){
                    ans++;
                }
            }
        }
        return ans;
    }

    // This is the Not the Optimal solution but reduce the runtime to much more lower.
    // The merge sort Approach , while Applying Merge sort logic to sort the array , the simple logic will 
    // implemented , if the left or right half of the divided array have the following conditions
    // a[i] > a[j]; then there are exactly mid - i or mid - left number of inversions.
    // the time complexity of this algorithm is 0(nlogn) this is what it takes for merge sort
    // in general. and the logic will placed inside the merge sort approach , so no extra runtime will added.
    // the space complexity is 0(n) , for merge sort standard operation.

    // The merge sort approach will modify the array so , this is the disadvantages of this approach.
    // make sure to make a copy of array to avoid this problem :
    static int mergesort_optimal(int arr[] , int temp[], int left , int right){
        int mid;
        int inv_count = 0;
        if(left < right){
            mid = (right + left)/2;
            inv_count += mergesort_optimal(arr , temp  , left , mid);// recursive call first half
            inv_count += mergesort_optimal(arr , temp , mid + 1 , right);// recursive call second half
            inv_count += Final_merge(arr , temp , left , mid + 1 , right);
        }
        return inv_count;
    }
    static int Final_merge(int arr[] , int temp[] , int left , int mid , int right){
        int inv_count = 0;
        int i = left; // initilize the value of left to i ;
        int j = mid; // initilize the value of mid to j ;
        int k = left; // initilize the value of left to k ;

        while((i <= mid) && (j <= right)){ // within the boundary conditions check
            if(arr[i] <= arr[j]){
                temp[k++] = arr[i++]; // copy to the new array;
            }
            else{// condition satisfies : a[i] > a[j] 
                temp[k++] = arr[j++]; // copy to the new array;
                inv_count += (mid - i); // invrement the no of inversion we get, (mid - i) -> inversion in total
            }
        }
            while(i <= mid - 1){ // copy the remaining elenent in first half
                temp[k++] = arr[i++];
            }
            while(j <= right){ // copy the remaining elenent in second half
                temp[k++] = arr[j++];
            }
            for(i = left; i <= right; i++){ // copy the array from left to right to the temp array
                arr[i] = temp[i];
            }
        return inv_count; // return the final_inversion_Count
    }
}

/*
input : 
2
5
5 3 2 4 1
4
4 3 2 1

output : 
8
6

*/


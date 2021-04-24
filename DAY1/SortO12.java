import java.util.*;

public class Main {
    static void PrintArray(int arr[]){
        for(int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
/*
In the interview , interviewer never asked to do it optimally in the first go , so we have to go with the worst runtime first
so , the first approach is goiing to be, Brute force , i.e, sort the array , the zeros will will come first and so on

time complexity is : 0(nlogn) -> use merge sort
sc -> 0(n) for merge sort
*/
    static void BruteForceSort(int arr[] , int n){
        Arrays.sort(arr); // use merge sort
    }

/*

Method 2 : 

by using third temproary array of n size , or by use count sort.

1. count the number of 0's ,1's and 2's int the givern array by traversing the given array.
2. create a temp array of size n and then , put the number number count of respective number to the new array.

time complexity : 0(n) for counting 0,1,2 and another 0(n) for putting elements back.
overall 0(2N) -> 0(N)

space complexity will be the 0(N) -> because we use the additional array of length of the original array, i.e N.


------> solution ------> secondOptimal();
*/

    static int[] secondOptimal(int arr[], int n){
        int one_count = 0;
        int two_count = 0;
        int zero_count = 0;
        int temp[] = new int[n];
        for(int i = 0; i < n; i++){
            if(arr[i] == 0){
                zero_count++;
            }
            else if(arr[i] == 1){
                one_count++;
            }
            else{
                two_count++;
            }
        }

        for(int i = 0;i < zero_count; i++){
            temp[i] = 0;
        }
        for(int i = zero_count;i < zero_count + one_count; i++){
            temp[i] = 1;
        }
        for(int i = (zero_count + one_count) ;i < n; i++){
            temp[i] = 2;
        }
        return temp;
    }


    /*
    Optimal Approach : 
    Method 1 
 

    Approach:The problem is similar to our old post Segregate 0s and 1s in an array, and both of these problems are variation of famous Dutch national flag problem.
    The problem was posed with three colours, here `0′, `1′ and `2′. The array is divided into four sections: 
        a[1..Lo-1] zeroes (red)
        a[Lo..Mid-1] ones (white)
        a[Mid..Hi] unknown
        a[Hi+1..N] twos (blue)
        If the ith element is 0 then swap the element to the low range, thus shrinking the unknown range.
        Similarly, if the element is 1 then keep it as it is but shrink the unknown range.
        If the element is 2 then swap it with an element in high range.
    Algorithm: 
        Keep three indices low = 1, mid = 1 and high = N and there are four ranges, 1 to low (the range containing 0), low to mid (the range containing 1), mid to high (the range containing unknown elements) and high to N (the range containing 2).
        Traverse the array from start to end and mid is less than high. (Loop counter is i)
        If the element is 0 then swap the element with the element at index low and update low = low + 1 and mid = mid + 1
        If the element is 1 then update mid = mid + 1
        If the element is 2 then swap the element with the element at index high and update high = high – 1 and update i = i – 1. As the swapped element is not processed
        Print the output array.

    Complexity Analysis: 

    Time Complexity: O(n). 
    Only one traversal of the array is needed.
    Space Complexity: O(1). 
    No extra space is required.
    */

    static void Optimal_sort(int arr[] , int n){
        int low = 0;
        int high = n - 1;
        int mid = 0;

       while(mid <= high){
               switch(arr[mid]){
                   case 0: {
                        int temp = arr[low];;
                        arr[low] = arr[mid];
                        arr[mid] = temp;
                        low++;
                        mid++;
                        break;
                   }
                   case 1:{
                       mid++;
                       break;
                   }
                   case 2:{
                        int temp = arr[mid];
                        arr[mid] =  arr[high];
                        arr[high] = temp;
                        high--;
                        break;
            }
         }
       }
    }

    // main method 
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        int e = 1;
        while(tc-- > 0){
            int n = sc.nextInt();
            int a[] = new int[n];
            for(int i = 0; i < n ; i++){
                a[i] = sc.nextInt();
            }
            Optimal_sort(a , n);
            System.out.print("Case #" + (e++) + " : ");
            PrintArray(a);
        }
    }
  
   
}

/*
input sample : 

2
5
0 1 0 1 2
6
0 1 0 2 2 0

output : 
Case #1 : 0 0 1 1 2 
Case #2 : 0 0 0 1 2 2 

*/


package com.company;

import java.util.*;

public class greedy_dc {
   /*https://www.geeksforgeeks.org/maximum-product-subset-array/*/
static int maxProductSubset(int a[],int n){
    if(n==1)
        return a[0];

    int max_neg=Integer.MIN_VALUE;
    int count_neg=0,count_zero=0;
    int prod=1;
    for(int i=0;i<n;i++){
        //if number is 0,we don't multiply it with product
        if(a[i]==0){
            count_zero++;
            continue;
        }
        //count negatives and keep track of maximum valued negative
        if(a[i]<0){
            count_neg++;
            max_neg=Math.max(max_neg,a[i]);
        }
        prod=prod*a[i];
    }
    if(count_zero==n){
        return 0;
    }
    //if there are odd number of negative number
    if(count_neg%2==1){
        //expectional case
        if(count_neg==1 && count_zero>0 && count_zero+count_neg==n){
            return 0;
        }
        prod = prod/max_neg;
    }
    return prod;
}

/*https://www.geeksforgeeks.org/minimum-product-subset-array/*/
static int minProductSubset(int a[],int n){
    if(n==1)
        return a[0];
    int negmax=Integer.MIN_VALUE;
    int posmin=Integer.MAX_VALUE;
    int count_neg =0,count_zero=0;
    int product =1;
    for(int i=0;i<n;i++){
        if(a[i]==0){
            count_zero++;
        }
        if(a[i]<0){
            count_neg++;
            negmax=Math.max(negmax,a[i]);
        }
        if(a[i]>0 && a[i]<posmin)
            posmin=a[i];
        product*=a[i];
    }

    if(count_zero==n || count_neg==0 && count_zero>0)
        return 0;
    if(count_neg==0)
        return posmin;
    if(count_neg%2==0 && count_neg!=0){
        product=product/negmax;
    }
    return product;
}
/*@https://www.geeksforgeeks.org/maximum-product-from-array-such-that-frequency-sum-of-all-repeating-elements-in-product-is-less-than-or-equal-to-2-k/*/

static long maxProd(int arr[],int n,int k){
    long product =1;
    HashMap<Integer,Integer> s = new HashMap<Integer, Integer>();
    Arrays.sort(arr);
    for(int i =0;i<n;i++){
        if(!s.containsKey(arr[i])){
            product=product *arr[i];
            s.put(arr[i],1);
        }
        else
            s.put(arr[i],s.get(arr[i])+1);
    }
    for(int j=n-1;j>=0 && k>0;j--){
        if((k>(s.get(arr[j]-1)) && ((s.get(arr[j])-1)>0))){
            //including the greater repeating values so that
            //product can be maximized
            product*=Math.pow(arr[j],s.get(arr[j])-1);
            k=k-s.get(arr[j])+1;
            s.put(arr[j],0);
        }

        if(k<=(s.get(arr[j])-1)&& ((s.get(arr[j])-1)>0)){
            product*=Math.pow(arr[j],k);
            break;
        }
    }
    return product;
}
/*https://www.geeksforgeeks.org/product-of-non-repeating-distinct-elements-in-an-array/*/
/*TC-O(NLOGN)*/
static int product_of_distinct_elementsofArray(int a[],int n){
    Arrays.sort(a);
    int prod=1*a[0];
    for(int i=0;i<n-1;i++){
        if(a[i]!=a[i+1]){
            prod=prod*a[i+1];
        }
    }
    return prod;
}
/*same question as above ,TC-O(N) AND SC-O(N)*/
static int product_of_distinct_elementsofarray(int a[],int n){
    int prod=1;
    HashSet<Integer> s = new HashSet<>();
    for(int i=0;i<n;i++){
        if(!s.contains(a[i])){
            prod *=a[i];
            s.add(a[i]);
        }
    }
    return prod;
}

/*https://www.youtube.com/watch?v=eKp56OLhoQs
* https://www.geeksforgeeks.org/sieve-of-eratosthenes/*/
static void seiveOfErathenes(boolean prime[],int n){
    //ArrayList<Integer> l = new ArrayList<>();
    prime[0]=false;
    prime[1]=false;
    for(int p=2;p*p<=n;p++){
        //if prime[p] is not changed,then it is a prime
        if(prime[p]){
            //update all the multiples of p
            for(int i=p*p;i<=n;i+=p)
                prime[i]=false;
        }
    }
//    for(int i=2;i<=n;i++){
//        if(prime[i]==true)
//            l.add(i);
//    }
}

/*@https://www.geeksforgeeks.org/sum-of-elements-in-an-array-having-prime-frequency/*/
static int SumOfElementsHavingPrimeFrequency(int a[],int n){
    boolean prime[] = new boolean[n+1];
    Arrays.fill(prime,true);
    seiveOfErathenes(prime,n+1);
    HashMap<Integer,Integer> m = new HashMap<>();
    for(int i =0;i<n;i++){
        if(m.containsKey(a[i]))
            m.put(a[i],m.get(a[i])+1);
        else
            m.put(a[i],1);
    }
    int sum =0;
    for(Map.Entry<Integer,Integer> entry :m.entrySet()){
        int key=entry.getKey();
        int value = entry.getValue();
        if(prime[value]){
            sum+=(key);
        }
    }
    return sum;
}

/*@https://www.geeksforgeeks.org/product-of-elements-in-an-array-having-prime-frequency/*/
static int ProductOfElementsHavingPrimeFrequency(int a[],int n ){
    boolean prime[] = new boolean[n+1];
    Arrays.fill(prime,true);
    seiveOfErathenes(prime,n+1);
    HashMap<Integer,Integer> m = new HashMap<>();
    for(int i =0;i<n;i++){
        if(m.containsKey(a[i]))
            m.put(a[i],m.get(a[i])+1);
        else
            m.put(a[i],1);
    }
    int prod =1;
    for(Map.Entry<Integer,Integer> entry :m.entrySet()){
        int key=entry.getKey();
        int value=entry.getValue();
        if(prime[value]){
            prod*=(key);
        }
    }
    return prod;
}
/*https://www.geeksforgeeks.org/minimum-steps-to-make-the-product-of-the-array-equal-to-1/*/
static int MinStep(int a[],int n){
    int pos=0,neg=0,zero=0;
    int step=0;
    for(int i=0;i<n;i++){
        if(a[i]==0){
            zero++;
        }
        else if(a[i]<0){
            neg++;
            //cost needed to make it -1
            step=step +(-1-a[i]);
        }
        else{
            pos++;
            //cost needed to make it 1
            step=step +(a[i]-1);
        }
    }
    if(neg%2==0){
        //as the count of negative is even
        //so we just have to changes 0s to 1s
        //total cost will be count of zeros
        step=step+zero;
    }
    else{
        //as the count of negatives is odd
        //therefore we check if there are zeros present
        if(zero>0){
            //if there are zeros present then
            //we convert one 0 to -1 and remaining to 1
            step=step+zero;
        }
        else {
            //If there are no zeroes present
            //then we have to change one -1 to 1
            //The cost for same is 2
            step=step+2;
        }
    }

    return step;
}
/*https://www.geeksforgeeks.org/maximize-array-sum-k-negations-set-2/
O(n+klogn)*/
static void maxSumAfterKNegation(int a[],int k){
    Arrays.sort(a);
    int sum=0;
    int min =Integer.MAX_VALUE;
    for(int i=0;k>0 && i<a.length && a[i]<0;i++){
        a[i]=-a[i];
        k--;
    }
    for(int i=0;i<a.length;i++){
        sum+=a[i];
        if(a[i]<min){
            min = a[i];
            System.out.println("i"+i+"min"+min);
        }
    }
    System.out.println("sum"+sum);
    System.out.println("min"+min);
    System.out.println("k%2*min*2"+(k%2)*min*2);
    int res = sum -(k%2)*min*2;
    System.out.println(res);
}
/*https://www.geeksforgeeks.org/maximize-array-sum-k-negations-set-2/*/
/*Below solution is same as above but by using priority queue*/
static void maxSumAfterKneg(int a[],int k){
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for(int x: a)
        pq.add(x);
    while(k-- > 0){
        int temp =pq.poll();
        temp*=-1;
        pq.add(temp);
    }
    int sum=0;
    for(int x:pq){
        sum+=x;
    }
    System.out.println(sum);
}
/*https://www.geeksforgeeks.org/maximum-sum-increasing-order-elements-n-arrays/*/
/*O(mn)*/
static int maxSum_from_n_array(int a[][],int n){
    int sum =0;
    int curr =0;
    int max =0;
    for(int i=a.length-1;i>=0;i--){
        for(int j=0;j<a[i].length;j++){
                if(max!=0){
                    if(a[i][j]<max)
                        curr = Math.max(curr,a[i][j]);
                    else
                        continue;
                }
                else{
                    curr = Math.max(curr,a[i][j]);
                }
        }
        if(max>curr || sum==0){
            max=curr;
            sum+=max;
            curr=0;
        }
        else{
            System.out.println(0);
            return 0;
        }
    }
    System.out.println(sum);
    return sum;
}
/*@https://www.geeksforgeeks.org/maximum-sum-absolute-difference-array/*/
static int MaxSumDiff(int a[],int n){
    ArrayList<Integer> fl = new ArrayList<>();
    Arrays.sort(a);
    for(int i=0;i<n/2;++i){
        //n/2 because we are putting 2 elements at a time
        fl.add(a[i]);
        fl.add(a[n-i-1]);
    }
    int maxsum =0;
    for(int i=0;i<n-1;++i){
        maxsum=maxsum + Math.abs(fl.get(i)-fl.get(i+1));
    }
    maxsum=maxsum + Math.abs(fl.get(n-1)-fl.get(0));
    return maxsum;
}
/*https://www.geeksforgeeks.org/partition-into-two-subarrays-of-lengths-k-and-n-k-such-that-the-difference-of-sums-is-maximum/*/
static int maxDifference(int a[],int n,int k){
    int M,S=0,S1=0,max_dif=0;
    for(int i=0;i<n;i++){
        S+=a[i];
    }
    int temp;
    //sort array in descending order
    for(int i=0;i<n;i++){
        for(int j=i+1;j<n;j++){
            if(a[i]<a[j]){
                temp=a[i];
                a[i]=a[j];
                a[j]=temp;
            }
        }
    }
    M=Math.max(k,n-k);
    for(int i=0;i<M;i++){
        S1+=a[i];
    }
    max_dif=S1 - (S-S1);
    return max_dif;
}
/*@https://www.geeksforgeeks.org/minimum-sum-product-two-arrays/*/
static int minProd(int a[],int b[],int n,int k){
    int diff=0,res=0;
    int temp=0;
    for(int i=0;i<n;i++){
        int pro = a[i]*b[i];
        res=res+pro;
        if(pro<0 && b[i]<0)
            temp =(a[i]+2*k)*b[i];
        else if(pro<0 && a[i]<0)
            temp=(a[i]-2*k)*b[i];
        else if(pro>0 && b[i]<0)
            temp=(a[i]+2*k)*b[i];
        else if(pro>0 && a[i]<0)
            temp=(a[i]-2*k)*b[i];

        int d = Math.abs(pro-temp);
        if(d>diff)
            diff=d;
    }
    return res-diff;
}

static int maxCrossingSum(int arr[],int l,int m,int h){
    int sum=0;
    int left_sum=Integer.MIN_VALUE;
    for (int i=m;i>=1;i--){
        sum= sum +arr[i];
        if (sum>left_sum)
            left_sum=sum;
    }
    sum=0;
    int right_sum=Integer.MIN_VALUE;
    for (int i=m+1;i<=h;i++){
        sum=sum+arr[i];
        if (sum>right_sum)
            right_sum=sum;
    }

    return left_sum+right_sum;
}
static int maxSubArraySum(int arr[],int l,int h){
    if (l==h)
        return arr[l];
    int m =(l+h)/2;
    return Math.max(Math.max(maxSubArraySum(arr,l,m),
            maxSubArraySum(arr,m+1,h)),maxCrossingSum(arr,l,m,h));
}
static class Pair{
    private int start,finish;
    public Pair(int start,int finish){
        this.start=start;
        this.finish=finish;
    }
    public int getFinish(){
        return finish;
    }
    public int getStart(){
        return start;
    }
    public String toString(){
        return "{"+getStart()+","+getFinish()+"}";
    }
}
static Set<Integer> selectActivity(List<Pair> activities){
    int k =0;
    Set<Integer> out = new HashSet<>();
    out.add(0);
    for (int i=1;i<activities.size();i++){
        if (activities.get(i).getStart() >= activities.get(k).getFinish()){
            out.add(i);
            k=i;
        }
    }
    return out;
}

/*https://www.geeksforgeeks.org/policemen-catch-thieves/
the idea is to get lowest index of policeman p and thief t.
make an allotment if |p-t|<=k and increment to the next p and t found
otherwise increment min(p,t) to the next p or t found
repeat above two steps until next p and t are found
return the allotments made
*/

static int PoliceThief(char[] a,int n,int k){
    int res=0;
    ArrayList<Integer> thi = new ArrayList<>();
    ArrayList<Integer> pol = new ArrayList<>();
    for(int i=0;i<n;i++){
        if (a[i]=='P')
            pol.add(i);
        else if (a[i]=='T')
            thi.add(i);
    }
    int l=0,r=0;
    while (l<thi.size() && r<pol.size()){
        if (Math.abs(thi.get(l)-pol.get(r))<=k){
            res++;
            l++;
            r++;
        }
        else if (thi.get(l)<pol.get(r))
            l++;
        else
            r++;
    }
    return res;
}

/*
Let there be a subarray (i, j) whose sum is divisible by k
  sum(i, j) = sum(0, j) - sum(0, i-1)
Sum for any subarray can be written as q*k + rem where q
is a quotient and rem is remainder
Thus,
    sum(i, j) = (q1 * k + rem1) - (q2 * k + rem2)
    sum(i, j) = (q1 - q2)k + rem1-rem2

We see, for sum(i, j) i.e. for sum of any subarray to be
divisible by k, the RHS should also be divisible by k.
(q1 - q2)k is obviously divisible by k, for (rem1-rem2) to
follow the same, rem1 = rem2 where
    rem1 = Sum of subarray (0, j) % k
    rem2 = Sum of subarray (0, i-1) % k
    @https://www.geeksforgeeks.org/count-sub-arrays-sum-divisible-k/
* So if any sub-array sum from index i’th to j’th is divisible by k then we can saya[0]+…a[i-1] (mod k) = a[0]+…+a[j] (mod k)
So we need to find such a pair of indices (i, j) that they satisfy the above condition. Here is the algorithm :
Make an auxiliary array of size k as Mod[k] . This array holds the count of each remainder we are getting after dividing cumulative sum till any index in arr[].
Now start calculating cumulative sum and simultaneously take it’s mod with K, whichever remainder we get increment count by 1 for remainder as index in Mod[] auxiliary array. Sub-array by each pair of positions with same value of ( cumSum % k) constitute a continuous range whose sum is divisible by K.
Now traverse Mod[] auxiliary array, for any Mod[i] > 1 we can choose any to pair of indices for sub-array by (Mod[i]*(Mod[i] – 1))/2 number of ways . Do the same for all remainders < k and sum up the result that will be the number all possible sub-arrays divisible by K.*/
static int subarray_with_sum_div_by_k(int a[],int n,int k){
    int mod[]=new int[k];
    Arrays.fill(mod,0);
    int currSum=0;
    for (int i=0;i<n;i++){
        currSum+=a[i];
        mod[((currSum%k)+k)%k]++;
    }
    int result=0;
    for (int i=0;i<k;i++){
        if (mod[i]>1){
            result+=(mod[i]*(mod[i]-1))/2;
        }
    }
    result+=mod[0];
    return result;
}
public static void main(String[] args){
  /*int a[]={-1,-1,-2,4,3};
  int n = a.length;
  System.out.println(maxProductSubset(a,n));

  int arr[] ={5,4,6,5,4,6};
  int n1 = arr.length;
  System.out.println("sum of elements having prime frequency"+SumOfElementsHavingPrimeFrequency(arr,n1));
  System.out.println("product of elements having prime frequency"+ProductOfElementsHavingPrimeFrequency(arr,n1));
  int array[] = { 0, -2, -1, -3, 4 };
  int n2 =array.length;
  System.out.println(MinStep(array,n2));*/

  /*int a[]={-2, 0, 5, -1, 2};
  maxSumAfterKNegation(a,5);
  int array[]= {1,2,4,8};
  int n =array.length;
  System.out.println(MaxSumDiff(array,n));
    int arr[] = { 8, 4, 5, 2, 10 };
    int N = arr.length;
    int k = 2;
    System.out.println(maxDifference(arr, N, k));*/

    //int a[] = { 2, 3, 4, 5, 4 };
   // int b[] = { 3, 4, 2, 3, 2 };
    int a[] = {1, 2, -3};
    int b[]  = {-2, 3, -5};
    int n = 3, k = 5;
    System.out.println(minProd(a,b,n,k));
    List<Pair> activities = Arrays.asList(new Pair(1, 4), new Pair(3, 5),
            new Pair(0, 6), new Pair(5, 7), new Pair(3, 8),
            new Pair(5, 9), new Pair(6, 10), new Pair(8, 11),
            new Pair(8, 12), new Pair(2, 13), new Pair(12, 14));
     Collections.sort(activities, new Comparator<Pair>() {
         @Override
         public int compare(Pair o1, Pair o2) {
             return o1.getFinish() - o2.getFinish();
         }
     });
     Set<Integer> res = selectActivity(activities);
     for (Integer i:res){
         System.out.println(activities.get(i));
     }
 }
}

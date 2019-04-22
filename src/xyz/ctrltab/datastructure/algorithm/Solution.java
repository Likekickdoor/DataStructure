package xyz.ctrltab.datastructure.algorithm;

public class Solution {
	static int fun1(int n) {
		if(n<=1)
			return n;
		else
			return fun1(n-1)+fun1(n-2);
	}
	static int fun2(int n) {
		if(n<=1)
			return n;
		int fn2 = 0;
		int fn1 = 1;
		int temp = fn1+fn2;
		n-=1;
		while(--n>0) {
			fn2 = fn1;
			fn1 = temp;
			temp = fn2+fn1;
		}
		return temp;
	}
    public static void main(String[] args) {
//    	System.out.println(Solution.fun2(8));
    	int n=1;
    	for(int i=1;i<=32;i++) {
    		n = n<<1;
    	}
    	System.out.print(n);
    }
}

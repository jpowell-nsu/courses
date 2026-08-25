package fibo;

import java.util.Arrays;

public class Fibo {

	public static void main(String[] args) {
			
		int[] memo = new int[100];
		
		System.out.println(Arrays.toString(memo));
		Arrays.fill(memo, -1);
		
		System.out.println(   fibo(5, memo)   );
		
		System.out.println(Arrays.toString(memo));
	}

	public static int fibo(int n, int[] memo) {
		if (  memo[n] != -1  ) {
		 return memo[n];
		}
		else {
			if (n <= 1) {
				memo[n] = n;
				System.out.println(Arrays.toString(memo));
				return memo[n];
			}
			memo[n] = fibo(n-1, memo) + fibo(n-2, memo);
			System.out.println(Arrays.toString(memo));
			return memo[n];
		}
		
		
	}

}

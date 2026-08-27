package fibo;

import java.util.ArrayList;
import java.util.Arrays;

public class Fibo {

	public static void main(String[] args) {
			
		//int[] memo = new int[100];
		ArrayList<Integer> memolist = new ArrayList<>();
		//
		//for (int i = 0; i < 100; i++) {
		//	memolist.add(0);
		//}
	
		//memolist.add(5);
		//memolist.add(0, 6);
		//memolist.add(9, 42);

		System.out.println(memolist.size());
		System.out.println(memolist);
		//memolist.
		
		//System.out.println(Arrays.toString(memo));
		//Arrays.fill(memo, -1);
		
		System.out.println(   fibo(5, memolist)   );
		
		//System.out.println(Arrays.toString(memo));
	}

	public static int fibo(int n, ArrayList<Integer> memolist) {
		for (int i = 0; i <= n; i++) {
			memolist.add(0);
		}
		System.out.println(memolist);
		
		if (  memolist.get(n) != 0  ) {
		 return memolist.get(n);
		}
		else {
			if (n <= 1) {
				memolist.set(n, n);
				
				//System.out.println(memolist);
				return memolist.get(n);
			}
			int result = fibo(n-1, memolist) + fibo(n-2, memolist);
			memolist.set(n, result);
			
			//System.out.println(memolist);
			return memolist.get(n);
		}
		
	}

}

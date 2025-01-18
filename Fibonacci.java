package code;

import java.util.Scanner;

public class Fibonacci {
	public static int fibonacciLap(int n) {
       
        int a = 0; // Fibonacci(0)
        int b = 1; // Fibonacci(1)
        int fib = 0; // Biến để lưu giá trị Fibonacci hiện tại
        
        for (int i = 2; i <= n; i++) {
            fib = a + b; // Tính giá trị Fibonacci tiếp theo
            a = b;       // Cập nhật a
            b = fib;     // Cập nhật b
        }
        
        return fib; // Trả về giá trị Fibonacci thứ n
    }
	
	public static int fibonacciDequy(int n) {
		if (n <= 0) return 0; // Trường hợp không hợp lệ
        if (n == 1) return 1; // Fibonacci(1) = 1
        return fibonacciDequy(n - 1) + fibonacciDequy(n - 2); // Đệ quy
    }
	
	 public static void main(String[] args) {
	        int n = 20;
	        int  i;
	        long sum = 0;
	        long sum1 = 0;
	        long startTime;
	        long endTime;
	        long start;
	        long end;
	        
	        // Lap
	        for (i=0;i<10000;++i) {
	        	startTime = System.nanoTime();
	        	fibonacciLap(n);
	        	endTime = System.nanoTime();
	        	sum += (endTime - startTime) ;// Thời gian chạy tính bằng nano giây
	        }
	        int result = fibonacciLap(n);
	        System.out.println("Lặp:");
	        System.out.println("Giá trị Fibonacci thứ " + n + " là: " + result);
	        System.out.println("Thời gian chạy là: " + sum/10000 + " nanoseconds");
	        
	        // De quy
	        for(i=0;i<10000;++i) {
	        start = System.nanoTime();
	        fibonacciDequy(n);
	        end = System.nanoTime();
			sum1 += end - start; // Thời gian chạy tính bằng nano giây
	        }	
	        int result1 = fibonacciDequy(n);
			System.out.println("Đệ quy:");
	        System.out.println("Giá trị Fibonacci thứ " + n + " là: " + result1);
	        System.out.println("Thời gian chạy là: " + sum1/10000 + " nanoseconds");
	    }
}



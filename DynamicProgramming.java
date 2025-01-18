package code;

public class DynamicProgramming {
	// Hàm tính số Fibonacci n bằng đệ quy
	public static long fibonacci(int n) {
        // Nếu n nhỏ hơn hoặc bằng 1, trả về n
        if (n <= 1) {
            return n;
        }
        
        // Gọi đệ quy cho n-1 và n-2
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
	
	// Mảng để lưu trữ các giá trị Fibonacci đã tính toán
	private static long[] memo;

    // Hàm tính số Fibonacci n với Dynamic Programming
    public static long fibonacciuu(int n) {
        // Nếu n nhỏ hơn hoặc bằng 1, trả về n
        if (n <= 1) {
            return n;
        }
        
        // Kiểm tra xem giá trị đã được tính toán chưa
        if (memo[n] != -1) {
            return memo[n];
        }

        // Tính toán giá trị Fibonacci và lưu vào mảng
        memo[n] = fibonacciuu(n - 1) + fibonacciuu(n - 2);
        return memo[n];
    }
    
    public static void main(String[] args) {
        int n = 25; // Ví dụ: tính số Fibonacci thứ 50
        int i;
        // Khởi tạo mảng memo với kích thước n + 1 và giá trị -1
        memo = new long[n + 1];
        for ( i = 0; i <= n; i++) {
            memo[i] = -1;
        }

        // Tính và in ra số Fibonacci thứ n
        long startTime;
        long endTime;
        long sum = 0;
        // Tính và in ra số Fibonacci thứ n
        for(i=0;i<10000;++i) {
        startTime = System.nanoTime();
        fibonacciuu(n);
        endTime = System.nanoTime(); // Kết thúc đo thời gian
        sum += (endTime - startTime);
        }
        long result = fibonacciuu(n);
        System.out.println("Đệ quy tối ưu - DynamicProgramming: ");
        System.out.println("Fibonacci số thứ " + n + " là: " + result);
        System.out.println("Thời gian chạy trung bình: " + sum/10000 + " nanoseconds");
        
        long start;
        long end;
        long sum1 = 0;
        // Tính và in ra số Fibonacci thứ n
        for(i=0;i<10000;++i) {
        start = System.nanoTime();
        fibonacci(n);
        end = System.nanoTime(); // Kết thúc đo thời gian
        sum1 += (end - start);
        }
        long result1 = fibonacci(n);
        System.out.println("Fibonacci số thứ " + n + " là: " + result1);
        System.out.println("Thời gian thực thi trung bình: " + sum1/10000 + " nanoseconds");
    }
}

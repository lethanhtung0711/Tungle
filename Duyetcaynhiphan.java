package code;

import java.util.Stack;


public class Duyetcaynhiphan{
	
	// Cấu trúc cây nhị phân
	static class Node {
		int data;
		Node left, right;

		public Node(int item) {
			data = item;
			left = right = null;
		}
	}
	
	// Duyệt cây nhị phân theo chiều sâu (tiền thứ tự) bằng thuật toán lặp
	public void preorderTraversalLap(Node root, StringBuilder result) {
        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            result.append(current.data).append(" "); // Ghi dữ liệu vào StringBuilder

            // Đẩy phải trước, vì ngăn xếp LIFO (Last In First Out)
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }
	
	public void preorderTraversalDequy(Node root, StringBuilder result) {
        if (root == null) return;

        // Ghi dữ liệu vào StringBuilder
        result.append(root.data).append(" ");
        
        // Gọi đệ quy cho cây con trái
        preorderTraversalDequy(root.left, result);
        // Gọi đệ quy cho cây con phải
        preorderTraversalDequy(root.right, result);
    }
	
	public static void main(String[] args) {
        Duyetcaynhiphan tree = new Duyetcaynhiphan();

        // Tạo cây nhị phân
        Node root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);
        root.left.left = new Node(5);
        root.left.right = new Node(4);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left.left = new Node(15);
        root.left.left.right = new Node(17);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(23);
        root.right.left.left = new Node(11);
        root.right.left.right = new Node(21);
        root.right.right.left = new Node(19);
        root.right.right.right = new Node(8);
        StringBuilder result = new StringBuilder(); // Khởi tạo StringBuilder để lưu kết quả

        // Gọi hàm duyệt
        long startTime = System.nanoTime(); // Bắt đầu đo thời gian
        tree.preorderTraversalLap(root, result);
        long endTime = System.nanoTime(); // Kết thúc đo thời gian
        long averageDuration = endTime - startTime;
        // In kết quả duyệt 
        System.out.println("Kết quả duyệt theo chiều sâu (tiền thứ tự): " + result.toString().trim());
        System.out.println("Thời gian thực thi trung bình: " + averageDuration + " nanoseconds");
        
        //Dequy
        StringBuilder result1 = new StringBuilder(); // Khởi tạo StringBuilder để lưu kết quả

        // Gọi hàm duyệt
        long start = System.nanoTime();
        tree.preorderTraversalDequy(root, result1);
        long end = System.nanoTime(); // Kết thúc đo thời gian
        long average = end - start;
        // In kết quả duyệt chỉ một lần
        System.out.println("Kết quả duyệt theo chiều sâu (tiền thứ tự): " + result1.toString().trim());
        System.out.println("Thời gian thực thi trung bình: " + average + " nanoseconds");
    }
}
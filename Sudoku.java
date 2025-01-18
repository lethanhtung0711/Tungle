package Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.util.Collections;
import java.util.ArrayList;

public class Sudoku extends JFrame implements ActionListener, KeyListener {
    int[][] a = new int[9][9]; // Ma trận hiển thị
    int[][] A = new int[9][9]; // Lời giải chuẩn
    JButton[][] bt = new JButton[9][9];
    JButton newGame_bt, solve_bt;
    JLabel attemptsLabel;
    final int REMOVED_CELLS = 45; // Số ô bị xóa cố định
    int remainingAttempts = 5; // Số lần sai tối đa

    public Sudoku() {
        super("VKU - Sudoku");
        init();
    }

    public void init() {
        Container cn = this.getContentPane();
        JPanel pn2 = new JPanel();
        pn2.setLayout(new FlowLayout());

        newGame_bt = new JButton("New Game");
        newGame_bt.addActionListener(this);

        solve_bt = new JButton("Solve Sudoku");
        solve_bt.addActionListener(this);

        attemptsLabel = new JLabel("Lần chơi còn lại: " + remainingAttempts);
        pn2.add(newGame_bt);
        pn2.add(solve_bt);
        pn2.add(attemptsLabel);
        cn.add(pn2, "North");

        JPanel pn = new JPanel();
        pn.setLayout(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bt[i][j] = new JButton();
                bt[i][j].addActionListener(this);
                bt[i][j].addKeyListener(this); // Lắng nghe phím nhập
                bt[i][j].setActionCommand(i + " " + j);
                bt[i][j].setFont(new Font("UTM Micra", Font.BOLD, 20));
                bt[i][j].setForeground(Color.BLACK);
                bt[i][j].setBackground(Color.WHITE);

                // Thêm viền để phân biệt các ô vuông 3x3
                int top = (i % 3 == 0) ? 3 : 1;
                int left = (j % 3 == 0) ? 3 : 1;
                int bottom = (i == 8) ? 3 : 1;
                int right = (j == 8) ? 3 : 1;
                bt[i][j].setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));

                pn.add(bt[i][j]);
            }
        }
        cn.add(pn, "Center");

        this.setSize(700, 700);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        generateRandomSudoku(); // Tạo bảng Sudoku mới
    }

    public void generateRandomSudoku() {
        // Tạo bảng Sudoku chuẩn ban đầu
        String baseSudoku = "543691728987432165261875934634519872872346591195728643428957316716283459359164287";
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                A[i][j] = baseSudoku.charAt(i * 9 + j) - '0';

        // Hoán đổi ngẫu nhiên các số trong bảng
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                A[i][j] = numbers.get(A[i][j] - 1);
            }
        }

        // Sao chép ma trận lời giải vào bảng hiện tại
        for (int i = 0; i < 9; i++) {
            System.arraycopy(A[i], 0, a[i], 0, 9);
        }

        // Xóa ngẫu nhiên các ô theo số lượng được chỉ định
        for (int k = 0; k < REMOVED_CELLS; k++) {
            int i = (int) (Math.random() * 9);
            int j = (int) (Math.random() * 9);
            a[i][j] = 0;
        }

        // Hiển thị bảng Sudoku trên giao diện
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bt[i][j].setText(a[i][j] == 0 ? "" : String.valueOf(a[i][j]));
                if (a[i][j] != 0) bt[i][j].setEnabled(false); // Vô hiệu hóa ô đã có giá trị
            }
        }
    }

    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) return true;
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num || 
                board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == num)
                return false;
        }
        return true;
    }

    public boolean checkIfCompleted() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (a[i][j] != A[i][j]) return false; // Nếu có ô không đúng, chưa hoàn thành
            }
        }
        return true; // Hoàn thành nếu tất cả các ô đều đúng
    }

    public void updateBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bt[i][j].setText(String.valueOf(A[i][j]));
                bt[i][j].setForeground(Color.BLUE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == solve_bt) {
            if (solveSudoku(a)) {
                updateBoard();
                JOptionPane.showMessageDialog(this, "Trò chơi đã được giải thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Không thể giải trò chơi này!");
            }
        } else if (e.getSource() == newGame_bt) {
            this.dispose();
            new Sudoku();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        JButton btn = (JButton) e.getComponent();
        String command = btn.getActionCommand();
        int i = Integer.parseInt(command.split(" ")[0]);
        int j = Integer.parseInt(command.split(" ")[1]);

        if (Character.isDigit(e.getKeyChar())) {
            int num = e.getKeyChar() - '0';
            if (isValid(a, i, j, num)) {
                a[i][j] = num;
                btn.setText(String.valueOf(num));
                btn.setForeground(Color.BLUE);
                if (checkIfCompleted()) {
                    JOptionPane.showMessageDialog(this, "Chúc mừng! Bạn đã hoàn thành trò chơi!");
                }
            } else {
                remainingAttempts--;
                attemptsLabel.setText("Lần chơi còn lại: " + remainingAttempts);
                JOptionPane.showMessageDialog(this, "Số bạn nhập không hợp lệ. Hãy thử lại!", "Thông báo lỗi", JOptionPane.WARNING_MESSAGE);
                if (remainingAttempts <= 0) {
                    JOptionPane.showMessageDialog(this, "Bạn đã thua, hãy thử lại!");
                    this.dispose();
                    new Sudoku();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Sudoku();
    }
}

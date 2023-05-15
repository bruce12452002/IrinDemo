package teach0516.zetcode;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {

    private JLabel statusbar;
    private Board board;

    public Tetris() {
        initUI();
    }

    public Tetris(Board board) {
        board.setStatusbar(this.getStatusBar());
        this.board = board;
        initUI();
    }

    private void initUI() {
        statusbar = new JLabel(" 0");
        statusbar.setFont(new Font("Serif", Font.PLAIN, 32));
        add(statusbar, BorderLayout.NORTH);

        if (board == null)
            board = new Board(this);
        add(board);
        board.start();

        setTitle("Tetris");
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 關閉時，整個畫面關掉
        setLocationRelativeTo(null); // 置中顯示
    }

    public JLabel getStatusBar() {
        return statusbar;
    }

    public void dropDown() {
        board.dropDown();
    }

    public boolean isGameOver() {
        return board.getGameOver();
    }

    public int getLinesRemoved() {
        return board.getLinesRemoved();
    }

    public static void main(String[] args) {
        // swing is non-thread-safe
        EventQueue.invokeLater(() -> {
            Tetris game = new Tetris();
            game.setVisible(true);
        });
    }
}
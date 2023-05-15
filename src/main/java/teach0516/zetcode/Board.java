package teach0516.zetcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JPanel {

    private final int BOARD_WIDTH = 10; // 左右方塊有幾個
    private final int BOARD_HEIGHT = 22; // 上下方塊有幾個
    private final int INITIAL_DELAY = 100; // 開始玩的時候，慢個 100 毫秒才開始
    private final int PERIOD_INTERVAL = 1000; // 每 300 毫秒方塊往下

    private Timer timer;
    private boolean isFallingFinished = false; // 方塊是否掉到最下面了
    private boolean isStarted = false;
    private boolean isPaused = false;
    private int numLinesRemoved = 0; // 方塊成功消失的行數
    public int curX = 0;
    public int curY = 0;
    private JLabel statusbar; // 顯示在左上方的文字
    private Shape curPiece;
    private Shape.Tetrominoe[] board;

    public Board(Tetris parent) {
        statusbar = parent.getStatusBar();
        initBoard();
    }

    public Board() {
        initBoard();
    }

    private void initBoard() {
        setFocusable(true);
        curPiece = new Shape();
        board = new Shape.Tetrominoe[BOARD_WIDTH * BOARD_HEIGHT];
        addKeyListener(new TAdapter());
        clearBoard();
    }

    public void setStatusbar(JLabel status_label) {
        statusbar = status_label;
    }

    private int squareWidth() {
        return (int) getSize().getWidth() / BOARD_WIDTH;
    }

    private int squareHeight() {
        return (int) getSize().getHeight() / BOARD_HEIGHT;
    }

    private Shape.Tetrominoe shapeAt(int x, int y) {
        return board[(y * BOARD_WIDTH) + x];
    }

    public void start() {
        isStarted = true;
        setStatusText("");
        clearBoard();
        newPiece();
        numLinesRemoved = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(),
                INITIAL_DELAY, PERIOD_INTERVAL);
    }

    private void pause() {
        if (!isStarted) {
            return;
        }
        isPaused = !isPaused;
        if (isPaused) {
            setStatusText("paused");
        } else {
            setStatusText(String.valueOf(numLinesRemoved));
        }
    }

    private void setStatusText(String text) {
        if (statusbar != null)
            statusbar.setText(text);
    }

    private void doDrawing(Graphics g) {
        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();

        for (int i = 0; i < BOARD_HEIGHT; ++i) {
            for (int j = 0; j < BOARD_WIDTH; ++j) {

                Shape.Tetrominoe shape = shapeAt(j, BOARD_HEIGHT - i - 1);
                if (shape != Shape.Tetrominoe.NoShape) {

                    drawSquare(g, j * squareWidth(),
                            boardTop + i * squareHeight(), shape);
                }
            }
        }

        if (curPiece.getShape() != Shape.Tetrominoe.NoShape) {
            for (int i = 0; i < 4; ++i) {

                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                drawSquare(g, x * squareWidth(),
                        boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(),
                        curPiece.getShape());
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public void dropDown() {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1)) {
                break;
            }
            --newY;
        }
        pieceDropped();
    }

    private void oneLineDown() {
        if (!tryMove(curPiece, curX, curY - 1)) {
            pieceDropped();
        }
    }

    private void clearBoard() {
        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; ++i) {
            board[i] = Shape.Tetrominoe.NoShape;
        }
    }

    private void pieceDropped() {

        for (int i = 0; i < 4; ++i) {

            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BOARD_WIDTH) + x] = curPiece.getShape();
        }
        removeFullLines();

        if (!isFallingFinished) {
            newPiece();
        }
    }

    private void newPiece() {
        curPiece.setRandomShape();
        initPiece();
    }

    public void newPiece(Shape.Tetrominoe shape) {
        curPiece.setShape(shape); // TODO add
//        curPiece.setShape(Shape.Tetrominoe.TShape);
        initPiece();
    }

    private void initPiece() {

        curX = BOARD_WIDTH / 2 + 1;
        curY = BOARD_HEIGHT - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(Shape.Tetrominoe.NoShape);
            timer.cancel();
            isStarted = false;
            setStatusText("Game over");
        }
    }

    public Shape getCurPiece() {
        return curPiece;
    }

    public boolean tryMove(Shape newPiece, int newX, int newY) {

        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);

            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT) {
                return false;
            }

            if (shapeAt(x, y) != Shape.Tetrominoe.NoShape) {
                return false;
            }
        }
//        System.out.println(newX + "," + newY);
        curPiece = newPiece;
        curX = newX;
        curY = newY;

        repaint();

        return true;
    }

    private void removeFullLines() {
        int numFullLines = 0;

        for (int i = BOARD_HEIGHT - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < BOARD_WIDTH; ++j) {
                if (shapeAt(j, i) == Shape.Tetrominoe.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BOARD_HEIGHT - 1; ++k) {
                    for (int j = 0; j < BOARD_WIDTH; ++j) {
                        board[(k * BOARD_WIDTH) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }

        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            setStatusText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(Shape.Tetrominoe.NoShape);
            repaint();
        }
    }

    private void drawSquare(Graphics g, int x, int y, Shape.Tetrominoe shape) {

        Color colors[] = {
                new Color(0, 0, 0), new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);

    }

    private void doGameCycle() {
        update();
        repaint();
    }

    private void update() {
        if (isPaused) {
            return;
        }
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }

    public int getLinesRemoved() {
        return numLinesRemoved;
    }

    public boolean getGameOver() {
        // TODO add
        if ("Game over".equals(statusbar.getText())) {
            return true;
        }
        return false;
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            // System.out.println("key pressed");

//            if (e.getKeyCode() == KeyEvent.VK_R) { // 重玩
//                start();
//            }

            if (!isStarted || curPiece.getShape() == Shape.Tetrominoe.NoShape) {
                return;
            }

            int keycode = e.getKeyCode();

            if (keycode == KeyEvent.VK_P) { // 暫停
                pause();
                return;
            }

            if (isPaused) {
                return;
            }

            switch (keycode) {

                case KeyEvent.VK_LEFT:
                    tryMove(curPiece, curX - 1, curY);
                    break;

                case KeyEvent.VK_RIGHT:
                    tryMove(curPiece, curX + 1, curY);
                    break;

                case KeyEvent.VK_DOWN:
                    tryMove(curPiece.rotateRight(), curX, curY);
                    break;

                case KeyEvent.VK_UP:
                    tryMove(curPiece.rotateLeft(), curX, curY);
                    break;

                case KeyEvent.VK_SPACE: // 直接到最底下
                    dropDown();
                    break;

                case KeyEvent.VK_D: // 加快下降的速度
                    oneLineDown();
                    break;
            }
        }
    }

    private class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            doGameCycle();
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package firstprogect.tictactio;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Student 2
 */
public class code extends JComponent {

    public static final int Field_EMPTY = 0;
    public static final int Field_X = 10;
    public static final int Field_0 = 200;
    int[][] field;
    boolean isXturn;

    public code(String col) {
        System.out.println(col);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new int[3][3];
        initGame();
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == mouseEvent.BUTTON1) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            int i = (int) ((float) x / getWidth() * 3);
            int j = (int) ((float) y / getHeight() * 3);
            if (field[i][j] == Field_EMPTY) {
                field[i][j] = isXturn ? Field_X : Field_0;
                isXturn = !isXturn;
                repaint();
                int res = checkState();
                if (res != 0) {
                    if (res == Field_0 * 3) {
                        JOptionPane.showMessageDialog(this, "00000000", "00000000", JOptionPane.INFORMATION_MESSAGE);
                    } else if (res == Field_X * 3) {
                        JOptionPane.showMessageDialog(this, "XXXXXXX", "XXXXXX", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "QQQQQQQ", "QQQQQQ", JOptionPane.INFORMATION_MESSAGE);
                    }
                    initGame();
                    repaint();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.clearRect(0, 0, getWidth(), getHeight());
        drawGrid(graphics);
        drawX0(graphics);
    }

    protected void drawGrid(Graphics graphics) {
        int w = getWidth();
        int h = getHeight();
        int dw = w / 3;
        int dh = h / 3;
        graphics.setColor(Color.RED);
        for (int i = 0; i < 3; i++) {
            graphics.drawLine(0, dh * i, w, dh * i);
            graphics.drawLine(dw * i, 0, dw * i, h);
        }
    }

    public void initGame() {
        for (int i = 0; i < 3; i++) {
            for (int q = 0; q < 3; q++) {
                field[i][q] = Field_EMPTY;
            }
        }
        isXturn = true;
    }

    void drawX(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawLine(x, y, x + dw, y + dh);
        graphics.drawLine(x, y + dh, x + dw, y);
    }

    void draw0(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawOval(x + 5 * dw / 100, y, dw * 9 / 10, dh);
    }

    void drawX0(Graphics graphics) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == Field_X) {
                    drawX(i, j, graphics);
                } else if (field[i][j] == Field_0) {
                    draw0(i, j, graphics);
                }

            }
        }
    }

    int checkState() {
        int diag = 0;
        int diag2 = 0;
        for (int i = 0; i < 3; i++) {
            diag += field[i][i];
            diag2 += field[i][2 - i];
        }
        if (diag == Field_0 * 3 || diag == Field_X * 3) {
            return diag;
        }
        if (diag2 == Field_0 * 3 || diag2 == Field_X * 3) {
            return diag;
        }
        int check_i, check_j;
        boolean hasEmpty = false;
        for (int i = 0; i < 3; i++) {
            check_i = 0;
            check_j = 0;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    hasEmpty = true;
                }
                check_i += field[i][j];
                check_j += field[j][i];
            }
            if (check_i == Field_0 * 3 || check_i == Field_X * 3) {
                return check_i;
            }
            if (check_j == Field_0 * 3 || check_j == Field_X * 3) {
                return check_j;
            }
        }
        if (hasEmpty) {
            return 0;
        } else {
            return -1;
        }
    }
}

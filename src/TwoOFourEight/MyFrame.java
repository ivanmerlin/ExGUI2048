package TwoOFourEight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class MyFrame extends JFrame {

    final int ROW = 4;
    final int SCREEN_WIDTH = ((int) Toolkit.getDefaultToolkit().getScreenSize().width);
    final int SCREEN_HEIGHT = ((int) Toolkit.getDefaultToolkit().getScreenSize().height);
    final int WIDTH = 600;//resizable
    final int HEIGHT = 600;
    Container container;
    NumLabel[][] group = new NumLabel[ROW][ROW];
    boolean[][] map = new boolean[ROW][ROW];
    int size=0;
    public MyFrame() {
        super();
        container = this.getContentPane();
        this.setLayout(null);
        this.setTitle("2048");
        this.setVisible(true);
        Insets insets = this.getInsets();
        this.setSize(WIDTH + insets.left + insets.right, HEIGHT + insets.top + insets.bottom);//16,38
        this.setLocation(SCREEN_WIDTH / 2 - WIDTH / 2, SCREEN_HEIGHT / 2 - HEIGHT / 2);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        for(int i=0;i<ROW;i++) {
            for (int j = 0; j < ROW; j++) {
                group[i][j] = new NumLabel();
                container.add(group[i][j]);
                placeNum(i,j);
            }
        }
        fillLabels();
        addListeners();
        repaint();

    }

    private void addListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moveToLeft();
                    fillLabels();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveToRight();
                    fillLabels();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    moveToUp();
                    fillLabels();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    moveToBottom();
                    fillLabels();
                }
                repaint();

            }
        });
    }

    private void moveToBottom() {
        for (int by = ROW-1; by>=0 ; by--) {
            for (int bx =  ROW-1; bx>=0; bx--) {
                if (!map[bx][by]) {//move
                    for (int tx = bx - 1; tx >= 0; tx--) {
                        if (map[tx][by] == true) {
                            swap(bx, by, tx, by);
                            break;
                        }
                    }
                }
                if (map[bx][by]) {
                    if(bx<ROW-1){
                        if(map[bx+1][by] && group[bx+1][by].getNum()==group[bx][by].getNum()){
                            group[bx+1][by].doubleSelf();
                            group[bx][by].blankSelf();
                            size--;
                            map[bx][by]=false;
                            bx++;
                        }
                    }
                }
            }
        }
    }

    private void moveToUp() {
        for (int by = 0; by < ROW; by++) {
            for (int bx = 0; bx < ROW; bx++) {
                if(!map[bx][by]) {
                    for (int tx = bx + 1; tx < ROW; tx++) {
                        if (map[tx][by]) {
                            swap(bx, by, tx, by);
                            break;
                        }
                    }
                }
                if (map[bx][by]) {
                    if(bx>0){
                        if(map[bx-1][by] && group[bx-1][by].getNum()==group[bx][by].getNum()){
                            group[bx-1][by].doubleSelf();
                            group[bx][by].blankSelf();
                            size--;
                            map[bx][by]=false;
                            bx--;
                        }
                    }
                }
            }
        }
    }

    private void moveToRight() {
        for (int bx = ROW-1; bx>=0 ; bx--) {
            for (int by =  ROW-1; by>=0; by--) {
                if (!map[bx][by] ) {
                    for (int ty = by - 1; ty >=0; ty--) {
                        if (map[bx][ty] == true) {
                            swap(bx, by, bx, ty);
                            break;
                        }
                    }
                }
                if (map[bx][by]) {
                    if(by<ROW-1){
                        if(map[bx][by+1] && group[bx][by+1].getNum()==group[bx][by].getNum()){
                            group[bx][by+1].doubleSelf();
                            group[bx][by].blankSelf();
                            size--;
                            map[bx][by]=false;
                            by++;
                        }
                    }
                }
            }
        }
    }

    private void moveToLeft() {
        for (int bx = 0; bx < ROW; bx++) {
            for (int by = 0; by < ROW; by++) {
                if (!map[bx][by] ) {
                    for (int ty = by + 1; ty < ROW; ty++) {
                        if (map[bx][ty] == true) {
                            swap(bx, by, bx, ty);
                            break;
                        }
                    }
                }
                if (map[bx][by]) {
                    if(by>0){
                        if(map[bx][by-1] && group[bx][by-1].getNum()==group[bx][by].getNum()){
                            group[bx][by-1].doubleSelf();
                            group[bx][by].blankSelf();
                            size--;
                            map[bx][by]=false;
                            by--;
                        }
                    }
                }
            }
        }
    }

    private void swap(int bx, int by, int tx, int ty) {
        boolean tmp = map[bx][by];
        map[bx][by] = map[tx][ty];
        map[tx][ty] = tmp;
        NumLabel t = group[tx][ty];
        NumLabel b = group[bx][by];
        group[bx][by] = t;
        group[tx][ty] = b;
        placeNum(bx,by);
        placeNum(tx, ty);
        this.setVisible(true);

    }

    private void fillLabels() {
        if (size + 2 <= ROW*ROW) {

            int[] p = generateRandom();
            int x = p[0];
            int y = p[1];
            while (map[x][y]) {
                p = generateRandom();
                x = p[0];
                y = p[1];
            }
            group[x][y].setNum(2);
            map[x][y] = true;
            placeNum(x, y);
            p = generateRandom();
            x = p[0];
            y = p[1];
            while (map[x][y]) {
                p = generateRandom();
                x = p[0];
                y = p[1];
            }
            group[x][y].setNum(2);
            map[x][y] = true;
            size=size+2;
        }
    }

    private void placeNum(int x, int y) {
        group[x][y].setLocation(WIDTH / ROW * y, HEIGHT / ROW * x);
        group[x][y].setSize(WIDTH / ROW, HEIGHT / ROW);
    }

    private int[] generateRandom() {
        Random random = new Random();
        int x = random.nextInt(4);
        int y = random.nextInt(4);
        while (map[x][y] == true) {
            x = random.nextInt(4);
            y = random.nextInt(4);
        }
        int[] result = {x, y};
        return result;
    }


    public static void main(String[] args) {
        MyFrame myframe = new MyFrame();
    }
}

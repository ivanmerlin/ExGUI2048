package TwoOFourEight;

import javax.swing.*;
import java.awt.*;


public class NumLabel extends JLabel {
    int val;
    Font font=new Font("宋体",Font.BOLD, 30);
    public NumLabel(){
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setFont(font);
        this.setText("");
        this.setOpaque(true);
        val=0;
        changeColor();
    }
    public NumLabel(int i){
        this.setText(Integer.toString(i));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setFont(font);
        val=i;
        this.setOpaque(true);
        changeColor();
    }

    public void setNum(int i){
        this.setText(Integer.toString(i));
        val=i;
        changeColor();
    }
    public void doubleSelf(){
        val=val<<1;
        this.setText(Integer.toString(val));
        changeColor();
    }

    private void changeColor() {
        Color c=Color.gray;
        switch(val){
            case 0:
                c=Color.white;
                break;
            case 2:
                c=Color.lightGray;
                break;
            case 4:
                c=Color.pink;
                break;
            case 8:
                c=new Color(79,245,217);
                break;
            case 16:
                c=Color.ORANGE;
                break;
            case 32:
                c=new Color(166,217,123);
                break;
            case 64:
                c=new Color(176,176,255);
                break;
            case 128:
                c=Color.GREEN;
                break;
            case 256:
                c=new Color(222,148,57);
                break;
            case 512:
                c=new Color(77,63,181);
                break;
            case 1024:
                c=Color.RED;
                break;
            default:
                c=Color.gray;
                break;
        }
        this.setBackground(c);
        repaint();
    }

    public void blankSelf(){
        this.setText("");
        val=0;
        changeColor();
    }
    public int getNum(){
        return val;
    }
}

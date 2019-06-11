package Utility;

import javax.swing.*;
import java.awt.*;

public class SetBackground extends JLabel{
    private Image image;

    public SetBackground(Image image){
        this.image = image;
    }

    @Override
    public void paintComponent(Graphics g){
        int x =getSize().width;
        int y =getSize().height;
        g.drawImage(image,0,0,x,y,this);
    }
}

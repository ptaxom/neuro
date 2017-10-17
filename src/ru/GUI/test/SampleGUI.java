package ru.GUI.test;

import ru.data.faultData;
import ru.neuro.io.FaultReader;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import static java.lang.Math.*;

/**
 * Created by 34 on 02.10.2017.
 */


public class SampleGUI extends JFrame{

    private static String name;

    public SampleGUI(String name){
        super("Изменение ошибки нейросети: "+name);
        this.name = name;
        this.setSize(520, 540);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new DrawComponent());
    }

    static class DrawComponent extends JComponent{
        public void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D)g;


            g2.draw(new Line2D.Double(20,20,20,480));
            g2.draw(new Line2D.Double(20, 480 ,500, 480));

            double x0 = 30, y0 = 480;

            faultData data = FaultReader.Read(name);
            int dx = 470, dy = 460;
            double stepdx = dx/(data.getNum()-1);
            double max = 300, min = -100, stepdy;
            for (int i = 0; i<data.getNum(); i++)
            {
                max = (max<data.getData(i)) ? data.getData(i) : max ;
                min = (max<data.getData(i)) ? data.getData(i) : min ;
            }
            stepdy = dy ;
            double[] x = new double[data.getNum()];
            double[] y = new double[data.getNum()];
            for(int i = 0; i< data.getNum(); i++)
            {
                x[i] = x0+i*stepdx;
                y[i] = y0-data.getData(i)*stepdy;
            }

            for(int i = 0; i<data.getNum()-1;i++)
                g2.draw(new Line2D.Double(x[i],y[i],x[i+1],y[i+1]));

        }

        public double f(double x){
            return sin(x)*log(abs(x));
        }

        public void Circle(Graphics2D g, double x, double y, double r){
            Ellipse2D circle = new Ellipse2D.Double();
            circle.setFrameFromCenter(x,y,x+r,y+r);
            g.draw(circle);
        }

    }


}

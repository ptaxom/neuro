package ru.neuro.Main;


import ru.GUI.test.SampleGUI;
import ru.neuro.Net.Net;
import ru.neuro.io.*;

import javax.swing.*;


/**
 * Created by 34 on 30.09.2017.
 */

    //Нейросеть уводит на 1
    //Пересмотреть МОР
    //Пофиксить веса
    //пофиксить моменты

public class Main {

    public static String name = "XOR";
    public static Net net;

    public static void main(String[] args) {
       test();
        JFrame frame = new SampleGUI(name);
    }

    public static void test(){
        Net net = new Net(name);
      for (int i = 0; i<70; i++)
      {
          generate();
          net.Activate("io.txt");
      }
    }

    public static void generate(){
        int x = (int)(Math.random()*10) % 2;
        int y = (int)(Math.random()*10) % 2;
        WriteWeight.WriteText(x+" "+y+"#"+(x ^ y),name, "io.txt");
    }

    public static void test2(){
        int[] b = {2,7, 14, 6,1};
        net = new Net(name, 5,b,0.3,0.7);
    }

}

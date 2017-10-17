package ru.neuro.io;

import ru.neuro.Net.Net;

import java.io.IOException;
import java.io.*;
/**
 * Created by 34 on 01.10.2017.
 */
public class NetReader {

    public static void Read(Net net) {
        String path = "res//"+net.getName()+"/cfg.txt";
        try{
            File file = new File(path);
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            int n = Integer.parseInt(in.readLine());
            net.setLayers(n);
            net.InitLayer(n);
            String s = in.readLine();
            for (int i = 0; i < n-1; i++)
            {
                String bufS;
                int k = (s.indexOf(" ") == -1) ? 0 : s.indexOf(" ");
                bufS = s.substring(0, k);
                net.setLayer(i, Integer.parseInt(bufS));
                String newS = "";
                for (int l = s.indexOf(" ")+1;l<s.length(); l++)
                    newS += s.charAt(l);
                s = newS;
            }
            net.setLayer(n-1, Integer.parseInt(s));
            net.setAlfa(Double.parseDouble(in.readLine()));
            net.setEps(Double.parseDouble(in.readLine()));
        } catch (IOException e){}
    }
}

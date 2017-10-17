package ru.neuro.io;

import ru.neuro.Net.NLayer;

import java.io.IOException;
import java.io.*;

/**
 * Created by 34 on 30.09.2017.
 */
public class LayerReader {
    public static void Read(NLayer nLayer, boolean d) {
        String path;
        if (d == true)
            path = "res//"+nLayer.getName()+"/delta/"+nLayer.getNumber()+".txt";
            else path = "res//"+nLayer.getName()+"/"+nLayer.getNumber()+".txt";
        try{
            File file = new File(path);
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            for (int i = 0; i < nLayer.getNeyrons(); i++)
            {
                String s = in.readLine();
                int Neyrons = (nLayer.getOwnNet()).getLayer(nLayer.getNumber()+1);
                for (int j = 0; j <  Neyrons- 1; j++)
                {
                    String bufS;
                    int k = (s.indexOf(" ") == -1) ? 0 : s.indexOf(" ");
                    bufS = s.substring(0, k);
                    if (d == true)
                        nLayer.setdWeight(i, j, Double.parseDouble(bufS));
                        else nLayer.setWeight(i, j, Double.parseDouble(bufS));
                    String newS = "";
                    for (int l = s.indexOf(" ")+1;l<s.length(); l++)
                        newS += s.charAt(l);
                    s = newS;
                }
                if (d == true)
                    nLayer.setdWeight(i, Neyrons-1, Double.parseDouble(s));
                    else nLayer.setWeight(i, Neyrons-1, Double.parseDouble(s));
            }
        } catch (IOException e){}
    }
}

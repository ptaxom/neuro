package ru.neuro.io;

import ru.neuro.Net.IOData;
import ru.neuro.Net.Net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 34 on 01.10.2017.
 */
public class IOReader {
    public static void Read(IOData data, String path, String name){
        try{
            File file = new File(path+"/"+name);
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            //-------
            String s = in.readLine();
            for (int j = 0; j <  data.getInputN()-1; j++)
            {
                String bufS;
                int k = (s.indexOf(" ") == -1) ? 0 : s.indexOf(" ");
                bufS = s.substring(0, k);
                data.setInput(j, Double.parseDouble(bufS));
                String newS = "";
                for (int l = s.indexOf(" ")+1;l<s.length(); l++)
                    newS += s.charAt(l);
                s = newS;
            }
            data.setInput(data.getInputN()-1, Double.parseDouble(s));
            //-------
            s = in.readLine();
            for (int j = 0; j <  data.getOutputN()-1; j++)
            {
                String bufS;
                int k = (s.indexOf(" ") == -1) ? 0 : s.indexOf(" ");
                bufS = s.substring(0, k);
                data.setOutput(j, Double.parseDouble(bufS));
                String newS = "";
                for (int l = s.indexOf(" ")+1;l<s.length(); l++)
                    newS += s.charAt(l);
                s = newS;
            }
            data.setOutput(data.getOutputN()-1, Double.parseDouble(s));
        } catch (IOException e){}
    }
}

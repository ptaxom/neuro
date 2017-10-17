package ru.neuro.io;

import ru.data.faultData;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by 34 on 02.10.2017.
 */
public class FaultReader {

    public static faultData Read(String name){
        String path = "res//"+name+"/eps.txt";
        int cnt = 0;
        ArrayList<String> list = new ArrayList<>();
        try{
            File file = new File(path);
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            String s;
            while((s = in.readLine()) != null) {
                list.add(s);
                cnt++;
            }
        }catch (FileNotFoundException e){}
         catch (IOException e){}
         double[] eps = new double[cnt];
         for (int i = 0; i<cnt;i++)
             eps[i] = Double.parseDouble(list.get(i));
         return new faultData(cnt, eps);
    }

}

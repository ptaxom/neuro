package ru.neuro.io;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by 34 on 01.10.2017.
 */
public class ErrorWriter {
    public static void Rewrite(String path, String name, double eps){
        try{
            File folder = new File(path);
            if (!folder.exists())
                folder.mkdir();
            File output = new File(path+"/"+name);
            ArrayList<String> list = new ArrayList<>();
            if (!output.exists())
                output.createNewFile();
            else{
                BufferedReader in = new BufferedReader(new FileReader(output.getAbsoluteFile()));
                String s;
                while((s = in.readLine()) != null)
                    list.add(s);
            }
            list.add(Double.toString(eps));
            PrintWriter out = new PrintWriter(output.getAbsoluteFile());
            try {
                for(int i  = 0; i< list.size(); i++)
                    out.println(list.get(i));
            } finally {
                out.close();
            }
        }
        catch (IOException e){}
    }
}

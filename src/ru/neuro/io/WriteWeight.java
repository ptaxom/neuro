package ru.neuro.io;

import java.io.*;

/**
 * Created by 34 on 30.09.2017.
 */
public class WriteWeight {


    public static void Write(double[][] toWrite, String dir, String name,int n, int m){
        try{
            File folder = new File("res//"+dir);
            if (!folder.exists())
                folder.mkdir();
            File output = new File("res//"+dir+"/"+name);
            if (!output.exists())
                output.createNewFile();
            PrintWriter out = new PrintWriter(output.getAbsoluteFile());
            try {
                for (int i = 0; i<n; i++){
                    for (int j = 0; j < m;j++)
                        if (j == m-1)
                            out.println(toWrite[i][j]);
                        else out.print(toWrite[i][j]+" ");
                }
            } finally {
                out.close();
            }
        }
        catch (IOException e){}
    }

    public static void WriteText(String toWrite, String dir, String name){
        try{
            File folder = new File("res//"+dir);
            if (!folder.exists())
                folder.mkdir();
            File output = new File("res//"+dir+"/"+name);
            if (!output.exists())
                output.createNewFile();
            PrintWriter out = new PrintWriter(output.getAbsoluteFile());
            try {
                for (int i = 0; i<toWrite.length(); i++)
                    if (toWrite.charAt(i) == '#')
                        out.println();
                else out.print(toWrite.charAt(i));

            } finally {
                out.close();
            }
        }
        catch (IOException e){}
    }

}

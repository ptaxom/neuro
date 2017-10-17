package ru.neuro.Net;

import ru.neuro.io.IOReader;

/**
 * Created by 34 on 01.10.2017.
 */
public class IOData {
   private double input[];
   private double output[];
   private int inputN;
   private int outputN;

   public IOData(Net net, String path, String name){
        inputN = net.getLayer(0);
        outputN = net.getLayer(net.getLayers()-1);
        input = new double[inputN];
        output = new double[outputN];
        IOReader.Read(this, path, name);
   }

   public int getInputN(){
       return inputN;
   }

   public int getOutputN(){
       return outputN;
   }

   public void setInput(int num, double data){
       input[num] = data;
   }

   public void setOutput(int num, double data){
       output[num] = data;
   }

   public double getInput(int num){ return  input[num]; }

   public double getOutput(int num) { return  output[num]; }
}

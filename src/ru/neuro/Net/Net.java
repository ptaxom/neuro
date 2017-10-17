package ru.neuro.Net;

import ru.neuro.io.ErrorWriter;
import ru.neuro.io.NetReader;
import ru.neuro.io.WriteWeight;

import java.util.ArrayList;

/**
 * Created by 34 on 30.09.2017.
 */
public class Net {
    int Layers;
    int[] Layer;
    String name;
    NLayer[] nLayer;
    private double alfa;
    private double eps;

    public double getAlfa(){
        return alfa;
    }

    public double getEps(){
        return eps;
    }

    public void setAlfa(double a) {this.alfa = a; }

    public void setEps(double e) {this.eps = e; }

    public String getName() {return  name;}

    public int getLayer(int num) {return Layer[num]; }

    public void setLayers(int n) {this.Layers = n; }

    public void setLayer(int num, int n) {
        this.Layer[num] = n;
    }

    public NLayer getnLayer(int num){return nLayer[num]; }

    public int getLayers() { return  Layers; }

    public Net(String name,int k, int[] args, double a, double e){  //Создание нейросети с нуля
        alfa = a;
        eps = e;
        this.Layer = new int[k];
        this.name = name;
        for (int i = 0; i < k; i++)
            Layer[i] = args[i];
        for (int i = 0; i< k-1; i++) {
            NLayer l = new NLayer(i, this);
        }
        String s = "";
        for (int i = 0; i<k-1;i++)
            s+=Layer[i]+" ";
        s+=Layer[k-1];
        s = k +"#"+s+"#"+a+"#"+e;
        WriteWeight.WriteText(s, name, "cfg.txt");
    }

    public Net(String name)
    {
        this.name = name;
        NetReader.Read(this);
        nLayer = new NLayer[Layers];
        for (int i = 0; i<Layers; i++)
            nLayer[i] = new NLayer(i, this, i);
        for (int i = 0; i<Layers; i++)
            Layer[i] = nLayer[i].getNeyrons();
    }

    public void InitLayer(int num){
        Layer = new int[num];
    }


    public void PrintNet(){
        System.out.println("Название нейросети: "+name);
        System.out.println("Число нейронных слоев в нейросети: "+Layers);
        System.out.println("Карта нейронных слоев:");
        for (int i = 0; i<Layers; i++)
            System.out.print(Layer[i]+" ");
        System.out.println("\nМомент обучаемости: "+alfa);
        System.out.println("Момент градиента обучаемости: "+eps);
        for(int i = 0; i<Layers; i++)
            nLayer[i].PrintLayer();
    }




    public static double f(double x){
        return 1/(Math.exp(-x)+1);
    }
    public static double fh(double x) {return
            Math.exp(-x)/(1+Math.exp(-x))/(1+Math.exp(-x));
    }


    public void Activate(String fileName){
    IOData data = new IOData(this, "res//"+name, fileName);
    nLayer[0].InitNeyron(data, 0);
    for (int i = 1; i < Layers; i++)
        nLayer[i].InitNeyron(data, 1);
    double fault = nLayer[Layers-1].getError(data);
    ErrorWriter.Rewrite("res//"+name, "eps.txt", fault);
    double[] out = new double[Layer[Layers-1]];
    String s = "";
    for (int i = 0; i<Layer[Layers-1]; i++){
        double k = (nLayer[Layers-1].getNeyron(i)).getValue();
        (nLayer[Layers-1].getNeyron(i)).setDelta((data.getOutput(i)-k)*fh(k));
        s+=Double.toString(k)+" ";
    }
        if (fault>0.0001) {
            WriteWeight.WriteText(s, name, "output.txt");
            Teach();
        }
    }


    public void Teach(){
        for(int i=Layers-2; i>=0; i--){
            nLayer[i].calcDelta();
        }
        for(int i=Layers-2; i>=0; i--){
            nLayer[i].calcDW();
        }

    }

}

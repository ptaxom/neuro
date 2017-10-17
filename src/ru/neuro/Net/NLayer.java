package ru.neuro.Net;

import ru.neuro.io.LayerReader;
import ru.neuro.io.WriteWeight;

/**
 * Created by 34 on 30.09.2017.
 */
public class NLayer {
    private int Number;
    private int Neyrons;
    private String Name;
    private double[][] Weight;
    private double[][] dWeight;
    private Neyron[] Neyron;
    private static  Net ownNet;

    public Net getOwnNet(){
        return  ownNet;
    }

    public void setWeight(int x, int y, double weight){
        Weight[x][y] = weight;
    }

    public void setdWeight(int x, int y, double dweight){
        dWeight[x][y] = dweight;
    }

    public double getWeight(int x, int y){ return Weight[x][y]; }

    public double getdWeight(int x, int y){ return dWeight[x][y]; }

    public String getName(){
        return  Name;
    }

    public int getNumber(){
        return Number;
    }

    public int getNeyrons() {
        return Neyrons;
    }



    public NLayer(int num, Net net){
        this.Number = num;
        this.ownNet = net;
        Name = net.name;
        Neyrons = net.Layer[num];
        int nextNeyrons = net.Layer[num+1];
        double[][] k = new double[Neyrons][nextNeyrons];
        double[][] k2 = new double[Neyrons][nextNeyrons];
        for (int i = 0; i < Neyrons; i++)
            for (int j = 0; j < nextNeyrons; j++)
            {
                double z = Math.random();
                if (Math.abs(z)>4)
                    z = 1 / z;
                k[i][j] = z;
                k2[i][j] = 0;
            }
        WriteWeight.Write(k, Name, Number+".txt", Neyrons, nextNeyrons);
        WriteWeight.Write(k2, Name+"/delta/", Number+".txt", Neyrons, nextNeyrons);
    }

    public NLayer(int num, Net net, int z){
        this.Number = num;
        this.ownNet = net;
        Name = net.name;
        Neyrons = net.Layer[num];
        if(z!=net.Layers-1) {
        int nextNeyrons = net.Layer[num+1];
            Weight = new double[Neyrons][nextNeyrons];
            dWeight = new double[Neyrons][nextNeyrons];
            LayerReader.Read(this, false);
            LayerReader.Read(this, true);
        }

    }

    public void PrintLayer(){
        System.out.println("-------------------------");
        System.out.println("Номер нейронного слоя: "+Number);
        System.out.println("Число нейронов в этом слое: "+Neyrons);
        if (Number != ownNet.Layers-1) {
            System.out.println("----------WEIGHT---------");
            for (int i = 0; i < Neyrons; i++) {
                for (int j = 0; j < ownNet.Layer[Number + 1]; j++)
                    System.out.print(Weight[i][j] + " ");
                System.out.println();
            }
            System.out.println("----------DDELTA---------");
            for (int i = 0; i < Neyrons; i++) {
                for (int j = 0; j < ownNet.Layer[Number + 1]; j++)
                    System.out.print(dWeight[i][j] + " ");
                System.out.println();
            }
        }
    }

    public void InitNeyron(IOData data, int type){
        Neyron = new Neyron[Neyrons];
        for (int i = 0; i<Neyrons; i++) {
            if (type == 0)
                Neyron[i] = new Neyron(this, data.getInput(i), i);
            if (type == 1)
                Neyron[i] = new Neyron(this, i);

        }
    }

    public double getError(IOData data){
        double sum = 0;
        for(int i = 0; i<Neyrons; i++)
            sum+=(Neyron[i].getValue()-data.getOutput(i))*(Neyron[i].getValue()-data.getOutput(i));
        return sum/Neyrons;
    }

    public Neyron getNeyron(int num) {return  Neyron[num]; }

    public void calcDelta(){
        for (int i = 0; i<Neyrons; i++)
            Neyron[i].calcDelta(this);
    }

    public void calcDW(){
        for (int i = 0; i<Neyrons; i++)
            Neyron[i].calcDW(this);
        int nextNeyrons = ownNet.getLayer(Number+1);
        WriteWeight.Write(Weight, Name, Number+".txt", Neyrons, nextNeyrons);
        WriteWeight.Write(dWeight, Name+"/delta/", Number+".txt", Neyrons, nextNeyrons);

    }
}

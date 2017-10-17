package ru.neuro.Net;

/**
 * Created by 34 on 30.09.2017.
 */
public class Neyron {
    private double value;
    private int pos;
    private double delta;
    private double fault;
    private int nLayer;
    private int nNeyron;
    private NLayer ownLayer;

    public Neyron(NLayer layer, int pos){
        double sum = 0;
        this.pos = pos;
        this.ownLayer = layer;
        int prevNeyrons = (layer.getOwnNet()).getLayer(layer.getNumber()-1);
        NLayer prevLayer = (layer.getOwnNet()).getnLayer(layer.getNumber()-1);
        for (int i = 0; i < prevNeyrons; i++)
            sum+=(prevLayer.getNeyron(i)).getValue()*prevLayer.getWeight(i, pos);
        this.value = Net.f(sum);
    }

    public Neyron(NLayer layer, double value, int pos){
        ownLayer = layer;
        this.value = value;
        this.pos = pos;

    }


    public double getValue(){
        return value;
    }

    public void setDelta(double delta) { this.delta = delta;}

    public double getDelta() {return delta; }

    public void calcDelta(NLayer nLayer){
        NLayer next = (nLayer.getOwnNet()).getnLayer(nLayer.getNumber()+1);
        double sum = 0;
        for (int i = 0; i < next.getNeyrons(); i++)
            sum+=next.getNeyron(i).getDelta()*nLayer.getWeight(pos, i);
        delta = Net.fh(value)*sum;
    }



    public void calcDW(NLayer nLayer){
        NLayer next = (nLayer.getOwnNet()).getnLayer(nLayer.getNumber()+1);
        for (int i = 0; i < next.getNeyrons(); i++)
        {
            double grad = value * next.getNeyron(i).getDelta();
            double dw = nLayer.getOwnNet().getEps()*grad+nLayer.getOwnNet().getAlfa()*nLayer.getdWeight(pos,i);
            nLayer.setWeight(pos, i, nLayer.getWeight(pos,i)+dw);
            nLayer.setdWeight(pos, i, dw);
        }

    }


}

package ru.data;

/**
 * Created by 34 on 02.10.2017.
 */
public class faultData {
    private int num;
    private double[] data;

    public faultData(int num, double[] data){
        this.num = num;
        this.data = new double[num];
        this.data = data;
    }

    public int getNum() {return num;}

    public double getData(int num) {return data[num];}

}

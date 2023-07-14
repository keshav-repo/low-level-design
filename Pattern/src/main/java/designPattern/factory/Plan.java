package designPattern.factory;

public abstract class Plan {

    protected double rate;

    public abstract double getRate();

    public double calculateBill(int units){
        return getRate()*units;
    }

}

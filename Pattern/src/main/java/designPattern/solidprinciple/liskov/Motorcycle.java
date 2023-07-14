package designPattern.solidprinciple.liskov;

public class Motorcycle implements Bike{
    boolean isEnginerOn;
    int speed;
    @Override
    public void turnOnEngine() {
        isEnginerOn = true;
    }

    @Override
    public void accelerate() {
        speed = speed+10;
    }
}

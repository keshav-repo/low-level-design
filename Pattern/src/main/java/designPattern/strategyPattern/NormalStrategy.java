package designPattern.strategyPattern;

public class NormalStrategy implements DrivingStrategy{
    @Override
    public void drive() {
        System.out.println("normal strategy capacity");
    }
}

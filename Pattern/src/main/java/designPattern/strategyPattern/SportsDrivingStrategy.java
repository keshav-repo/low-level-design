package designPattern.strategyPattern;

public class SportsDrivingStrategy implements DrivingStrategy{
    @Override
    public void drive() {
        System.out.println("sports driving capability");
    }
}

package designPattern.factory;

public class FactoryDesignPattern {
    public static void main(String[] args){
        Plan plan = PlanFactory.getPlan("DOMESTIC");// COMMERCIAL

        double rate = plan.getRate();
        double price = plan.calculateBill(10);

        System.out.println("Rate: "+rate+ " price: "+price);
    }
}

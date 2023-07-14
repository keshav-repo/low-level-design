package designPattern.factory;

public class PlanFactory {
    public static Plan getPlan(String planType){
        switch(planType){
            case "DOMESTIC": return new DomesticPlan();
            case "COMMERCIAL": return new Commercial();
            case "INSTITUTIONAL": return new Institutional();

            default: new Commercial();
        }
        return null;
    }
}

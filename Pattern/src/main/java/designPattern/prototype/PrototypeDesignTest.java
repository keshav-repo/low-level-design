package designPattern.prototype;

public class PrototypeDesignTest {

    public static void main(String[] args) throws CloneNotSupportedException{
        Employees employees = new Employees();
        employees.loadData();

        Employees e2 = employees.clone();

        employees.getEmployeeName().add("Mohan");
        System.out.println( employees.toString());

        System.out.println( e2.toString() );
    }

}

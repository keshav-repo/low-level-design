package designPattern.composite;

/**
 * Created by Gebruiker on 5/3/2018.
 */
public class CompositeDemo {

    public static void main(String args[]) {
        Department salesDepartment = new SalesDepartment(1, "Sales department");
        Department financialDepartment = new FinancialDepartment(2, "Financial department");

        HeadDepartment headDepartment = new HeadDepartment(3, "Head department");

        headDepartment.addDepartMent(salesDepartment);
        headDepartment.addDepartMent(financialDepartment);

//        HeadDepartment anotherHeadDept = new HeadDepartment(4, "Top head");
//        anotherHeadDept.addDepartMent(headDepartment);

        headDepartment.printDepartmentName();

        // anotherHeadDept.printDepartmentName();
    }
}

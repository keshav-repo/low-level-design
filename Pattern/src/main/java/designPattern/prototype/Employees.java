package designPattern.prototype;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class Employees implements Cloneable{

    private List<String> employeeName;

    public Employees() {
        employeeName = new ArrayList<String>();
    }
    public Employees(List<String> employeeName) {
        this.employeeName = employeeName;
    }

    public void loadData(){
        employeeName.addAll(Arrays.asList("Ram", "Shyam", "Lakshman", "Keshav"));
    }

    public List<String> getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(List<String> employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public Employees clone() throws CloneNotSupportedException{
        List<String> temp = new ArrayList<String>();
       // temp.addAll(this.employeeName);
        this.employeeName.forEach(e->temp.add(e));
        return new Employees(temp);
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employeeName=" + employeeName +
                '}';
    }
}

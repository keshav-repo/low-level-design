package designPattern.builder;

public class Phone {
    private String os;
    private int ram;

    public void Phone(){
    }

    public Phone(String os, int ram){
        this.os = os;
        this.ram = ram;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "os='" + os + '\'' +
                ", ram=" + ram +
                '}';
    }
}

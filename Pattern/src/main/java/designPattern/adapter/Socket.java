package designPattern.adapter;

public class Socket {

    public Volt getVolts(){
        return new Volt(120);
    }
}

package designPattern.adapter;

public class AdapterPatternTest {
    public static void main(String[] args){
        SocketAdapter socketAdapter = new SocketClassAdapterImpl();

        Volt v3 = socketAdapter.get3Volt();
        Volt v12 = socketAdapter.get12Volt();

        System.out.println("3 volt is: "+v3.getVolt());
        System.out.println("12 volt is: "+v12.getVolt());
    }
}

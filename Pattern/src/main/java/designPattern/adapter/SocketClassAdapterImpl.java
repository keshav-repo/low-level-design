package designPattern.adapter;

public class SocketClassAdapterImpl extends Socket implements SocketAdapter{

    @Override
    public Volt get120Volt() {
        return getVolts();
    }

    @Override
    public Volt get12Volt() {
        Volt volt = getVolts();
        return convertVolt(volt,10);
    }

    @Override
    public Volt get3Volt() {
        Volt volt = getVolts();
        return convertVolt(volt,40);
    }

    private Volt convertVolt(Volt v, int factor){
        return new Volt(v.getVolt()/factor);
    }
}

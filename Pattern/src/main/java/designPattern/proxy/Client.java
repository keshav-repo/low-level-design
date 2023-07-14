package designPattern.proxy;

public class Client {
    public static void main(String[] args) {
        ProxyInternet proxyInternet = new ProxyInternet();

        try{
            proxyInternet.connectTo("abc.com");
        }catch (Exception exception){
            System.out.println(exception.getLocalizedMessage());
        }
    }
}

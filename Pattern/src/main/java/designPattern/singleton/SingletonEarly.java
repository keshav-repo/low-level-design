package designPattern.singleton;

public class SingletonEarly{
    private static final SingletonEarly SINGLETON_EARLY = new SingletonEarly();

    private SingletonEarly(){}

    public static SingletonEarly getInstance(){
        return SINGLETON_EARLY;
    }
}

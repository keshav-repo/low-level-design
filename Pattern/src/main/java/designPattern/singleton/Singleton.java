package designPattern.singleton;

// lazy instantiation
public class Singleton {

    private static Singleton singleton;

    private Singleton(){}

    public static Singleton getSingletonInstance(){
        if(singleton==null){
            synchronized(singleton){
                if(singleton==null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}

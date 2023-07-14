package designPattern.obervablePattern.observer;


import designPattern.obervablePattern.observable.StockObservable;

public class MobileAlertObserverImpl implements NotificationAlertObserver{
    String username;
    StockObservable observable;
    public MobileAlertObserverImpl(String username, StockObservable observable) {
        this.username = username;
        this.observable = observable;
    }
    @Override
    public void update() {
        sendMsgOnMobile(username, "product in stock!, hurry up");
    }
    public void sendMsgOnMobile(String username, String message){
        System.out.println("message sent to "+username);
    }
}

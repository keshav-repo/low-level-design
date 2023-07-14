package designPattern.obervablePattern.observer;


import designPattern.obervablePattern.observable.StockObservable;

public class EmailAlertObserverImpl implements NotificationAlertObserver{
    String email;
    StockObservable observable;
    public EmailAlertObserverImpl(String email, StockObservable observable) {
        this.email = email;
        this.observable = observable;
    }
    @Override
    public void update() {
        sendEmail(email, "product in stock!, hurry up");
    }
    public void sendEmail(String username, String message){
        System.out.println("email sent to "+username);
    }

}

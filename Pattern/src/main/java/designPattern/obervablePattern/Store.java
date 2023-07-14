package designPattern.obervablePattern;


import designPattern.obervablePattern.observable.IphoneObservableImpl;
import designPattern.obervablePattern.observable.StockObservable;
import designPattern.obervablePattern.observer.EmailAlertObserverImpl;
import designPattern.obervablePattern.observer.MobileAlertObserverImpl;
import designPattern.obervablePattern.observer.NotificationAlertObserver;

public class Store {

    public static void main(String[] args) {
        StockObservable iphoneObservable = new IphoneObservableImpl();

        NotificationAlertObserver observer1 = new EmailAlertObserverImpl("xyz1@gmail.com", iphoneObservable );
        NotificationAlertObserver observer2 = new EmailAlertObserverImpl("xyz1@gmail.com", iphoneObservable );
        NotificationAlertObserver observer3 = new MobileAlertObserverImpl("xyz1User", iphoneObservable );

        iphoneObservable.add(observer1);
        iphoneObservable.add(observer2);
        iphoneObservable.add(observer3);

        iphoneObservable.setStockCount(10);

    }
}

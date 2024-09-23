package designPattern.observerPattern2;

import java.util.ArrayList;
import java.util.List;

// Subject (Observable)
interface Subject {
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;
    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(temperature));
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

// Observer
interface Observer {
    void update(int temperature);
}

// Concrete Observer
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(int temperature) {
        System.out.println(name + ": The temperature is " + temperature + " degrees Celsius.");
    }
}

public class WeatherObserver {
    public static void main(String[] args) {
        // Create the subject
        WeatherStation weatherStation = new WeatherStation();

        // Create observers
        Observer user1 = new User("User 1");
        Observer user2 = new User("User 2");

        // Register observers with the subject
        weatherStation.registerObserver(user1);
        weatherStation.registerObserver(user2);

        // Simulate temperature change
        weatherStation.setTemperature(25);
        weatherStation.setTemperature(30);

        // Unregister an observer
        weatherStation.unregisterObserver(user1);

        // Simulate temperature change
        weatherStation.setTemperature(27);
    }
}

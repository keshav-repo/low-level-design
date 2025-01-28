package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable{
    String name;
    public Task(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("Executing task "+name);
    }
}
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable task1 = new Task("task1");
        Runnable task2 = new Task("task2");

        executorService.submit(task1);
        executorService.submit(task2);

        executorService.shutdown();
    }
}

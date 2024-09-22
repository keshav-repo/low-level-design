package com.lession2;

public class DogDoor {
    private boolean open;
    public DogDoor() {
        this.open = false;
    }
    public void open(){
        System.out.println("The door opens");
        this.open = true;
    }
    public void close(){
        System.out.println("The door closes");
        this.open = false;
    }
    public boolean isOpen(){
        return open;
    }
}

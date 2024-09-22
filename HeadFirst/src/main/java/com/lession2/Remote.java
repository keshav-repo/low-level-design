package com.lession2;

public class Remote {
    private DogDoor dogDoor;

    public Remote(DogDoor dogDoor) {
        this.dogDoor = dogDoor;
    }

    public void pressButton(){
        System.out.println("Pressing the button ... ");
        if(dogDoor.isOpen()){
            dogDoor.close();
        }else {
            dogDoor.open();
        }
    }
}

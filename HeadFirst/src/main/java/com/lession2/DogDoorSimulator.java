package com.lession2;

public class DogDoorSimulator {
    public static void main(String[] args) {
        DogDoor dogDoor = new DogDoor();
        Remote remote = new Remote(dogDoor);

        System.out.println("fido barks to go outside");
        remote.pressButton();

        System.out.println("fido had gone out");
        remote.pressButton();

        System.out.println("fido has done all his work");
        remote.pressButton();

        System.out.println("fido back inside");
        remote.pressButton();
    }
}

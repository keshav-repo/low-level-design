package designPattern.solidprinciple.dependencyInv;

public class Mackbook {
    private final WiredKeyboard wiredKeyboard; //

    public Mackbook(WiredKeyboard wiredKeyboard) {
        this.wiredKeyboard = wiredKeyboard;
    }
}

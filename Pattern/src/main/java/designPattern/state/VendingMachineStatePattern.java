package designPattern.state;

// VendingMachine interface
interface VendingMachineState {
    void insertCoin();
    void selectProduct();
    void dispenseProduct();
}

// Concrete state classes
class NoCoinState implements VendingMachineState {
    public void insertCoin() {
        System.out.println("Coin inserted");
    }

    public void selectProduct() {
        System.out.println("Please insert a coin first");
    }

    public void dispenseProduct() {
        System.out.println("Please insert a coin first");
    }
}

class HasCoinState implements VendingMachineState {
    public void insertCoin() {
        System.out.println("Coin already inserted");
    }

    public void selectProduct() {
        System.out.println("Product selected");
    }

    public void dispenseProduct() {
        System.out.println("Product dispensed");
    }
}

// Context class (VendingMachine)
class VendingMachine {
    private VendingMachineState currentState;

    public VendingMachine() {
        currentState = new NoCoinState();
    }

    public void insertCoin() {
        currentState.insertCoin();
        if (currentState instanceof NoCoinState) {
            currentState = new HasCoinState();
        }
    }

    public void selectProduct() {
        currentState.selectProduct();
        if (currentState instanceof HasCoinState) {
            currentState = new NoCoinState();
        }
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }
}

public class VendingMachineStatePattern {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.selectProduct(); // Output: Please insert a coin first

        vendingMachine.insertCoin(); // Output: Coin inserted
        vendingMachine.selectProduct(); // Output: Product selected
        vendingMachine.dispenseProduct(); // Output: Product dispensed

        vendingMachine.selectProduct(); // Output: Please insert a coin first
    }
}

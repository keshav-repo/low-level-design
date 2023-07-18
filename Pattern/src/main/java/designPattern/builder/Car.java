package designPattern.builder;

public class Car {
    private String brand;
    private String model;
    private int year;
    private boolean isElectric;
    // ...

    private Car(String brand, String model, int year, boolean isElectric) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.isElectric = isElectric;
        // ...
    }

    // Getters...

    public static class Builder {
        private String brand;
        private String model;
        private int year;
        private boolean isElectric;
        // ...

        public Builder(String brand, String model) {
            this.brand = brand;
            this.model = model;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder isElectric(boolean isElectric) {
            this.isElectric = isElectric;
            return this;
        }

        // Other builder methods for additional properties...

        public Car build() {
            // Complex validation or instantiation logic can be performed here
            if (year < 1886) {
                throw new IllegalArgumentException("Invalid year");
            }

            if (brand == null || model == null) {
                throw new IllegalStateException("Brand and model are mandatory");
            }

            return new Car(brand, model, year, isElectric);
        }
    }

    public static void main(String[] args) {

        Car car = new Car.Builder("Tesla", "Model S")
                .year(2022)
                .isElectric(true)
                .build();

//        Car car2 = new Car.Builder("Tata", "Model 9") // Exception in thread "main" java.lang.IllegalArgumentException: Invalid year
//                .year(1880)
//                .isElectric(true)
//                .build();

        Car car3 = new Car.Builder("Maruti", null) // Exception in thread "main" java.lang.IllegalArgumentException: Invalid year
                .year(1880)
                .isElectric(true)
                .build();

    }
}

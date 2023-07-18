package designPattern.builder;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Bike {
    private String brand;
    private String model;
    private int year;
    private boolean isElectric;
    public static class BikeBuilder {
        private int year;
        private boolean isElectric = false;
        public BikeBuilder year(int year) {
            if (year < 1886) {
                throw new IllegalArgumentException("Invalid year");
            }
            this.year = year;
            return this;
        }
    }

    public static void main(String[] args) {
        Bike bike = Bike.builder()
                .brand("Tesla")
                .model("Model S")
                .year(2022) // This value satisfies the custom logic
                .build();
        System.out.println(bike);

    }
}

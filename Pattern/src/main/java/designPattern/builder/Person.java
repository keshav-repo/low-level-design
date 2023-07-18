package designPattern.builder;

public class Person {
    private String name;
    private int age;
    private String address;

    private Person(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.address = builder.address;
    }

    public static class Builder {
        private String name;
        private int age;
        private String address;

        public Builder() {
            // Set default values if needed
            this.name = "Default";
            this.age = 0;
            this.address = "";
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Person build() {
            // Set values dynamically based on certain conditions
            if (this.age < 18) {
                this.address = "Child Address";
            } else {
                this.address = "Adult Address";
            }

            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return String.format("name %s, age %d, address %s", name, age, address);
    }

    public static void main(String[] args) {

        Person person = new Person.Builder()
                .name("John Doe")
                .age(17) // Override specific property
                .build();

        System.out.println(person);

    }

}

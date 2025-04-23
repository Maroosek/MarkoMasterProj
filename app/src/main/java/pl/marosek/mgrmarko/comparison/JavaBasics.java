package pl.marosek.mgrmarko.comparison;

// Ignore this class, it is for pure educational purposes only

// X. Variable and its initialization
class JavaVariables {
    public static void main(String[] args) {

        int number = 5;
        number += 3;
        // number = 8

        final int constant = 10;
        // stała, nie można zmienić wartości

        String text = "Hello";

        System.out.println(text + ", World");

        String multiline = "Line 1\nLine 2\nLine 3";
        System.out.println(multiline);

    }
}


// X. Class declaration and hello world
public class JavaBasics {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}


// x. Function declaration
class JavaFunctions {
    public static void main(String[] args) {
        int result = add(5, 3);
        // result = 8
    }

    public static int add(int a, int b) {
        return a + b;
    }
}

// x. classes and objects
class CarJava {
    private String manufacturer;
    private String model;

    public CarJava(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public void drive() {
        System.out.println("Driving " + manufacturer + " " + model);
    }

    public static void main(String[] args) {
        CarJava car = new CarJava("Toyota", "Corolla");
        car.drive();
    }
}

// x. Inheritance
class AnimalJava {
    public void eat() {
        System.out.println("Eating");
    }
}

class DogJava extends AnimalJava {
    public void bark() {
        System.out.println("Barking");
    }

    public static void main(String[] args) {
        DogJava dog = new DogJava();
        dog.eat();
        dog.bark();
    }
}

// x. loops

class JavaLoops {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }

        int i = 0;
        while (i < 5) {
            System.out.println(i);
            i++;
        }
    }
}

// x. conditions

class JavaConditions {
    public static void main(String[] args) {
        int number = 5;
        if (number > 0) {
            System.out.println("Number is positive");
        } else if (number < 0) {
            System.out.println("Number is negative");
        } else
            System.out.println("Number is zero");

        String grade = "5";
        switch (grade) {
            case "5":
                System.out.println("Excellent");
                break;
            case "4":
                System.out.println("Good");
                break;
            case "3":
                System.out.println("Satisfactory");
                break;
            default:
                System.out.println("Fail");
        }
    }
}

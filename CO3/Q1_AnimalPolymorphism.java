package c03;

// Q1: Implement Polymorphism with Different Animal Sounds
// Demonstrates method overriding and runtime polymorphism

// Base class Animal
class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    // Method to be overridden by subclasses
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    public String getName() {
        return name;
    }
}

// Dog subclass
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println("Woof");
    }
    
    public void wagTail() {
        System.out.println(name + " is wagging tail");
    }
}

// Cat subclass
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
    
    public void purr() {
        System.out.println(name + " is purring");
    }
}

// Cow subclass
class Cow extends Animal {
    public Cow(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println("Moo");
    }
    
    public void giveMilk() {
        System.out.println(name + " is giving milk");
    }
}

public class Q1_AnimalPolymorphism {
    
    // Method demonstrating polymorphism - accepts Animal reference
    public static void makeAnimalSound(Animal animal) {
        System.out.print(animal.getName() + " says: ");
        animal.makeSound(); // Dynamic binding - correct overridden method is called
    }
    
    // Method to demonstrate polymorphism with array
    public static void makeAllAnimalsSound(Animal[] animals) {
        System.out.println("\n=== All Animals Making Sounds ===");
        for (Animal animal : animals) {
            makeAnimalSound(animal);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Animal Polymorphism Demonstration ===");
        
        // Create different animal objects
        Dog dog = new Dog("Buddy");
        Cat cat = new Cat("Whiskers");
        Cow cow = new Cow("Bessie");
        
        System.out.println("\n1. Direct method calls:");
        dog.makeSound();
        cat.makeSound();
        cow.makeSound();
        
        System.out.println("\n2. Polymorphism with Animal references:");
        // Using Animal references to call overridden methods
        Animal animal1 = new Dog("Rex");
        Animal animal2 = new Cat("Fluffy");
        Animal animal3 = new Cow("Daisy");
        
        animal1.makeSound(); // Calls Dog's makeSound()
        animal2.makeSound(); // Calls Cat's makeSound()
        animal3.makeSound(); // Calls Cow's makeSound()
        
        System.out.println("\n3. Polymorphism with method parameter:");
        makeAnimalSound(dog);
        makeAnimalSound(cat);
        makeAnimalSound(cow);
        
        System.out.println("\n4. Polymorphism with array:");
        Animal[] animalFarm = {
            new Dog("Charlie"),
            new Cat("Mittens"),
            new Cow("Moobert"),
            new Dog("Max"),
            new Cat("Shadow")
        };
        
        makeAllAnimalsSound(animalFarm);
        
        System.out.println("\n5. Demonstrating other inherited methods:");
        for (Animal animal : animalFarm) {
            animal.sleep(); // Inherited method from Animal class
        }
        
        System.out.println("\n6. Type checking and casting:");
        for (Animal animal : animalFarm) {
            System.out.print(animal.getName() + " is a ");
            
            if (animal instanceof Dog) {
                System.out.println("Dog");
                ((Dog) animal).wagTail(); // Downcasting to access Dog-specific method
            } else if (animal instanceof Cat) {
                System.out.println("Cat");
                ((Cat) animal).purr(); // Downcasting to access Cat-specific method
            } else if (animal instanceof Cow) {
                System.out.println("Cow");
                ((Cow) animal).giveMilk(); // Downcasting to access Cow-specific method
            }
        }
        
        System.out.println("\n=== Polymorphism Summary ===");
        System.out.println("✓ Same method call (makeSound()) produces different behaviors");
        System.out.println("✓ Method resolution happens at runtime (dynamic binding)");
        System.out.println("✓ Animal reference can point to any Animal subclass object");
        System.out.println("✓ Enables writing flexible code that works with multiple types");
    }
}
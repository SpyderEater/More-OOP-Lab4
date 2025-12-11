package ua.edu.ucu.apps;

/**
 * Main class that combines functionality from FactoryDemo, GameManager, and original Main
 * Demonstrates character creation using factory pattern and manages game fights
 */
public class Main {
    
    /**
     * Game manager functionality for handling fights between characters
     */
    public static void fight(Character c1, Character c2) {
        System.out.println("Fight begins between " + c1 + " and " + c2);
        
        while (c1.isAlive() && c2.isAlive()) {
            System.out.println("\n" + c1 + " attacks " + c2);
            c1.kick(c2);
            System.out.println("After attack: " + c2);
            
            if (c2.isAlive()) {
                System.out.println("\n" + c2 + " attacks " + c1);
                c2.kick(c1);
                System.out.println("After attack: " + c1);
            }
        }
        
        if (c1.isAlive()) {
            System.out.println("\nFight ended! " + c1.getClass().getSimpleName() + " wins!");
        } else {
            System.out.println("\nFight ended! " + c2.getClass().getSimpleName() + " wins!");
        }
    }
    
    /**
     * Factory demonstration showing different ways to create characters
     */
    public static void demonstrateFactory() {
        System.out.println("=== Character Factory Demonstration ===\n");

        // Method 1: Using instance method with reflection
        System.out.println("1. Using instance method (createCharacter):");
        CharacterFactory factory = new CharacterFactory();
        for (int i = 0; i < 3; i++) {
            Character character = factory.createCharacter();
            System.out.println("  " + character);
        }

        // Method 2: Using static method with direct instantiation
        System.out.println("\n2. Using static method (createRandomCharacter):");
        for (int i = 0; i < 3; i++) {
            Character character = CharacterFactory.createRandomCharacter();
            System.out.println("  " + character);
        }

        // Method 3: Using static method with reflection
        System.out.println("\n3. Using static method with reflection (createRandomCharacterWithReflection):");
        try {
            for (int i = 0; i < 3; i++) {
                Character character = CharacterFactory.createRandomCharacterWithReflection();
                System.out.println("  " + character);
            }
        } catch (Exception e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // Method 4: Using reflections library
        System.out.println("\n4. Using reflections library (createRandomCharacterWithLibReflections):");
        try {
            for (int i = 0; i < 3; i++) {
                Character character = CharacterFactory.createRandomCharacterWithLibReflections();
                System.out.println("  " + character);
            }
        } catch (Exception e) {
            System.out.println("  Error: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        System.out.println("\n=== Factory Demonstration Complete ===");
    }
    
    /**
     * Game demonstration combining character creation and fighting
     */
    public static void demonstrateGame() {
        System.out.println("\n=== Lord of the Rings Heroes Game ===\n");

        // Create a character factory
        CharacterFactory factory = new CharacterFactory();

        // Create random characters
        Character character1 = factory.createCharacter();
        Character character2 = factory.createCharacter();

        System.out.println("Created two random characters:");
        System.out.println("Character 1: " + character1);
        System.out.println("Character 2: " + character2);
        System.out.println();

        // Start the fight
        fight(character1, character2);

        System.out.println("\n=== Game Demonstration Complete ===");
    }

    public static void main(String[] args) {
        // Run factory demonstration
        demonstrateFactory();
        
        // Run game demonstration
        demonstrateGame();
    }
}
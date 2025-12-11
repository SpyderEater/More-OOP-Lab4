package ua.edu.ucu.apps;

import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CharacterFactory {
    private static final Random RANDOM = new Random();
    private static final Class<?>[] characterClasses = {
        Hobbit.class, Elf.class, King.class, Knight.class
    };

    private static final List<Class<?>> classes = List.of(
        Hobbit.class, Elf.class, King.class, Knight.class
    );

    /**
     * Instance method to create a random character using reflection
     */
    public Character createCharacter() {
        try {
            Class<?> randomClass = characterClasses[RANDOM.nextInt(characterClasses.length)];
            Constructor<?> constructor = randomClass.getDeclaredConstructor();
            return (Character) constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | 
                 IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to create character", e);
        }
    }

    /**
     * Static method to create a random character using direct instantiation
     */
    public static Character createRandomCharacter() {
        int choice = RANDOM.nextInt(4); // Fixed: should be 4 for 4 character types
        return switch (choice) {
            case 0 -> new Hobbit();
            case 1 -> new Elf();
            case 2 -> new Knight();
            case 3 -> new King();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
    }

    /**
     * Static method to create a random character using reflection with proper error handling
     */
    public static Character createRandomCharacterWithReflection()
            throws NoSuchMethodException, InvocationTargetException, 
                   InstantiationException, IllegalAccessException {
        int choice = RANDOM.nextInt(classes.size());
        Class<?> characterClass = classes.get(choice);
        Constructor<?> constructor = characterClass.getDeclaredConstructor();
        return (Character) constructor.newInstance();
    }

    /**
     * Static method to create a random character using org.reflections library
     */
    public static Character createRandomCharacterWithLibReflections() {
        try {
            Reflections reflections = new Reflections("ua.edu.ucu.apps");
            Set<Class<? extends Character>> subTypes = reflections.getSubTypesOf(Character.class);
            
            if (subTypes.isEmpty()) {
                throw new RuntimeException("No Character subclasses found");
            }
            
            int choice = RANDOM.nextInt(subTypes.size());
            Class<?> characterClass = (Class<?>) subTypes.toArray()[choice];
            Constructor<?> constructor = characterClass.getDeclaredConstructor();
            return (Character) constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | 
                 IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to create character using reflections", e);
        }
    }
}
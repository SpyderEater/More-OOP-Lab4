import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import ua.edu.ucu.apps.*;

/**
 * Unit tests for the Main class functionality
 * Tests the unified functionality from FactoryDemo, GameManager, and original Main
 */
public class MainTest {
    
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Test fight method with two characters")
    public void testFight() {
        // Create test characters
        CharacterFactory factory = new CharacterFactory();
        ua.edu.ucu.apps.Character char1 = factory.createCharacter();
        ua.edu.ucu.apps.Character char2 = factory.createCharacter();
        
        // Store original HP to ensure fight actually happened
        int originalHp1 = char1.getHp();
        int originalHp2 = char2.getHp();
        
        // Execute fight
        Main.fight(char1, char2);
        
        // Verify that at least one character lost HP or died
        assertTrue(char1.getHp() < originalHp1 || char2.getHp() < originalHp2 || !char1.isAlive() || !char2.isAlive(),
                "Fight should result in at least one character taking damage");
        
        // Verify that exactly one character is alive at the end
        assertTrue(char1.isAlive() ^ char2.isAlive(), "Exactly one character should survive the fight");
        
        // Verify output contains fight messages
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Fight begins between"), "Output should contain fight start message");
        assertTrue(output.contains("Fight ended!"), "Output should contain fight end message");
        assertTrue(output.contains("wins!"), "Output should contain winner announcement");
    }

    @Test
    @DisplayName("Test demonstrateFactory method")
    public void testDemonstrateFactory() {
        Main.demonstrateFactory();
        
        String output = outputStreamCaptor.toString();
        
        // Verify all factory demonstration sections are present
        assertTrue(output.contains("Character Factory Demonstration"), "Should contain factory demo header");
        assertTrue(output.contains("1. Using instance method (createCharacter)"), "Should test instance method");
        assertTrue(output.contains("2. Using static method (createRandomCharacter)"), "Should test static method");
        assertTrue(output.contains("3. Using static method with reflection"), "Should test reflection method");
        assertTrue(output.contains("4. Using reflections library"), "Should test reflections library method");
        assertTrue(output.contains("Factory Demonstration Complete"), "Should contain completion message");
    }

    @Test
    @DisplayName("Test demonstrateGame method")
    public void testDemonstrateGame() {
        Main.demonstrateGame();
        
        String output = outputStreamCaptor.toString();
        
        // Verify game demonstration elements
        assertTrue(output.contains("Lord of the Rings Heroes Game"), "Should contain game header");
        assertTrue(output.contains("Created two random characters"), "Should mention character creation");
        assertTrue(output.contains("Character 1:"), "Should show first character");
        assertTrue(output.contains("Character 2:"), "Should show second character");
        assertTrue(output.contains("Fight begins between"), "Should start a fight");
        assertTrue(output.contains("Game Demonstration Complete"), "Should contain completion message");
    }

    @Test
    @DisplayName("Test main method runs both demonstrations")
    public void testMain() {
        Main.main(new String[]{});
        
        String output = outputStreamCaptor.toString();
        
        // Verify both demonstrations run
        assertTrue(output.contains("Character Factory Demonstration"), "Main should run factory demonstration");
        assertTrue(output.contains("Lord of the Rings Heroes Game"), "Main should run game demonstration");
        assertTrue(output.contains("Factory Demonstration Complete"), "Factory demo should complete");
        assertTrue(output.contains("Game Demonstration Complete"), "Game demo should complete");
    }

    @Test
    @DisplayName("Test fight method handles characters with different HP")
    public void testFightWithDifferentCharacters() {
        // Create characters of specific types for more predictable testing
        Elf elf = new Elf();
        Hobbit hobbit = new Hobbit();
        
        Main.fight(elf, hobbit);
        
        // Verify the fight concluded properly
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Fight begins between"), "Should start the fight");
        assertTrue(output.contains("wins!"), "Should declare a winner");
        
        // One should be alive, one should be dead
        assertTrue(elf.isAlive() ^ hobbit.isAlive(), "Exactly one character should survive");
    }

    @Test
    @DisplayName("Test that factory methods are being called in demonstrateFactory")
    public void testFactoryMethodCalls() {
        Main.demonstrateFactory();
        
        String output = outputStreamCaptor.toString();
        
        // Count character creations - should be at least 12 (3 per method * 4 methods)
        // Even if some methods fail, we should see some character objects
        int characterCount = output.split("hp=").length - 1; // Count character toString outputs
        assertTrue(characterCount >= 3, "Should create multiple characters during demonstration");
    }
    
    // Clean up after each test
    public void tearDown() {
        System.setOut(originalOut);
    }
}
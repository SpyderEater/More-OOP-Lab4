package ua.edu.ucu.apps;

/**
 * Strategy interface for different kick behaviors
 * This allows easier creation of new characters with different attack strategies
 */
public interface KickStrategy {
    void kick(Character attacker, Character target);
}
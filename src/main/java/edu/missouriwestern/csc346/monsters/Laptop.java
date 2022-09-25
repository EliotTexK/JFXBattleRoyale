package edu.missouriwestern.csc346.monsters;

public class Laptop extends Computer {

    public Laptop(OperatingSystem operatingSystem) {
        super("Laptop PC", 3, 0.5, 0.5, "💻", operatingSystem);
    }

    @Override
    public void computerAttack() {
        setAttackEffectiveness(0);
        setAttackMessage("tried desparately to attack, but alas, could do nothing, as it is a Laptop");
    }
    
}

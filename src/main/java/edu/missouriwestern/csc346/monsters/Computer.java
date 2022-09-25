
package edu.missouriwestern.csc346.monsters;

public abstract class Computer extends Critter {

    private OperatingSystem operatingSystem;

    public Computer(String name, double weight, double stamina, double health,
            String imageCharacter, OperatingSystem operatingSystem) {
        super(String.format("%s running %s", name, operatingSystem.getName()),
            weight, stamina, health,
            1.00 - operatingSystem.getChanceOfBug(),
            imageCharacter);
        setOperatingSystem(operatingSystem);
    }

    @Override
    protected String getNoAttackMessage() {
        return operatingSystem.getRandomBugMessage();
    }

    @Override
    public void attack() {
        computerAttack();
        setAttackEffectiveness(getAttackEffectiveness()*operatingSystem.getEfficiencyMultiplier());
    }

    protected abstract void computerAttack();

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
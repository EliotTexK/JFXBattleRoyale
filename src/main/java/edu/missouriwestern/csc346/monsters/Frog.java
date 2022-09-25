package edu.missouriwestern.csc346.monsters;

public class Frog extends Critter {
    public Frog() {
        super("Frog", 1, 0.50, 1.0, .5, "");
        setImageCharacter("🐸");
    }

    @Override
    protected String getNoAttackMessage() {
        String[] messages = {
                "croaks",
                "ribbits",
                "jumps",
                "eats a bug"
        };
        int pick = (int) (Math.random() * messages.length);
        return messages[pick];
    }

    @Override
    public void attack() {
        setAttackMessage(
                "attacks with a deadly poison. As it turns out, this was no ordinary frog, but a poison dart frog!");
        setAttackEffectiveness(2.00);
    }
}

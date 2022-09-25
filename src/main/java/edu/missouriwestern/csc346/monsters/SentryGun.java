package edu.missouriwestern.csc346.monsters;

public class SentryGun extends Computer {

    public SentryGun(OperatingSystem operatingSystem) {
        super("SGR-A1 Autonomous Sentry Gun", 117, 0.75, 1.00, "🔫", operatingSystem);
    }

    @Override
    public void computerAttack() {
        setAttackEffectiveness(1.0);
        String[] messages = {
                "fires a deadly barrage of rocket-powered grenades",
                "fires its machine gun",
                "fires its machine gun, following up with a barrage of grenades"
        };
        int pick = (int) (Math.random() * messages.length);
        setAttackMessage(messages[pick]);
    }
}
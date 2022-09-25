package edu.missouriwestern.csc346.monsters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class OperatingSystem {
    private String name;
    private double efficiencyMultiplier;
    private double chanceOfBug;
    private ArrayList<String> bugMessages;

    public OperatingSystem(String name, double efficiencyMultiplier, double chanceOfBug,
            ArrayList<String> bugMessages) {
        this.name = name;
        this.efficiencyMultiplier = efficiencyMultiplier;
        this.chanceOfBug = chanceOfBug;
        this.bugMessages = bugMessages;
    }

    public String getName() {
        return name;
    }

    public double getEfficiencyMultiplier() {
        return efficiencyMultiplier;
    }

    public double getChanceOfBug() {
        return chanceOfBug;
    }

    public String getRandomBugMessage() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, bugMessages.size());
        return "encountered an issue with their operating system: \n" + bugMessages.get(randomNum);
    }

    public static OperatingSystem WindowsPreset() {
        return new OperatingSystem("Windows", 1.5, 0.6,
        new ArrayList<String>(Arrays.asList(
            "\"Getting windows ready, Don't turn off your computer\"",
            "\"This application failed to start because the DLL file was not found. Re-installing the application may fix this problem\"",
            "\"Working on updates, 99% complete, Don't turn off your computer\"",
            "\"Task failed successfully.\"",
            "\"Your PC ran into a problem that it couldn't handle, and now it needs to restart. \nYou can search for the error online: AMBIGUOUS_ERROR\""
            )));
    }

    public static OperatingSystem MacPreset() {
        return new OperatingSystem("MacOS", 1.25, 0.3,
        new ArrayList<String>(Arrays.asList(
            "Too busy working off the debt accrued from buying an Apple device...",
            "Too busy trying to justify paying THAT much for an Apple device..."
            )));
    }

    public static OperatingSystem LinuxPreset() {
        return new OperatingSystem("GNU/Linux", 1.7, 0.4,
        new ArrayList<String>(Arrays.asList(
            "\"bash: command not found\"",
            "\"Failed to download repository information...\"",
            "\"Kernel panic - not syncing: VFS: Unable to mount root fs on unknown-block(0,0)\"",
            "\"command: unrecognized option '--option' \n Try 'command --help' for more information",
            "\"Failed to start fight.service: Access denied\""
            )));
    }
}

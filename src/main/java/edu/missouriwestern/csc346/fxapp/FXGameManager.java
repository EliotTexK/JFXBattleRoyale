package edu.missouriwestern.csc346.fxapp;

import edu.missouriwestern.csc346.monsters.*;
import java.lang.Runnable;
import java.util.ArrayList;

public class FXGameManager extends GameManager implements Runnable {
    ArrayList<Player> roster;
    String terminalOutput = "";
    String bottomOutput = "";
    String topOutput = "";
    Thread gameThread;
    boolean gameIsDone = false;

    @Override
    public String displayMessage(String message) {
        // filter out the fight announcement message:
        if (message.contains("remaining rounds")) {
            roundStart(message);
            message = "";
        }
        terminalOutput += message + "\n";
        return message;
    }

    @Override
    public void fightAnnouncement(Player p1, Player p2) {
        String message1 = String.format("%s\n       VS\n%s", p1.toString(), p2.toString());
        String message2 = String.format("\n%s (%1.0f%%)\n           and\n%s (%1.0f%%)\n           enter the game...\n",
                p1.toString(), p1.getBody().getHealth() * 100.0, p2.toString(), p2.getBody().getHealth() * 100.0);
        bottomOutput = message1;

        // Notify the render thread that the game is paused "unpause" the render thread
        synchronized (this) {
            notify();
        }

        // wait for button to unpause the game thread
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        displayMessage(message2);
    }

    public void roundStart(String message) {
        topOutput = message.split("\n")[0] + "\n";
    }

    @Override
    public void run() {
        contest(roster);
        gameIsDone = true;
        synchronized (this) {
            notify();
        }
    }

    public FXGameManager(ArrayList<Player> _roster) {
        roster = _roster;

    }

    public void startGameThread() throws InterruptedException {
        gameThread = new Thread(this);
        gameThread.start();

        // wait for game thread to finish the round
        synchronized (this) {
            wait();
        }
        // now that it's finished, output the results to GUI
        App.addToCenterTerminal(terminalOutput);
        terminalOutput = "";
        App.setTopPane(topOutput);
        App.setBottomPane(bottomOutput);
        App.displayRoster();
    }

    public void unpause() throws InterruptedException {
        if (gameIsDone) {
            App.addToCenterTerminal("\nGame Over. Get outta here!");
            return;
        }

        // unpause the game thread
        synchronized (this) {
            notify();
        }

        // wait for game thread to finish the round
        synchronized (this) {
            wait();
        }

        // now that it's finished, output the results to GUI
        App.addToCenterTerminal(terminalOutput);
        terminalOutput = "";
        App.setTopPane(topOutput);
        App.setBottomPane(bottomOutput);
        App.displayRoster();
    }
}

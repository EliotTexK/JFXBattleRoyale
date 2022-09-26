package edu.missouriwestern.csc346.fxapp;

import javafx.application.Platform;

// Wrapper that runs the REAL application so that
// we can load JavaFX at the right time.
public class Entry {
    public static void main(String[] args) {
        // Speaking of other hacky workarounds, we also need to
        // run Platform.startup() with a runnable so that JavaFX
        // has time to initialize before we start the actual app.
        Platform.startup(() -> {});
        App.entryPoint(args);
    }
}

package edu.missouriwestern.csc346.fxapp;

import java.util.ArrayList;

import edu.missouriwestern.csc346.monsters.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    private static FXGameManager gameManager;
    private static ArrayList<Player> roster = new ArrayList<>();
    private static VBox centerTerminal = new VBox();
    private static ScrollPane center = new ScrollPane(centerTerminal);
    private static HBox bottomBox = new HBox();
    private static VBox bottomPane = new VBox();
    private static Button advanceButton = new Button();
    private static VBox topPane = new VBox();
    private static VBox leftPane = new VBox();

    @Override
    public void start(Stage stage) throws InterruptedException {
        centerTerminal.setPadding(new Insets(0, 0, 0, 20));
        bottomPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(10,20,0,20));
        topPane.setAlignment(Pos.CENTER);

        advanceButton.setText("TO THE DEATH!");
        advanceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                try {
                    gameManager.unpause();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().add(bottomPane);
        bottomBox.getChildren().add(advanceButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(center);
        borderPane.setBottom(bottomBox);
        borderPane.setTop(topPane);
        borderPane.setLeft(leftPane);
        HBox.setHgrow(bottomPane, Priority.ALWAYS);

        var scene = new Scene(borderPane, 1024, 768);
        scene.getStylesheets().add(getClass().getResource("/style-main.css").toExternalForm());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        gameManager.unpause();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        stage.setTitle("ELIOT'S BATTLE ROYALE");
        stage.setScene(scene);
        stage.show();

        appMain();
    }

    // You don't want to run main() in this file, or else JavaFX
    // would be required in the module path on startup. This gives
    // the "javafx runtime components are missing" error. Since we
    // are trying to compile our app as a standalone jar, we want it
    // to run without a fuss.
    public static void entryPoint(String[] args) {
        launch();
    }

    public static void addToCenterTerminal(String message) {
        Label lbl = new Label(message);
        centerTerminal.getChildren().add(lbl);
    }

    public static void setBottomPane(String message) {
        String[] mStrings = message.split("\n");
        bottomPane.getChildren().clear();
        for (String s : mStrings) {
            bottomPane.getChildren().add(new Label(s));
        }
    }

    public static void setTopPane(String message) {
        Label lbl = new Label(message);
        lbl.setStyle("-fx-font-size: 22");
        topPane.getChildren().clear();
        topPane.getChildren().add(lbl);
    }

    public static void appMain() throws InterruptedException {
        // ARRAYLIST IS PASS-BY-REFERENCE!!!
        gameManager = new FXGameManager(roster);

        roster.add(new Player("E102-Gamma", new SentryGun(OperatingSystem.WindowsPreset())));
        roster.add(new Player("4CE0460D0G", new Laptop(OperatingSystem.LinuxPreset())));
        roster.add(new Player("Broccoli Spears", new Cook()));
        roster.add(new Player("Gordon Ramsay", new Cook()));
        roster.add(new Player("Crazy", new Frog()));
        roster.add(new Player("Randy", new GiantRat()));
        roster.add(new Player("Remmy", new GiantRat()));
        
        gameManager.startGameThread();
    }

    public static void displayRoster() {
        leftPane.getChildren().clear();
        leftPane.getChildren().add(new Label("THE ROSTER:"));
        for (int i = 0; i < roster.size(); i++) {
            String message = String.format("%d. %s",i+1,roster.get(i).getName());
            leftPane.getChildren().add(new Label(message));
        }
    }
}
package edu.bsu.cs222;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GUI extends Application{

    Button search;
    Button brokenButton;
    Scene input, output;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wikipedia Revision Checker");

        Label instructionText = new Label("Please insert the name of a Wikipedia article you wish to check the revision history of.");
        Label outputText = new Label("This is where the output will go.");

        TextField articleInput = new TextField();

        search = new Button();
        search.setText("Search");
        search.setOnAction(e -> {
            primaryStage.setScene(output);
            String input =articleInput.getText();
        });

        brokenButton = new Button();
        brokenButton.setText("This is an empty result");
        brokenButton.setOnAction(e -> AlertBox.output("Oops", "Invalid input"));

        VBox layoutInput = new VBox(50);
        layoutInput.getChildren().addAll(instructionText, articleInput, search, brokenButton);
        layoutInput.setAlignment(Pos.CENTER_LEFT);

        StackPane layoutOutput = new StackPane();
        layoutOutput.getChildren().add(outputText);

        input = new Scene(layoutInput, 500, 400);
        output = new Scene(layoutOutput, 400, 400);

        primaryStage.setScene(input);
        primaryStage.show();
    }

}

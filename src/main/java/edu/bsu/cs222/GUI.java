package edu.bsu.cs222;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;


public class GUI extends Application{

    Button search;
    Button brokenButton;
    Scene input, output;
    Label redirectText;
    VBox layoutOutput = new VBox();
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wikipedia Revision Checker");

        Label instructionText = new Label("Please insert the name of a Wikipedia article you wish to check the revision history of.");
        //redirectText = new Label("");
        TextField articleInput = new TextField();

        search = new Button();
        search.setText("Search");
        search.setOnAction(e -> {
            try {
                output(primaryStage, articleInput);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        brokenButton = new Button();
        brokenButton.setText("This is an empty result");
        brokenButton.setOnAction(e -> AlertBox.output("Oops", "Invalid input"));

        VBox layoutInput = new VBox(50);
        layoutInput.getChildren().addAll(instructionText, articleInput, search, brokenButton);
        layoutInput.setAlignment(Pos.CENTER_LEFT);


        input = new Scene(layoutInput, 500, 400);

        primaryStage.setScene(input);
        primaryStage.show();
    }

    public void wikiSearch(TextField articleInput) throws IOException {
        String input =articleInput.getText();
        String JSONString = new WikiConnection().getJSONStringFromArticleName(input);
        JSONParser parser = new JSONParser(JSONString);
        ArrayList<Revision> revisions = parser.constructRevisionArrayList();

        Label outputText = new Label("Showing the last " + revisions.size() + " edits:\n");
        layoutOutput.getChildren().add(outputText);

        for (Revision revision : revisions) {
            Label revisionsText = new Label(revision.getTimestamp() + " " + revision.getUsername() + "\n");
            layoutOutput.getChildren().add(revisionsText);
        }
    }

    public void redirectOutput(TextField articleInput) throws IOException{
        String input =articleInput.getText();
        String JSONString = new WikiConnection().getJSONStringFromArticleName(input);
        JSONParser parser = new JSONParser(JSONString);
        redirectText = new Label(parser.getRedirectsAsString());
        layoutOutput.getChildren().add(redirectText);
    }

    public void output(Stage primaryStage, TextField articleInput) throws IOException{
        redirectOutput(articleInput);
        wikiSearch(articleInput);
        layoutOutput.setAlignment(Pos.CENTER);
        output = new Scene(layoutOutput, 400, 400);
        primaryStage.setScene(output);
    }
}

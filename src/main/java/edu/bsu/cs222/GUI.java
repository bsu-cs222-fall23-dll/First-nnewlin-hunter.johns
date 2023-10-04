package edu.bsu.cs222;


import com.jayway.jsonpath.JsonPath;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.minidev.json.JSONArray;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class GUI extends Application{

    Button search;
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


        VBox layoutInput = new VBox(50);
        layoutInput.getChildren().addAll(instructionText, articleInput, search);
        layoutInput.setAlignment(Pos.CENTER_LEFT);


        input = new Scene(layoutInput, 500, 400);

        primaryStage.setScene(input);
        primaryStage.show();
    }

    public void wikiSearch(String input) throws IOException {
            String JSONString = new WikiConnection().getJSONStringFromArticleName(input);
            JSONParser parser = new JSONParser(JSONString);
            boolean validArticle = articleChecker(JSONString);

            if (validArticle){
                ArrayList<Revision> revisions = parser.constructRevisionArrayList();

                Label outputText = new Label("Showing the last " + revisions.size() + " edits:\n");
                layoutOutput.getChildren().add(outputText);

                for (Revision revision : revisions) {
                    Label revisionsText = new Label(revision.getTimestamp() + " " + revision.getUsername() + "\n");
                    layoutOutput.getChildren().add(revisionsText);
                }
            }

    }

    public void redirectOutput(String input) throws IOException{
        String JSONString = new WikiConnection().getJSONStringFromArticleName(input);
        JSONParser parser = new JSONParser(JSONString);
        redirectText = new Label(parser.getRedirectsAsString());
        layoutOutput.getChildren().add(redirectText);
    }

    public void output(Stage primaryStage, TextField articleInput) throws IOException{
        String input =articleInput.getText();
        boolean validInput = inputChecker(input);
        if (validInput){
        redirectOutput(input);
        wikiSearch(input);}
        layoutOutput.setAlignment(Pos.CENTER);
        output = new Scene(layoutOutput, 400, 400);
        primaryStage.setScene(output);
    }

    public boolean inputChecker(String input) {
        if (Objects.equals(input, "")) {
            AlertBox.output("Oops", "No input detected.");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean articleChecker(String JSONString) {
        JSONArray JSONPages = JsonPath.read(JSONString, "$.query.pages.*.[*]");
        int pageID = Integer.parseInt(JSONPages.get(0).toString());
        if (pageID == 0) {
            AlertBox.output("Oops", "No page found.");
            return false;
        }
        else{return true;}
    }
}

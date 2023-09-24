package edu.bsu.cs222;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String articleName;
        String JSONString;

        System.out.println("Enter article name:");
        articleName = new Scanner(System.in).nextLine();
        JSONString = new WikiConnection().getJSONStringFromArticleName(articleName);
        JSONParser parser = new JSONParser(JSONString);
        System.out.print(parser.getAnyRedirects());
        System.out.println();
        ArrayList<Revision> revisions = parser.constructRevisionList();
        Main.displayRevisionsFromList(revisions);

    }
    public static void displayRevisionsFromList(ArrayList<Revision> revisionList)
    {
        System.out.print("Showing the last " + revisionList.size() + " edits:\n");
        for (Revision revision : revisionList) {
            System.out.print(revision.getTimestamp() + " " + revision.getUsername() + "\n");
        }
    }
}

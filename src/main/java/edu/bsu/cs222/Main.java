package edu.bsu.cs222;

import java.io.IOException;
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
        System.out.print(JSONString);
        System.out.println();
        System.out.print(parser.getAnyRedirects());
        System.out.println();
        System.out.print(parser.doesPageExist());

        ArrayList<Revision> revisions = parser.constructRevisionList();

    }
    public void displayRevisionsFromList(ArrayList<Revision> revisionList)
    {
        for(int x = 0; x < revisionList.size(); x++)
        {
            System.out.print("Showing the last " + revisionList.size() + " edits:\n");
            System.out.print(revisionList.get(x).getTimestamp() + " " + revisionList.get(x).getUsername() + "\n");
        }
    }
}

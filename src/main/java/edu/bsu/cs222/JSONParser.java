package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.util.ArrayList;

public class JSONParser
{
    String JSONString;
    public JSONParser(String JSONString)
    {
        this.JSONString = JSONString;
    }

    public String getRedirectsAsString()
    {
        if(isRedirected())
        {
            String unformattedRedirectString = "Redirected to " + JsonPath.read(this.JSONString,"$..redirects[*].to") + "\n";
            return formatRedirects(unformattedRedirectString);
        }
        else return "";
    }

    public String formatRedirects(String redirect)
    {
        redirect = redirect.replace("[","");
        redirect = redirect.replace("\"","");
        redirect = redirect.replace("]","");
        return redirect;
    }

    public ArrayList<Revision> constructRevisionArrayList() {
        JSONArray JSONRevisions = JsonPath.read(this.JSONString, "$..revisions[*]");
        ArrayList<Revision> revisionArrayList = new ArrayList<>();
        ArrayList<String> timestamps = constructTimestampArrayList();
        ArrayList<String> usernames = constructUsernameArrayList();
        if(doesPageExist())
        {
            for (int arrayIndex = 0; arrayIndex < JSONRevisions.size(); arrayIndex++)
            {
                revisionArrayList.add(new Revision(timestamps.get(arrayIndex), usernames.get(arrayIndex)));
            }
        }
        return revisionArrayList;
    }
    public ArrayList<String> constructTimestampArrayList()
    {
        return JsonPath.read(this.JSONString,"$..revisions[*].timestamp");
    }
    public ArrayList<String> constructUsernameArrayList()
    {
        return JsonPath.read(this.JSONString,"$..revisions[*].user");
    }
    public boolean isRedirected()
    {
        return !(JsonPath.read(this.JSONString,"$..redirects[*].to").toString().equals("[]"));
    }
    public boolean doesPageExist()
    {
        try
        {
            JSONArray JSONPages= JsonPath.read(this.JSONString,"$.query.pages.*.[*]");
            int pageID = Integer.parseInt(JSONPages.get(0).toString());
            if(pageID == 0)
            {
                System.err.println("No Page Found");
                System.exit(0);
                return false;
            }
            else
            {
                return true;
            }

        }
        catch(Exception e)
        {
            System.err.println("Invalid Input");
            System.exit(0);
            return false;
        }
    }
}
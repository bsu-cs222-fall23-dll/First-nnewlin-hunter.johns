package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.util.ArrayList;

public class JSONParser
{
    String JSONData;
    public JSONParser(String JSONData)
    {
        this.JSONData = JSONData;
    }
    public String getAnyRedirects()
    {
        if(isRedirected())
        {
            return "Redirected to: " + JsonPath.read(this.JSONData,"$..redirects[*].to") + "\n";
        }
        else return "";
    }
    public ArrayList<Revision> constructRevisionList()
    {
        JSONArray array = JsonPath.read(this.JSONData, "$..revisions[*]");
        ArrayList<Revision> revisionList = new ArrayList<>();
        ArrayList<String> timestamps = constructTimestampList();
        ArrayList<String> usernames = constructUsernameList();
        for(int x = 0; x < array.size(); x++)
        {
            revisionList.add(new Revision(timestamps.get(x),usernames.get(x)));
        }
        return revisionList;
    }
    private ArrayList<String> constructTimestampList()
    {
        return JsonPath.read(this.JSONData,"$..revisions[*].timestamp");
    }
    private ArrayList<String> constructUsernameList()
    {
        return JsonPath.read(this.JSONData,"$..revisions[*].user");
    }
    private boolean isRedirected()
    {
        return !(JsonPath.read(this.JSONData,"$..redirects[*].to").toString().equals("[]"));
    }
    public boolean doesPageExist() throws Exception
    {
        try
        {
            JSONArray array= JsonPath.read(this.JSONData,"$.query.pages.*.[*]");
            int pageID = Integer.parseInt(array.get(0).toString());
            if(pageID == 0)
            {
                System.err.println("Invalid Page");
                return false;
            }
            else
            {
                return true;
            }
        }
        catch(Exception e)
        {
            System.err.println("Not a Page Name");
            return false;
        }
    }
}
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
            String redirect = "Redirected to " + JsonPath.read(this.JSONData,"$..redirects[*].to") + "\n";
            return formatRedirects(redirect);
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

    public ArrayList<Revision> constructRevisionList() {
        JSONArray array = JsonPath.read(this.JSONData, "$..revisions[*]");
        ArrayList<Revision> revisionList = new ArrayList<>();
        ArrayList<String> timestamps = constructTimestampList();
        ArrayList<String> usernames = constructUsernameList();
        if(doesPageExist())
        {
            for (int x = 0; x < array.size(); x++)
            {
                revisionList.add(new Revision(timestamps.get(x), usernames.get(x)));
            }
        }
        return revisionList;
    }
    public ArrayList<String> constructTimestampList()
    {
        return JsonPath.read(this.JSONData,"$..revisions[*].timestamp");
    }
    public ArrayList<String> constructUsernameList()
    {
        return JsonPath.read(this.JSONData,"$..revisions[*].user");
    }
    public boolean isRedirected()
    {
        return !(JsonPath.read(this.JSONData,"$..redirects[*].to").toString().equals("[]"));
    }
    public boolean doesPageExist()
    {
        try
        {
            JSONArray array= JsonPath.read(this.JSONData,"$.query.pages.*.[*]");
            int pageID = Integer.parseInt(array.get(0).toString());
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
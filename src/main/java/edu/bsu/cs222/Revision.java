package edu.bsu.cs222;

public class Revision
{
    public String timestamp;
    public String username;
    public Revision(String timestamp, String username)
    {
        this.timestamp = timestamp;
        this.username = username;
    }
    public String getTimestamp()
    {
        return timestamp;
    }
    public String getUsername()
    {
        return username;
    }
}

package edu.bsu.cs222;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class WikiConnection
{
    public String getJSONStringFromArticleName(String articleName) throws IOException {
        WikiConnection wikiConnection = new WikiConnection();
        URL url = wikiConnection.getConstructedURLFromArticleName(articleName);
        URLConnection urlConnection = WikiConnection.connectToWikipedia(url);
        return wikiConnection.getJSONStringFromConnection(urlConnection);
    }

    public URL getConstructedURLFromArticleName(String articleName) throws MalformedURLException
    {
        return new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                    URLEncoder.encode(articleName, Charset.defaultCharset()) +
                    "&rvprop=timestamp|user&rvlimit=13&redirects");
    }

    public static URLConnection connectToWikipedia(URL url)
    {
        try
        {
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent",
                    "CS222FirstProject/0.1 (nnewlin@bsu.edu)");
            connection.connect();
            return connection;
        }
        catch(Exception e)
        {
            System.err.println("Network Error");
            System.exit(0);
            return null;
        }
    }
    public String getJSONStringFromConnection(URLConnection connection) throws IOException
    {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }
}
package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;

public class WikiConnectionTest {

    @Test
    public void getJSONStringFromArticleNameTest() throws IOException {
        WikiConnection connection = new WikiConnection();
        String results = connection.getJSONStringFromArticleName("Zappa").substring(0,11);
        Assertions.assertEquals("{\"continue\"", results); //If the first word comes through, it is to be expected that the rest of the string will as well
    }

    @Test
    public void getConstructedURLFromArticleNameTest() throws MalformedURLException {
        WikiConnection connection = new WikiConnection();
        String results = connection.getConstructedURLFromArticleName("Biden").toString();
        Assertions.assertEquals("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Biden&rvprop=timestamp|user&rvlimit=13&redirects", results);
    }

}

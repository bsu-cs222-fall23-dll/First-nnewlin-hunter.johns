package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;

public class WikiConnectionTest {

    @Test
    public void constructURLStringFromArticleNameTest() throws MalformedURLException {
        WikiConnection connection = new WikiConnection();
        String results = connection.getConstructedURLFromArticleName("Biden").toString();
        Assertions.assertEquals("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Biden&rvprop=timestamp|user&rvlimit=13&redirects", results);
    }
}

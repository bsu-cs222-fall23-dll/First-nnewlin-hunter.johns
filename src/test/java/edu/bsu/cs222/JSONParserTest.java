package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JSONParserTest
{
    @Test
    public void RedirectsTest() throws IOException {
        String JSONString = new WikiConnection().getJSONStringFromArticleName("Zappa");
        JSONParser parser = new JSONParser(JSONString);
        boolean validator = parser.isRedirected();
        Assertions.assertTrue(validator);
    }
    @Test
    public void getAnyRedirectsTest() throws IOException {
        String JSONString = new WikiConnection().getJSONStringFromArticleName("Zappa");
        JSONParser parser = new JSONParser(JSONString);
        String validator = parser.getAnyRedirects();
        Assertions.assertEquals("Redirected to: [\"Frank Zappa\"]\n", validator);
    }
}
package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.json.JSONArray;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RevisionParserTest
{
    @Test
    public void testParse() throws IOException {
        RevisionParser parser = new RevisionParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        JSONArray revisions = parser.parse(testDataStream);
        Assertions.assertEquals(null, revisions);
    }
}
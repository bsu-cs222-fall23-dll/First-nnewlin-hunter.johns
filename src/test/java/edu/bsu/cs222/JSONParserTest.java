package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Objects;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JSONParserTest {

    @Test
    public void testAccessToJsonFile() throws IOException
    {
        String jsonData = readSampleFileAsString("test.json");

        Assertions.assertNotNull(jsonData);
    }

    @Test
    public void testConstructRevisionList() throws IOException
    {
        String jsonData = readSampleFileAsString("test.json");

        JSONParser parser = new JSONParser(jsonData);
        ArrayList<Revision> revisions = parser.constructRevisionList();
        Assertions.assertNotNull(revisions);
    }
    @Test
    public void testRevisionListCount() throws IOException
    {
        String jsonData = readSampleFileAsString("test.json");

        JSONParser parser = new JSONParser(jsonData);
        ArrayList<Revision> revisions = parser.constructRevisionList();
        Assertions.assertEquals(4, revisions.size());
    }
    @Test
    public void testTrueRedirect() throws IOException
    {
        String jsonData = readSampleFileAsString("redirect.json");
        JSONParser parser = new JSONParser(jsonData);
        Assertions.assertTrue(parser.isRedirected());
    }
    @Test
    public void testFalseRedirect() throws IOException
    {
        String jsonData = readSampleFileAsString("test.json");
        JSONParser parser = new JSONParser(jsonData);
        Assertions.assertFalse(parser.isRedirected());
    }
    @Test
    public void testStringOutputWhenFalseRedirect() throws IOException
    {
        String jsonData = readSampleFileAsString("test.json");

        JSONParser testParser = new JSONParser(jsonData);
        Assertions.assertEquals("",testParser.getAnyRedirects());
    }
    @Test
    public void testStringOutputWhenTrueRedirect() throws IOException
    {
        String jsonData = readSampleFileAsString("redirect.json");

        JSONParser redirectParser = new JSONParser(jsonData);
        Assertions.assertEquals("Redirected to Joe Biden\n",redirectParser.getAnyRedirects());
    }
    @Test
    public void testRedirectFormat() throws IOException
    {
        String jsonData = readSampleFileAsString("redirect.json");

        //Redirect string with array brackets
        String rawRedirectString = "Redirected to " + JsonPath.read(jsonData,"$..redirects[*].to") + "\n";

        //Redirect string without array brackets
        JSONParser redirectParser = new JSONParser(jsonData);
        String formattedRedirectString = redirectParser.formatRedirects(rawRedirectString);

        Assertions.assertEquals("Redirected to Joe Biden\n",formattedRedirectString);
    }
    @Test
    public void testConstructTimestampList() throws IOException
    {
        String jsonData = readSampleFileAsString("test.json");
        JSONParser parser = new JSONParser(jsonData);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("2023-01-31T14:43:39Z");
        arrayList.add("2023-01-31T14:41:34Z");
        arrayList.add("2023-01-09T11:22:35Z");
        arrayList.add("2022-12-30T16:50:48Z");
        Assertions.assertEquals(arrayList,parser.constructTimestampList());
    }
    @Test
    public void testConstructUsernameList() throws IOException
    {
        String jsonData = readSampleFileAsString("test.json");
        JSONParser parser = new JSONParser(jsonData);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Kazamzam");
        arrayList.add("Kazamzam");
        arrayList.add("WikiCleanerBot");
        arrayList.add("Osseinavia");
        Assertions.assertEquals(arrayList,parser.constructUsernameList());
    }
    private String readSampleFileAsString(String filename) throws NullPointerException, IOException
    {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
        return new String(Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
    }
}
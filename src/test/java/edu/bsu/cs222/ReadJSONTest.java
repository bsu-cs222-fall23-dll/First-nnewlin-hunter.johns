package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReadJSONTest {

    @Test
    public void testAccessToJsonFile() throws IOException
    {
        String jsonData = readSampleFileAsString("test.json");
        Assertions.assertNotNull(jsonData);
    }

    @Test
    public void testCountRevisionsWithJsonPath() throws IOException
    {
        String jsonData = readSampleFileAsString("test.json");
        JSONArray revisions = getRevisionsFromJson(jsonData);
        Assertions.assertEquals(4, revisions.size());
    }

    @Test
    public void testRedirects() throws IOException {
        String jsonData = readSampleFileAsString("redirect.json");
        JSONArray redirects = getRevisionsFromJson(jsonData);

        Assertions.assertNotNull(redirects);
    }

    private String readSampleFileAsString(String filename) throws NullPointerException, IOException
    {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(filename);
        return new String(Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
    }

    private JSONArray getRevisionsFromJson(String jsonData)
    {
        return JsonPath.read(jsonData, "$..revisions[*]");
    }

}
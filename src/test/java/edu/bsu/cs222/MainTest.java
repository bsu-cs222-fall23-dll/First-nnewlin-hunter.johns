package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class MainTest {

    @Test
    public void validInputTest() throws IOException {
        String JSONString = new WikiConnection().getJSONStringFromArticleName("Hello");
        JSONParser parser = new JSONParser(JSONString);
        boolean validator = parser.doesPageExist();
        Assertions.assertTrue(validator);
    }


}

package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import java.util.*;

import java.io.IOException;
import java.io.InputStream;

public class RevisionParser
{

    public String parse(InputStream testDataStream) throws IOException {
        Object o = JsonPath.read(testDataStream, "$..timestamp");
        return o.toString();
    }
}
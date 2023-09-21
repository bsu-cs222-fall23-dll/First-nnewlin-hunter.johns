package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import java.util.*;

import org.json.JSONArray;
import java.io.IOException;
import java.io.InputStream;

public class RevisionParser
{

    public JSONArray parse(InputStream testDataStream) throws IOException {
        JSONArray revisionList = JsonPath.read(testDataStream, "$..revisions");
        return revisionList;
    }
}
package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RevisionTest
{
    @Test
    public void testGetTimestamp()
    {
        Revision revision = new Revision("023-01-31T14:43:39Z", "Kazamzam");
        Assertions.assertEquals("023-01-31T14:43:39Z", revision.getTimestamp());
    }
    @Test
    public void testGetUsername()
    {
        Revision revision = new Revision("023-01-31T14:43:39Z", "Kazamzam");
        Assertions.assertEquals("Kazamzam", revision.getUsername());
    }
}

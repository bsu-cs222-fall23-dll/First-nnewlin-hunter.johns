package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class MainTest {
    @Test
    public void testValidInput()
    {
        Main main = new Main();
        String testInput = main.input("Hello");
        Assertions.assertEquals("Hello", testInput);
    }

    @Test
    public void testInvalidInput(){
        Main main = new Main();
        Assertions.assertNull(main.inputValidator(""));
    }

    @Test
    public void testPass(){
        Main main = new Main();
        Assertions.assertNull(Main.pass(Main.inputValidator("")));
    }
}

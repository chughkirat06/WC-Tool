package WCTool;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandHandlerTest {
    private CommandHandler handler = new CommandHandler();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        // Redirect System.out to ByteArraystream
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        // Restore System.out to its original state
        System.setOut(originalOut);
    }

    @Test
    public void testCountBytes() throws IOException {
        String path = createTempFile("Hello world");
        handler.countBytes(path);
        assertEquals("11 ", outContent.toString());
    }

    @Test
    public void testCountLines() throws IOException {
        String path = createTempFile("Hello\nWorld");
        handler.countLines(path);
        assertEquals("2 ", outContent.toString());
    }

    @Test
    public void testCountWords() throws IOException {
        String path = createTempFile("Hello world in a new universe.");
        handler.countWords(path);
        assertEquals("6 ", outContent.toString());
    }

    @Test
    public void testCountChars() throws IOException {
        String path = createTempFile("Hello world!");
        handler.countChars(path);
        assertEquals("12 ", outContent.toString());
    }

    // @Test
    // public void testInvalidFlagMessage() {
    // handler.invalidFlagMessage("-z");
    // assertEquals("ccwc: invalid option -- 'z'", outContent.toString());
    // }

    // @Test
    // public void testNonExistentFile() {
    // String path = "NewFile.txt";
    // handler.countBytes(path);
    // assertEquals("ccwc: NewFile.txt: No such file or directory",
    // outContent.toString());
    // }

    private String createTempFile(String content) throws IOException {
        File tempFile = File.createTempFile("tempFile", ".txt");
        tempFile.deleteOnExit();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            bw.write(content);
        }
        return tempFile.getAbsolutePath();
    }

}

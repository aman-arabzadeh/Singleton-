import com.aman.singleton.util.Logger;
import com.aman.singleton.util.LoggerSingleton;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoggerSingletonTest {

    private static final String SERIALIZED_FILE_PATH = "logger.ser";

    @Test
    void testSingletonInstance() {
        LoggerSingleton logger1 = LoggerSingleton.getInstance();
        LoggerSingleton logger2 = LoggerSingleton.getInstance();
        assertSame(logger1, logger2);
    }
    @Test
    void testLogInfo() {
        LoggerSingleton logger = LoggerSingleton.getInstance();

        PrintStream originalOut = System.out; // Spara originalströmmen.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos)); // Omdirigera System.out.

        String testMessage = "This is an info log.";
        logger.logInfo(testMessage);

        System.setOut(originalOut); // Återställ originalströmmen.

        String logOutput = baos.toString();
        assertTrue(logOutput.contains(testMessage));
        assertTrue(logOutput.contains("[INFO]"));
    }
    @Test
    void testLogWarning() {
        LoggerSingleton logger = LoggerSingleton.getInstance();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        String testMessage = "This is a warning log.";
        logger.logWarning(testMessage);

        assertTrue(baos.toString().contains(testMessage));
        assertTrue(baos.toString().contains("[WARNING]"));
    }

    @Test
    void testLogError() {
        LoggerSingleton logger = LoggerSingleton.getInstance();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setErr(new PrintStream(baos));

        String testMessage = " This is an error log.";
        logger.logError(testMessage);
        assertTrue(baos.toString().contains(testMessage));
        assertTrue(baos.toString().contains("[ERROR]"));
    }


    @Test
    void testSerialization() throws IOException, ClassNotFoundException {
        LoggerSingleton logger1 = LoggerSingleton.getInstance();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE_PATH))) {
            out.writeObject(logger1);
        }
        Logger logger2;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SERIALIZED_FILE_PATH))) {
            logger2 = (Logger) in.readObject();
        }
        assertSame(logger1, logger2);
    }
}

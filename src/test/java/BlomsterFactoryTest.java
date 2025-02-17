import com.aman.factory.Example2.AndraBlommor;
import com.aman.factory.Example2.Blommor;
import com.aman.factory.Example2.BlomsterFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BlomsterFactoryTest {

    @InjectMocks
    private BlomsterFactory sut;

    @ParameterizedTest
    @CsvSource({
            "ROS,    ROS",
            "TULPAN, TULPAN",
            "ORKIDÉ, ORKIDÉ",
            "SOLROS, SOLROS",
            "LILJA,  LILJA",
            "OKAND,  OKAND"
    })
    void matchaBlomma(Blommor blommor, AndraBlommor andraBlommaStr) throws Exception {

        Method methodUnderTest = BlomsterFactory.class.getDeclaredMethod("matchaBlomma", Blommor.class);
        methodUnderTest.setAccessible(true);
        var rawResult = methodUnderTest.invoke(sut, blommor);

        if (rawResult instanceof Optional<?> optional) {
            assertInstanceOf(AndraBlommor.class, optional.orElse(null));
            assertEquals(andraBlommaStr, optional.orElse(null));
            assertEquals(Optional.of(andraBlommaStr), optional);
            assertEquals(Optional.of(andraBlommaStr), BlomsterFactory.getAndraBlomma(blommor));
        } else {
            assertEquals(andraBlommaStr, rawResult);
        }
    }

    @ParameterizedTest
    @CsvSource({
            "ROS,    ROS",
            "TULPAN, TULPAN",
            "ORKIDÉ, ORKIDÉ",
            "SOLROS, SOLROS",
            "LILJA,  LILJA",
            "OKAND,  OKAND"
    })
    void testHamtaBlommansNamn(String blommaStr, String expectedBlommansNamn) throws Exception {
        Blommor blommor = blommaStr.isEmpty() ? Blommor.ORKIDÉ : Blommor.valueOf(blommaStr);
        Method methodUnderTest = BlomsterFactory.class.getDeclaredMethod("hamtaBlommansNamn", Blommor.class);
        methodUnderTest.setAccessible(true);

        String actualResult = (String) methodUnderTest.invoke(sut, blommor);
        assertEquals(expectedBlommansNamn, actualResult);
        assertInstanceOf(String.class, actualResult);
        assertEquals(blommaStr, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "ROS,    Blommans namn: ROS",
            "TULPAN, Blommans namn: TULPAN",
            "OKAND,  Blommans namn: OKAND"
    })
    void skrivUtBlomma_test(Blommor blomma, String expectedOutput) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        BlomsterFactory.skrivUtBlomma(blomma);

        String actualOutput = outputStream.toString();
        assertEquals(expectedOutput, actualOutput.trim());
    }

    @Test
    void skrivUtBlomma_invalidValue_test() {
        assertThrows(IllegalArgumentException.class, () -> {
            Blommor blomma = Blommor.valueOf("OGILTIGT");
            BlomsterFactory.skrivUtBlomma(blomma);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "ROS, ROS",
            "TULPAN, TULPAN",
            "OKAND, OKAND"
    })
    void mockBlommorTest(String blommaStr, String expectedResult) {
        Blommor blommaMock = Mockito.mock(Blommor.class);
        Mockito.when(blommaMock.name()).thenReturn(blommaStr);
        String result = blommaMock.name();
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @CsvSource({
            "ROS, IllegalArgumentException",
            "TULPAN, NullPointerException"
    })
    void throwExceptionTest(String blommaStr, String expectedException) {
        Blommor blommaMock = Mockito.mock(Blommor.class);
        if (blommaStr.equals("ROS")) {
            Mockito.when(blommaMock.name()).thenThrow(new IllegalArgumentException("OGILTIGT"));
        } else {
            Mockito.when(blommaMock.name()).thenThrow(new NullPointerException("Null blomma"));
        }

        try {
            blommaMock.name();
            fail("fejla nu!!");
        } catch (Exception e) {
            assertEquals(expectedException.trim(), e.getClass().getSimpleName().trim());
        }
    }
}

/**
 * The class containing your tests for the {@link Demo} class.  Make sure you
 * test all methods in this class (including both 
 * {@link Demo#main(String[])} and 
 * {@link Demo#isTriangle(double, double, double)}).
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class DemoTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("5\n5\n5\n".getBytes());
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testZeroLengthValueSide(){
        assertFalse(Demo.isTriangle(0, 3, 4));
    }

    @Test
    public void testNegativeLengthValueSide(){
        assertFalse(Demo.isTriangle(9, -3, 4));
    }

    @Test
    public void testCollinearPoints(){
        assertFalse(Demo.isTriangle(3, 4, 7));
    }

    @Test
    public void testInEqualityViolation(){
        assertFalse(Demo.isTriangle(1, 4, 7));
    }

    @Test
    public void testEquilateralTriangle(){
        assertTrue(Demo.isTriangle(5, 5, 5));
    }

    @Test
    public void testIsoscelesTriangle(){
        assertTrue(Demo.isTriangle(5, 5, 8));
    }

    @Test
    public void testScaleneTriangle(){
        assertTrue(Demo.isTriangle(3, 4, 5));
    }

    @Test
    public void testMinimumValueTriangle(){
        assertTrue(Demo.isTriangle(1, 1, 1));
    }

    @Test
    public void testMaximumValueTriangle(){
        assertTrue(Demo.isTriangle(Integer.MAX_VALUE -1, Integer.MAX_VALUE -1, Integer.MAX_VALUE -2));
    }

    @Test
    public void testMainTriangle(){
        String[] value = {};
        Demo.main(value);

        String expectedResult = "Enter side 1: \r\n" +
                                "Enter side 2: \r\n" +
                                "Enter side 3: \r\n" +
                                "This is a triangle.\r\n";

        String actualOutput = outContent.toString();

        System.out.println("Actual Output:\n" + actualOutput);

        assertEquals(expectedResult, actualOutput);
    }

    @Test
    public void testMainNotATriangle(){
        ByteArrayInputStream inContentNotTriangle = new ByteArrayInputStream("1\n2\n3\n".getBytes());
        System.setIn(inContentNotTriangle);

        String[] value = {};
        Demo.main(value);

        String expectedResult = "Enter side 1: \r\n" +
                "Enter side 2: \r\n" +
                "Enter side 3: \r\n" +
                "This is not a triangle.\r\n";

        String actualOutput = outContent.toString();

        assertEquals(expectedResult, actualOutput);
    }

    // one of the values is 0
    // one of the values is -1
    // the sum of the two sides must be greater than the third side
    // all sides are equal
    // two sides are equal
    // all sides are different
    //
}

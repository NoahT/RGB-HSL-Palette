package rgb_hsl.color;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Unit testing class containing the necessary tests
 * for HexColor. All tests include elements strictly
 * in RGB space, with tests included for base 16 converisons.
 * @author Noah Teshima
 */
public class HexColorTest {
    private RGBColor colorOne;
    private HexColor colorTwo;

    /**
     * 2D array containing coordinates in RGB space.
     */
    private int[][] RGB_COLORS = {{0, 0, 0}, {255, 0, 0}, {0, 255, 0}, {0, 0, 255}, {120, 160, 200},
            {60, 120, 4}};

    /**
     * Array containing the associated hex color codes for RGB_COLORS
     */
    private String[] HEX_CODES = {"000000", "ff0000", "00ff00", "0000ff", "78a0c8", "3c7804"};

    /**
     * Array containing initial values for copy constructor unit test
     */
    private int[] COPY_INITIAL = {60, 120, 180},
    COPY_AFTER = {30, 60, 90};

    @Before
    public void init() {
        this.colorOne = new RGBColor();
        this.colorTwo = new HexColor();
    }

    /**
     * Unit test for determining whether the copy constructor
     * for HexColor creates a deep copy which is equal in value.
     */
    @Test
    public void testCopyConstructor() {
        this.colorOne = new RGBColor(COPY_INITIAL[0], COPY_INITIAL[1], COPY_INITIAL[2]);
        this.colorTwo = new HexColor(colorOne);

        /*
        This unit test is a near copy of the one in RGBColorTest. The only difference is
        to test the fact that any RGBColor declared type is allowed in the constructor
        invocation.
        */

        assertEquals("Hex colors made through copy constructors should " +
                "be equal in value!\n", this.colorOne, this.colorTwo);
        this.colorOne.setColor(COPY_AFTER[0], COPY_AFTER[1], COPY_AFTER[2]);
        assertEquals("Hex colors made through copy " +
                "constructors should not make shallow copies!\n", this.colorOne.equals(this.colorTwo), false);
    }

    /**
     * Unit test for determining whether the appropriate hex color code
     * is updated for each color.
     */
    @Test
    public void testHexCodes() {
        String err;
        int[] coords;
        for(int index = 0; index < RGB_COLORS.length; index++) {
            coords = RGB_COLORS[index];
            this.colorTwo.setColor(coords[0], coords[1], coords[2]);
            err = String.format("Expected hex code for %s is %s!\n", this.colorTwo, HEX_CODES[index]);
            assertEquals(err, this.colorTwo.getHexCode(), HEX_CODES[index]);
        }
    }

}

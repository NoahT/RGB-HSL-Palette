package rgb_hsl.color;


import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


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
     * Array containing parseable hex Strings for setting HexColor instances
     */
    private String[] PARSEABLE_CODES = {"000   ", " \n000000", "#fff\n", "#Ff0000\t", "0ea", "789aBc", "#bCa", "AFDB01", "69EFaa",
    "#ab3"};

    /**
     * Array containing hex Strings that are not parseable for HexColor instances.
     */
    private String[] UNPARSEABLE_CODES = {"0000", "#gaa", "asdf #123", "-1-2-3", "#aabbzz", "7017234", "##fff"};

    /**
     * 2D array containing the corresponding coordinates in RGB space for PARSEABLE_CODES
     */
    private int[][] PARSEABLE_RGB_COLORS = {{0, 0, 0}, {0, 0, 0}, {255, 255, 255}, {255, 0, 0}, {0, 238, 170},
            {120, 154, 188}, {187, 204, 170}, {175, 219, 1}, {105, 239, 170}, {170, 187, 51}};

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
     * Unit test for determining whether setting HexColor instances
     * by hex Strings is parsed correctly.
     */
    @Test
    public void testSetHexCode() {
        String err,
                code;
        int[] coords;
        for(int index = 0; index < PARSEABLE_CODES.length; index++) {
            code = PARSEABLE_CODES[index];
            this.colorTwo.setColor(code);
            coords = PARSEABLE_RGB_COLORS[index];

            err = String.format("Hex color code %s should have red value %d!\n", code, coords[0]);
            assertEquals(err, coords[0], this.colorTwo.getRed());
            err = String.format("Hex color code %s should have green value %d!\n", code, coords[1]);
            assertEquals(err, coords[1], this.colorTwo.getGreen());
            err = String.format("Hex color code %s should have blue value %d!\n", code, coords[2]);
            assertEquals(err, coords[2], this.colorTwo.getBlue());
        }
    }

    /**
     * Unit test for determining whether setting HexColor instances
     * by improperly formatted hex Strings is handled correctly.
     */
    @Test
    public void testUnparseableHexCode() {
        String err;
        for(String code : UNPARSEABLE_CODES) {
            try {
                err = String.format("Hex color code %s is not parseable!\n", code);
                this.colorTwo.setColor(code);
                fail(err);
            }catch(InputMismatchException exception) {
                //success if caught
            }
        }
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

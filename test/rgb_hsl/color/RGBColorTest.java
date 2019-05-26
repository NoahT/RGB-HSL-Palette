package rgb_hsl.color;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Unit testing class containing the necessary tests
 * for RGBColor. All tests include elements strictly
 * in RGB space.
 * @author Noah Teshima
 */
public class RGBColorTest {
    private RGBColor colorOne,
    colorTwo;

    /**
     * 2D array containing coordinates in RGB space.
     */
    private int[][] RGB_COLORS = {{0, 0, 0}, {255, 0, 0}, {0, 255, 0}, {0, 0, 255}, {120, 160, 200},
            {-60, -120, -260}, {60, 120, 260}};

    /**
     * Array containing initial values for copy constructor unit test
     */
    private int[] COPY_INITIAL = {60, 120, 180},
    COPY_AFTER = {30, 60, 90};

    /**
     * 2D array containing RGB boundary testing pairs.
     */
    private int[][] BOUNDSTEST = {{256, 0}, {257, 1}, {255, 255}, {-20, 0}, {0, 0}, {120, 120}};

    /**
     * 2D Array containing the expected HSL values from elements in RGB_COLORS
     */
    private double[][] HSLTEST = {{0, 0, 0}, {0, 1, .5}, {120, 1, .5}, {240, 1, .5}, {210, .42, .63},
            {0, 0, 0}, {90, .94, .24}};

    @Before
    public void init() {
        this.colorOne = new RGBColor();
        this.colorTwo = new RGBColor();
    }

    /**
     * Unit test for determining whether the copy constructor
     * for RGBColor creates a deep copy which is equal in value.
     */
    @Test
    public void testCopyConstructor() {
        this.colorOne = new RGBColor(COPY_INITIAL[0], COPY_INITIAL[1], COPY_INITIAL[2]);
        this.colorTwo = new RGBColor(colorOne);

        assertEquals("RGB colors made through copy constructors should " +
                "be equal in value!", this.colorOne, this.colorTwo);
        this.colorOne.setColor(COPY_AFTER[0], COPY_AFTER[1], COPY_AFTER[2]);
        assertEquals("RGB colors made through copy " +
                "constructors should not make shallow copies!", this.colorOne.equals(this.colorTwo), false);
    }

    /**
     * Unit test for testing the bounds on RGB elements.
     */
    @Test
    public void testRGBBounds() {
        for(int[] bounds : BOUNDSTEST) {
            this.colorOne.setRed(bounds[0]);
            this.colorOne.setGreen(bounds[0]);
            this.colorOne.setBlue(bounds[0]);

            assertEquals(this.colorOne.getRed(), bounds[1]);
            assertEquals(this.colorOne.getGreen(), bounds[1]);
            assertEquals(this.colorOne.getBlue(), bounds[1]);
        }
    }

    /**
     * Unit test for testing the conversion for hue in RGB space.
     */
    @Test
    public void testHue() {
        int[] coords;
        String err;
        for(int index = 0; index < RGB_COLORS.length; index++) {
            coords = RGB_COLORS[index];
            this.colorOne.setColor(coords[0], coords[1], coords[2]);
            err = String.format("%s does not have hue %.2f!", this.colorOne, HSLTEST[index][0]);
            assertEquals(err, HSLTEST[index][0], this.colorOne.getHSLColor().getHue(), 1);
        }
    }

    /**
     * Unit test for testing the conversion for saturation in RGB space.
     */
    @Test
    public void testSaturation() {
        int[] coords;
        String err;
        for(int index = 0; index < RGB_COLORS.length; index++) {
            coords = RGB_COLORS[index];
            this.colorOne.setColor(coords[0], coords[1], coords[2]);
            err = String.format("%s does not have saturation %.2f!", this.colorOne, HSLTEST[index][1]);
            assertEquals(err, HSLTEST[index][1], this.colorOne.getHSLColor().getSaturation(), 0.01);
        }
    }

    /**
     * Unit test for testing the conversion for lightness in RGB space.
     */
    @Test
    public void testLightness() {
        int[] coords;
        String err;
        for(int index = 0; index < RGB_COLORS.length; index++) {
            coords = RGB_COLORS[index];
            this.colorOne.setColor(coords[0], coords[1], coords[2]);
            err = String.format("%s does not have lightness %.2f!", this.colorOne, HSLTEST[index][2]);
            assertEquals(err, HSLTEST[index][2], this.colorOne.getHSLColor().getLightness(), 0.01);
        }
    }
}

package rgb_hsl.color;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Unit testing class containing the necessary tests
 * for HSLColor. All tests include elements strictly
 * in HSL space.
 * @author Noah Teshima
 */
public class HSLColorTest {
    private HSLColor colorOne,
    colorTwo;

    /**
     * 2D array containing coordinates in HSL space.
     */
    private double[][] HSL_COLORS = {{0, 0, 0}, {0, 1, .5}, {120, 1, .5}, {240, 1, .5}, {210, .42, .63},
            {90, .94, .24}};

    /**
     * Array containing initial values for copy constructor unit test
     */
    private int[] COPY_INITIAL = {60, 120, 180},
    COPY_AFTER = {30, 60, 90};

    /**
     * 2D array containing HSL hue boundary testing pairs.
     */
    private int[][] HUE_BOUNDS_TEST = {{-360, 360}, {-220, 140}, {-400, 320}, {-20, 340}, {0, 0}, {120, 120}, {400, 40}};

    /**
     * 2D array containing HSL saturation and lightness boundary testing pairs.
     */
    private double[][] SATURATION_LIGHTNESS_BOUNDS_TEST = {{-0.5, 0.0}, {1.5, 1.0}, {0.5, 0.5}};

    /**
     * 2D Array containing the expected RGB values from elements in HSL_COLORS
     */
    private int[][] RGBTEST = {{0, 0, 0}, {255, 0, 0}, {0, 255, 0}, {0, 0, 255}, {120, 160, 200},
            {60, 120, 4}};

    @Before
    public void init() {
        this.colorOne = new HSLColor();
        this.colorTwo = new HSLColor();
    }

    /**
     * Unit test for determining whether the copy constructor
     * for HSLColor creates a deep copy which is equal in value.
     */
    @Test
    public void testCopyConstructor() {
        this.colorOne = new HSLColor(COPY_INITIAL[0], COPY_INITIAL[1], COPY_INITIAL[2]);
        this.colorTwo = new HSLColor(colorOne);

        assertEquals("HSL colors made through copy constructors should " +
                "be equal in value!", this.colorOne, this.colorTwo);
        this.colorOne.setColor(COPY_AFTER[0], COPY_AFTER[1], COPY_AFTER[2]);
        assertEquals("HSL colors made through copy " +
                "constructors should not make shallow copies!", this.colorOne.equals(this.colorTwo), false);
    }

    /**
     * Unit test for testing the hue bounds on HSL elements.
     */
    @Test
    public void testHueBounds() {
        for(int[] bounds : HUE_BOUNDS_TEST) {
            this.colorOne.setHue(bounds[0]);

            assertEquals(bounds[1], this.colorOne.getHue());
        }
    }

    /**
     * Unit test for testing the saturation and lightness bounds on HSL elements.
     */
    @Test
    public void testSaturationLightnessBounds() {
        for(double[] bounds : SATURATION_LIGHTNESS_BOUNDS_TEST) {
            this.colorOne.setSaturation(bounds[0]);
            this.colorOne.setLightness(bounds[0]);

            assertEquals(bounds[1], this.colorOne.getSaturation(), 0.01);
            assertEquals(bounds[1], this.colorOne.getLightness(), 0.01);
        }
    }

    /**
     * Unit test for testing the conversion for red in HSL space.
     */
    @Test
    public void testRed() {
        double[] coords;
        String err;
        for(int index = 0; index < HSL_COLORS.length; index++) {
            coords = HSL_COLORS[index];
            this.colorOne.setColor(coords[0], coords[1], coords[2]);
            err = String.format("%s does not have red %d!", this.colorOne, RGBTEST[index][0]);
            assertEquals(err, RGBTEST[index][0], this.colorOne.getRGBColor().getRed(), 1);
        }
    }

    /**
     * Unit test for testing the conversion for green in HSL space.
     */
    @Test
    public void testGreen() {
        double[] coords;
        String err;
        for(int index = 0; index < HSL_COLORS.length; index++) {
            coords = HSL_COLORS[index];
            this.colorOne.setColor(coords[0], coords[1], coords[2]);
            err = String.format("%s does not have green %d!", this.colorOne, RGBTEST[index][1]);
            assertEquals(err, RGBTEST[index][1], this.colorOne.getRGBColor().getGreen(), 1);
        }
    }

    /**
     * Unit test for testing the conversion for blue in HSL space.
     */
    @Test
    public void testBlue() {
        double[] coords;
        String err;
        for(int index = 0; index < HSL_COLORS.length; index++) {
            coords = HSL_COLORS[index];
            this.colorOne.setColor(coords[0], coords[1], coords[2]);
            err = String.format("%s does not have blue %d!", this.colorOne, RGBTEST[index][2]);
            assertEquals(err, RGBTEST[index][2], this.colorOne.getRGBColor().getBlue(), 1);
        }
    }
}

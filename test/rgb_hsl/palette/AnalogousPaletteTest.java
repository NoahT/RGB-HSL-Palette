package rgb_hsl.palette;

import org.junit.Before;
import org.junit.Test;
import rgb_hsl.color.Color;
import rgb_hsl.color.RGBColor;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * Unit testing class containing the necessary
 * tests for AnalogousPalette.
 * @author Noah Teshima
 */
public class AnalogousPaletteTest {
    private Palette paletteOne;

    /**
     * Array containing coordinates in RGB space
     * with which an analogous palette is created.
     * For the purpose of these tests, the offset of
     * analogous colors is a maximal 120 degrees.
     */
    private int[][] RGB_INPUTS = {{0, 0, 0}, {255, 0, 0}, {120, 0, 40},
            {60, 120, 180}, {40, 100, 150}};

    /**
     * 2D array containing the results of first analogous colors
     */
    private int[][] ANALOGOUS_ONE = {{0, 0, 0}, {0, 255, 0}, {41, 122, 0},
            {180, 60, 120}, {149, 40, 100}};

    /**
     * 2D array containing the results of second analogous colors
     */
    private int[][] ANALOGOUS_TWO = {{0, 0, 0}, {0, 0, 255}, {0, 41, 122},
            {120, 180, 60}, {100, 149, 40}};

    @Before
    public void init() {
    }

    /**
     * Unit test designed to test the generation of analogous palettes..
     */
    @Test
    public void testAnalogousPalettes() {
        RGBColor analogousOne,
                analogousTwo;
        String err;
        int[] coords;
        int[] analCoordsOne,
                analCoordsTwo;
        for(int index = 0; index < RGB_INPUTS.length; index++) {
            coords = RGB_INPUTS[index];
            analCoordsOne = ANALOGOUS_ONE[index];
            analCoordsTwo = ANALOGOUS_TWO[index];
            this.paletteOne = new AnalogousPalette(new RGBColor(coords[0], coords[1], coords[2]), 120);

            analogousOne = Color.getRGBColor(this.paletteOne.getColor(1));
            analogousTwo = Color.getRGBColor(this.paletteOne.getColor(2));

            err = String.format("Analogous color %s is not %s!!\n",
                    analogousOne, Arrays.toString(analCoordsOne));
            assertEquals(err, analCoordsOne[0], analogousOne.getRed(), 2);
            assertEquals(err, analCoordsOne[1], analogousOne.getGreen(), 2);
            assertEquals(err, analCoordsOne[2], analogousOne.getBlue(), 2);

            err = String.format("Analogous color %s is not %s!!\n",
                    analogousTwo, Arrays.toString(analCoordsTwo));
            assertEquals(err, analCoordsTwo[0], analogousTwo.getRed(), 2);
            assertEquals(err, analCoordsTwo[1], analogousTwo.getGreen(), 2);
            assertEquals(err, analCoordsTwo[2], analogousTwo.getBlue(), 2);
        }
    }
}

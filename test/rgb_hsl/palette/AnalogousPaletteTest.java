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
    private int[][] RGB_INPUTS = {
            {0, 0, 0},
            {255, 0, 0},
            {120, 0, 40},
            {60, 120, 180},
            {40, 100, 150}};

    /**
     * 3D array containing the results of analogous palettes
     */
    private int[][][] RGB_RESULTS = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{120, 0, 40}, {41, 122, 0}, {0, 41, 122}},
            {{60, 120, 180}, {180, 60, 120}, {120, 180, 60}},
            {{40, 100, 150}, {149, 40, 100}, {100, 149, 40}}
    };

    @Before
    public void init() {
    }

    /**
     * Unit test designed to test the generation of analogous palettes. For the purpose of this
     * test, offset of each color generated is a maximal 120 degrees.
     */
    @Test
    public void testAnalogousPalettes() {
        RGBColor analogous;
        String err;
        int[] coords;
        int[] analCoords;
        for(int index = 0; index < RGB_INPUTS.length; index++) {
            coords = RGB_INPUTS[index];

            this.paletteOne = new AnalogousPalette(new RGBColor(coords[0], coords[1], coords[2]), 120);

            for(int index2 = 0; index2 < this.paletteOne.getColors().size(); index2++) {
                analogous = Color.getRGBColor(this.paletteOne.getColor(index2));
                analCoords = RGB_RESULTS[index][index2];

                err = String.format("Analogous color %s was given instead of %s!\n",
                        analogous, Arrays.toString(analCoords));
                assertEquals(err, analCoords[0], analogous.getRed(), 2);
                assertEquals(err, analCoords[1], analogous.getGreen(), 2);
                assertEquals(err, analCoords[2], analogous.getBlue(), 2);
            }
        }
    }
}

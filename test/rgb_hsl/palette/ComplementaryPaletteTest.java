package rgb_hsl.palette;

import org.junit.Before;
import org.junit.Test;
import rgb_hsl.color.Color;
import rgb_hsl.color.RGBColor;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * Unit testing class containing the necessary
 * tests for ComplementaryPalette.
 * @author Noah Teshima
 */
public class ComplementaryPaletteTest {
    private Palette paletteOne;

    /**
     * Array containing coordinates in RGB space
     * with which a complementary palette is created.
     */
    private int[][] RGB_INPUTS = {{0, 0, 0}, {255, 0, 0}, {0, 255, 0}, {0, 0, 255}, {120, 0, 40},
            {60, 120, 180}, {40, 100, 150}};

    /**
     * 2D array containing the results of creating complementary palettes
     */
    private int[][] RGB_RESULTS = {{0, 0, 0}, {0, 255, 255}, {255, 0, 255}, {255, 255, 0}, {0 , 120, 80},
            {180, 120, 60}, {150, 90, 40}};

    @Before
    public void init() {
    }

    /**
     * Unit test designed to test the generation of complementary palettes..
     */
    @Test
    public void testComplementaryPalettes() {
        RGBColor complementary;
        String err;
        int[] coords;
        int[] compCoords;
        for(int index = 0; index < RGB_INPUTS.length; index++) {
            coords = RGB_INPUTS[index];
            compCoords = RGB_RESULTS[index];
            this.paletteOne = new ComplementaryPalette(new RGBColor(coords[0], coords[1], coords[2]));

            for(Color color : this.paletteOne.getColors()) {
                if(color.equals(this.paletteOne.getStartingColor())) {
                    continue;
                }
                complementary = Color.getRGBColor(color);
                err = String.format("Complementary color %s was given instead of %s!\n",
                        complementary, Arrays.toString(compCoords));
                assertEquals(err, compCoords[0], complementary.getRed(), 2);
                assertEquals(err, compCoords[1], complementary.getGreen(), 2);
                assertEquals(err, compCoords[2], complementary.getBlue(), 2);
            }
        }
    }
}

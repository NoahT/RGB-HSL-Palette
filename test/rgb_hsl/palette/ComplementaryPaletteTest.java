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
    private int[][] RGB_INPUTS = {
            {0, 0, 0},
            {255, 0, 0},
            {0, 255, 0},
            {0, 0, 255},
            {120, 120, 0},
            {120, 0, 120},
            {0, 120, 120}};

    /**
     * 3D array containing the results of creating complementary palettes
     */
    private int[][][] RGB_RESULTS = {
            {{0, 0, 0}, {0, 0, 0}},
            {{255, 0, 0}, {0, 255, 255}},
            {{0, 255, 0}, {255, 0, 255}},
            {{0, 0, 255}, {255, 255, 0}},
            {{120, 120, 0}, {0, 0, 122}},
            {{120, 0, 120}, {0, 122, 0}},
            {{0, 120, 120}, {122, 0, 0}}};

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

            this.paletteOne = new ComplementaryPalette(new RGBColor(coords[0], coords[1], coords[2]));

            for(int index2 = 0; index2 < this.paletteOne.getColors().size(); index2++) {
                complementary = Color.getRGBColor(this.paletteOne.getColor(index2));
                compCoords = RGB_RESULTS[index][index2];

                err = String.format("Monochromatic color %s was given instead of %s!\n",
                        complementary, Arrays.toString(compCoords));
                assertEquals(err, compCoords[0], complementary.getRed(), 2);
                assertEquals(err, compCoords[1], complementary.getGreen(), 2);
                assertEquals(err, compCoords[2], complementary.getBlue(), 2);
            }
        }
    }
}

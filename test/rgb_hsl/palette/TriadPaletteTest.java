package rgb_hsl.palette;

import org.junit.Before;
import org.junit.Test;
import rgb_hsl.color.Color;
import rgb_hsl.color.RGBColor;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * Unit testing class containing the necessary
 * tests for TriadPalette.
 * @author Noah Teshima
 */
public class TriadPaletteTest {
    private Palette paletteOne;

    /**
     * Array containing coordinates in RGB space
     * with which a triadic palette is created.
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
     * 3D array containing the results of triadic palettes
     */
    private int[][][] RGB_RESULTS = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}},
            {{0, 255, 0}, {0, 0, 255}, {255, 0, 0}},
            {{0, 0, 255}, {255, 0, 0}, {0, 255, 0}},
            {{120, 120, 0}, {0, 122, 122}, {122, 0, 122}},
            {{120, 0, 120}, {122, 122, 0}, {0, 122, 122}},
            {{0, 120, 120}, {122, 0, 122}, {122, 122, 0}}
    };

    @Before
    public void init() {
    }

    /**
     * Unit test designed to test the generation of triadic palettes.
     */
    @Test
    public void testTriadPalettes() {
        RGBColor triad;
        String err;
        int[] coords;
        int[] triadCoords;
        for(int index = 0; index < RGB_INPUTS.length; index++) {
            coords = RGB_INPUTS[index];

            this.paletteOne = new AnalogousPalette(new RGBColor(coords[0], coords[1], coords[2]), 120);

            for(int index2 = 0; index2 < this.paletteOne.getColors().size(); index2++) {
                triad = Color.getRGBColor(this.paletteOne.getColor(index2));
                triadCoords = RGB_RESULTS[index][index2];

                err = String.format("Triadic color %s was given instead of %s!\n",
                        triad, Arrays.toString(triadCoords));
                assertEquals(err, triadCoords[0], triad.getRed(), 2);
                assertEquals(err, triadCoords[1], triad.getGreen(), 2);
                assertEquals(err, triadCoords[2], triad.getBlue(), 2);
            }
        }
    }
}

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
     */
    private int[][] RGB_INPUTS = {
            {0, 0, 0},
            {255, 0, 0},
            {0, 255, 0},
            {0, 0, 255},
            {120, 120, 0},
            {120, 0, 120},
            {0, 120, 120}
    };

    /**
     * 3D array containing the results of analogous palettes, with
     * offset adjusted to 0 degrees.
     */
    private int[][][] RGB_RESULTS_ZERO = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{255, 0, 0}, {255, 0, 0}, {255, 0, 0}},
            {{0, 255, 0}, {0, 255, 0}, {0, 255, 0}},
            {{0, 0, 255}, {0, 0, 255}, {0, 0, 255}},
            {{120, 120, 0}, {120, 120, 0}, {120, 120, 0}},
            {{120, 0, 120}, {120, 0, 120}, {120, 0, 120}},
            {{0, 120, 120}, {0, 120, 120}, {0, 120, 120}}
    };

    /**
     * 3D array containing the results of analogous palettes, with
     * offset adjusted to 30 degrees.
     */
    private int[][][] RGB_RESULTS_THIRTY = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{255, 0, 0}, {255, 128, 0}, {255, 0, 128}},
            {{0, 255, 0}, {0, 255, 128}, {128, 255, 0}},
            {{0, 0, 255}, {127, 0, 255}, {0, 127, 255}},
            {{120, 120, 0}, {61, 122, 0}, {122, 61, 0}},
            {{120, 0, 120}, {122, 0, 61}, {61, 0, 122}},
            {{0, 120, 120}, {0, 61, 122}, {0, 122, 61}}
    };

    /**
     * 3D array containing the results of analogous palettes, with
     * offset adjusted to 60 degrees.
     */
    private int[][][] RGB_RESULTS_SIXTY = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{255, 0, 0}, {255, 255, 0}, {255, 0, 255}},
            {{0, 255, 0}, {0, 255, 255}, {255, 255, 0}},
            {{0, 0, 255}, {255, 0, 255}, {0, 255, 255}},
            {{120, 120, 0}, {0, 122, 0}, {122, 0, 0}},
            {{120, 0, 120}, {122, 0, 0}, {0, 0, 122}},
            {{0, 120, 120}, {0, 0, 122}, {0, 122, 0}}
    };

    /**
     * 3D array containing the results of analogous palettes, with
     * offset adjusted to 90 degrees.
     */
    private int[][][] RGB_RESULTS_NINETY = {
            {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
            {{255, 0, 0}, {128, 255, 0}, {127, 0, 255}},
            {{0, 255, 0}, {0, 127, 255}, {255, 128, 0}},
            {{0, 0, 255}, {255, 0, 128}, {0, 255, 128}},
            {{120, 120, 0}, {0, 122, 61}, {122, 0, 61}},
            {{120, 0, 120}, {122, 61, 0}, {0, 61, 122}},
            {{0, 120, 120}, {61, 0, 122}, {61, 122, 0}}
    };

    @Before
    public void init() {
    }

    /**
     * Unit test designed to test the generation of analogous palettes with an offset
     * of zero degrees.
     */
    @Test
    public void testAnalogousPalettesZero() {
        this.runAnalogousPalette(RGB_RESULTS_ZERO, 0);
    }

    /**
     * Unit test designed to test the generation of analogous palettes with an offset
     * of thirty degrees.
     */
    @Test
    public void testAnalogousPalettesThirty() {
        this.runAnalogousPalette(RGB_RESULTS_THIRTY, 30);
    }

    /**
     * Unit test designed to test the generation of analogous palettes with an offset
     * of sixty degrees.
     */
    @Test
    public void testAnalogousPalettesSixty() {
        this.runAnalogousPalette(RGB_RESULTS_SIXTY, 60);
    }

    /**
     * Unit test designed to test the generation of analogous palettes with an offset
     * of ninety degrees.
     */
    @Test
    public void testAnalogousPalettesNinety() {
        this.runAnalogousPalette(RGB_RESULTS_NINETY, 90);
    }

    /**
     * Helper method used to test analogous palettes.
     * @param RGB_RESULTS 3D array containing expected output values
     * @param offset int value containing the offset in degrees
     */
    private void runAnalogousPalette(int[][][] RGB_RESULTS, int offset) {
        RGBColor analogous;
        String err;
        int[] coords;
        int[] analCoords;
        for(int index = 0; index < RGB_INPUTS.length; index++) {
            coords = RGB_INPUTS[index];

            this.paletteOne = new AnalogousPalette(new RGBColor(coords[0], coords[1], coords[2]), offset);

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

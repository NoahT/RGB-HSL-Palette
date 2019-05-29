package rgb_hsl.palette;

import org.junit.Before;
import org.junit.Test;
import rgb_hsl.color.Color;
import rgb_hsl.color.RGBColor;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * Unit testing class containing the necessary
 * tests for MonoChromaticPalette.
 * @author Noah Teshima
 */
public class MonoChromaticPaletteTest {
    private Palette paletteOne;

    /**
     * Array containing coordinates in RGB space
     * with which a monochromatic palette is created.
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
     * 3D array containing the results of creating monochromatic palettes with
     * one total color.
     */
    private int[][][] RGB_RESULTS_ONE = {
            {{0, 0, 0}},
            {{255, 0, 0}},
            {{0, 255, 0}},
            {{0, 0, 255}},
            {{120, 120, 0}},
            {{120, 0, 120}},
            {{0, 120, 120}}
    };

    /**
     * 3D array containing the results of creating monochromatic palettes with
     * two total colors.
     */
    private int[][][] RGB_RESULTS_TWO = {
            {{0, 0, 0}, {128, 128, 128}},
            {{255, 0, 0}, {0, 0, 0}},
            {{0, 255, 0}, {0, 0, 0}},
            {{0, 0, 255}, {0, 0, 0}},
            {{120, 120, 0}, {255, 255, 122}},
            {{120, 0, 120}, {255, 122, 255}},
            {{0, 120, 120}, {122, 255, 255}}
    };

    /**
     * 3D array containing the results of creating monochromatic palettes with
     * three total colors.
     */
    private int[][][] RGB_RESULTS_THREE = {
            {{0, 0, 0}, {84, 84, 84}, {168, 168, 168}},
            {{255, 0, 0}, {255, 170, 170}, {87, 0, 0}},
            {{0, 255, 0}, {170, 255, 170}, {0, 87, 0}},
            {{0, 0, 255}, {170, 170, 255}, {0, 0, 87}},
            {{120, 120, 0}, {255, 255, 36}, {255, 255, 204}},
            {{120, 0, 120}, {255, 36, 255}, {255, 204, 255}},
            {{0, 120, 120}, {36, 255, 255}, {204, 255, 255}}
    };

    @Before
    public void init() {
    }

    /**
     * Unit test designed to test the generation of monochromatic palettes
     * with one total color.
     */
    @Test
    public void testMonochromaticPalettesOne() {
        this.runAnalogousPalette(RGB_RESULTS_ONE, 1);
    }

    /**
     * Unit test designed to test the generation of monochromatic palettes
     * with two total colors.
     */
    @Test
    public void testMonochromaticPalettesTwo() {
        this.runAnalogousPalette(RGB_RESULTS_TWO, 2);
    }

    /**
     * Unit test designed to test the generation of monochromatic palettes
     * with three total colors.
     */
    @Test
    public void testMonochromaticPalettesThree() {
        this.runAnalogousPalette(RGB_RESULTS_THREE, 3);
    }

    /**
     * Helper method used to test monochromatic palettes.
     * @param RGB_RESULTS 3D array containing expected output values
     * @param amount int value containing the number of monochromatic
     *               colors to generate.
     */
    private void runAnalogousPalette(int[][][] RGB_RESULTS, int amount) {
        RGBColor monochromatic;
        String err;
        int[] coords;
        int[] monoCoords;
        for(int index = 0; index < RGB_INPUTS.length; index++) {
            coords = RGB_INPUTS[index];

            this.paletteOne = new MonoChromaticPalette(new RGBColor(coords[0], coords[1], coords[2]), amount);

            for(int index2 = 0; index2 < this.paletteOne.getColors().size(); index2++) {
                monochromatic = Color.getRGBColor(this.paletteOne.getColor(index2));
                monoCoords = RGB_RESULTS[index][index2];

                err = String.format("Monochromatic color %s was given instead of %s!\n",
                        monochromatic, Arrays.toString(monoCoords));
                assertEquals(err, monoCoords[0], monochromatic.getRed(), 2);
                assertEquals(err, monoCoords[1], monochromatic.getGreen(), 2);
                assertEquals(err, monoCoords[2], monochromatic.getBlue(), 2);
            }
        }
    }
}

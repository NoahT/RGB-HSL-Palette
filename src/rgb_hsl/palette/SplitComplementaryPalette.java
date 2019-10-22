package rgb_hsl.palette;

import rgb_hsl.color.Color;
import rgb_hsl.color.HSLColor;

/**
 * Palette subclass used to generate a split complementary
 * color palette. Split complementary color palettes take a
 * color and generates two accentuating colors offset
 * from its complement.
 * @author Noah Teshima
 */
public class SplitComplementaryPalette extends Palette {
    private int offset;

    /**
     * Default constructor. When invoked, a split complementary
     * color palette is generated using an offset of 60 degrees.
     */
    public SplitComplementaryPalette() {
        this(new HSLColor(0, 0, 0), 0);
    }
    /**
     * Consturctor used to set the base color and offset of accentuating colors.
     * @param offset integer value containing the offset from the complementary
     *               color.
     */
    public SplitComplementaryPalette(Color color, int offset) {
        super(color);
        this.setOffset(offset);
        this.generate();
    }

    /**
     * Mutator method designed to set the offset for accentuating colors.
     * @param offset
     */
    private void setOffset(int offset) {
        this.offset = (offset >= 0) ? offset : 0;
    }

    @Override
    protected void generate() {
        HSLColor accentuatingColorOne = Color.getHSLColor(super.getStartingColor()),
                accentuatingColorTwo = Color.getHSLColor(super.getStartingColor());
        accentuatingColorOne.incrementHue(this.offset);
        accentuatingColorTwo.incrementHue(this.offset);

        super.addColor(accentuatingColorOne);
        super.addColor(accentuatingColorTwo);
    }
}

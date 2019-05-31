package rgb_hsl.palette;

import rgb_hsl.color.HSLColor;
import rgb_hsl.color.Color;

/**
 * Palette subclass used to generate a analogous
 * color palette. An analogous color
 * palette takes any color and uses
 * colors with hues that are similar.
 * @author Noah Teshima
 * @since 1.0.0
 */
public class AnalogousPalette extends Palette {
	private int offset;

	/**
	 * Set the base color and the offset of adjacent
	 * colors for the analogous color palette.
	 * @param color Color reference containing the
	 *                 base color to use.
	 * @param offset integer value containing the offset
	 *                  of the two adjacent Colors in the palette
	 */
	public AnalogousPalette(Color color, int offset) {
		super(color);
		this.setOffset(offset);
		this.generate();
	}

	/**
	 * Set the offset of adjacent colors. If the offset
	 * is greater than 120 degrees, then 120 is set as
	 * the maximum.
	 * @param offset int value containing the hue offset of each
	 *               analogous color.
	 */
	private void setOffset(int offset) {
		if(offset < 0) {
			offset *= -1;
		}
		this.offset = (offset > 120) ? 120 : offset;
	}

	@Override
	protected void generate() {
		HSLColor analogousOne = new HSLColor(Color.getHSLColor(super.getStartingColor())),
				analogousTwo = new HSLColor(Color.getHSLColor(super.getStartingColor()));
		analogousOne.incrementHue(this.offset);
		analogousTwo.incrementHue(-this.offset);

		super.addColor(analogousOne);
		super.addColor(analogousTwo);
	}

}

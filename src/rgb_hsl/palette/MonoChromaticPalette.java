package rgb_hsl.palette;

import rgb_hsl.color.Color;
import rgb_hsl.color.HSLColor;

/**
 * Palette sublass used to generate a monochromatic
 * color palette. A monochromatic color
 * palette takes any color and uses
 * colors with lighter or darker shades.
 * @author Noah Teshima
 *
 */
public class MonoChromaticPalette extends Palette {
	private int amount;

	/**
	 * Default constructor. When invoked, the instance's
	 * list of colors is instantiated and adds the given
	 * color.
	 * @param color Color reference used to create a new
	 * color palette
	 */
	public MonoChromaticPalette(Color color) {
		this(color, 5);
	}

	/**
	 * Constructor used to set a monochromatic color palette
	 * given the number of values and offset of each value.
	 * @param color Color reference used to create a new color
	 * palette
	 * @param amount integer value containing the number of values
	 * to use
	 */
	public MonoChromaticPalette(Color color, int amount) {
		super(color);
		this.setAmount(amount);
		this.generate();
	}

	/**
	 * Private mutator method designed to set
	 * the specified amount of colors to add.
	 * @param amount integer value containing
	 * the amount of values to add.
	 */
	private void setAmount(int amount) {
		this.amount = (amount < 0) ? 0 : amount;
	}

	@Override
	protected void generate() {
	    HSLColor startingHSL = Color.getHSLColor(super.getStartingColor());
		double currentLightness = startingHSL.getLightness();
		HSLColor currentColor,
				tempHSL;
		//create all other aside from base color
		for(int index = 0; index < (this.amount - 1); index++) {
			currentLightness += ((double) 1 / (this.amount));
			//make sure lightness is in range of 0 to 1, inclusive
			currentLightness %= 1;

			tempHSL = Color.getHSLColor(super.getColor(index));
			int hue = tempHSL.getHue();
			double saturation = tempHSL.getSaturation();
			currentColor = new HSLColor(hue, saturation, currentLightness);
			super.addColor(currentColor);
		}
	}

}

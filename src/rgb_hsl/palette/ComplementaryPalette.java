package rgb_hsl.palette;

import rgb_hsl.color.Color;
import rgb_hsl.color.HSLColor;

/**
 * Palette subclass used to generate a complementary
 * color palette. A complementary color
 * palette takes any color and generates another
 * color with an opposite hue.
 * @author Noah Teshima
 *
 */
public class ComplementaryPalette extends Palette {
	/**
	 * Default constructor. When invoked, a complementary
	 * palette is created with respect to the origin in
	 * HSL space.
	 */
	public ComplementaryPalette() {
		super();
		this.generate();
	}

	/**
	 * Create a complementary palette based off the given
	 * Color reference. When invoked, the instance's list
	 * of colors is instantiated and adds the given color.
	 * @param color Color reference used to create a new
	 * color palette
	 */
	public ComplementaryPalette(Color color) {
		super(color);
		this.generate();
	}

	/**
	 * Get a Color object with the opposite hue of the
	 * starting color. Opposite hue is given as the maximal
	 * distance in HSL space between the starting color and
	 * any color with the same saturation and lightness. In
	 * other words, the complementary color is given by the color
	 * with a difference of 180 degrees in hue, while retaining
	 * the same saturation and lightness.
	 * @return Color object containing the complementary
	 * Color of the starting color
	 */
	private Color getComplementaryColor() {
	    HSLColor startingHSL = Color.getHSLColor(super.getStartingColor());
		int hue = startingHSL.getHue();
		double saturation = startingHSL.getSaturation(),
				lightness = startingHSL.getLightness();

		HSLColor complementary = new HSLColor(hue, saturation, lightness);
		complementary.incrementHue(180);

		return complementary;
	}

	@Override
	protected void generate() {
		super.addColor(this.getComplementaryColor());
	}

}

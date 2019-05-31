package rgb_hsl.palette;

import rgb_hsl.color.Color;

/**
 * Palette subclass used to generate a triad
 * color palette. A triad color
 * palette takes the two colors that are
 * equidistant from a base color's hue, where
 * each color is a maximal 120 degrees away from
 * each other in hue.
 * @author Noah Teshima
 * @since 1.0.0
 */
public class TriadPalette extends AnalogousPalette {
	/**
	 * Default constructor. Used to set the
	 * base color of the triad palette.
	 * @param color Color reference containing the
	 *              starting color with which a triadic
	 *              palette should be generated.
	 */
	public TriadPalette(Color color) {
		super(color, 120);
	}
}

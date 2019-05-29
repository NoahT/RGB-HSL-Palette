package rgb_hsl.palette;

import rgb_hsl.color.Color;

/**
 * Palette sublass used to generate a triad
 * color palette. A triad color
 * palette takes the two colors that are
 * equidistant from a base color's hue.
 * @author Noah Teshima
 *
 */
public class TriadPalette extends AnalogousPalette {
	/**
	 * Default constructor. Used to set the
	 * base color of the triad palette.
	 */
	public TriadPalette(Color color) {
		super(color, 120);
	}
}

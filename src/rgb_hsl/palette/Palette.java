package rgb_hsl.palette;

import rgb_hsl.color.Color;
import rgb_hsl.color.HSLColor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract superclass used as a base class for
 * creating different types of Palettes.
 * @author Noah Teshima
 *
 */
public abstract class Palette {
	private List<Color> colors;
	private Color startingColor;

	/**
	 * Default constructor. When invoked,
	 * the color palette is instantiated
	 * with respect to the origin in HSL
	 * space.
	 */
	public Palette() {
		this(new HSLColor(0, 0, 0));
	}

	/**
	 * Default constructor. When invoked, the
	 * instance's ArrayList containing Colors
	 * is instantiated.
	 * @param color Color reference containing the
	 *              color with respect to which the
	 *              palette should generate.
	 */
	public Palette(Color color) {
		this.colors = new ArrayList<>();
		this.colors.add(color);
		this.startingColor = color;
	}

	/**
	 * Method stub designed to generate any color
	 * palette given the starting color and any other
	 * necessary information.
	 */
	protected abstract void generate();

	/**
	 * File output method designed to write the
	 * given colors out to the specified file in HSL space.
	 * @param file String reference containing
	 * the name of the file to write to. If the
	 * file already exists, it will be overwritten.
	 * @throws FileNotFoundException if the given
	 * file is a directory, preventing the
	 * ability to be overwritten.
	 */
	public void writeToFile(String file) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new FileOutputStream(file));
		
		for(Color color: this.colors) {
			writer.write(Color.getHSLColor(color) + "\n");
		}
		
		writer.close();
	}

	/**
	 * Accessor method used to get a shallow
	 * copy of all the colors in the current
	 * palette.
	 * @return List of basetype Color
	 * containing all of the colors in the
	 * current palette.
	 */
	public List<Color> getColors() {
		return this.colors;
	}

	/**
	 * Accessor method used to get the starting
	 * color of the palette.
	 * @return Color object that the palette is
	 * based around.
	 */
	public Color getStartingColor() {
		return this.startingColor;
	}

	/**
	 * Accessor method used to get a shallow copy
	 * of the Color at the specified index.
	 * @param index integer value containing the
	 * Color object to return
	 * @return Color object corresponding to the
	 * specified index.
	 */
	public Color getColor(int index) {
		return this.colors.get(index);
	}

	/**
	 * Accessor method used to get the number of
	 * Colors being used in the palette.
	 * @return integer value containing the number
	 * of Colors being used in the palette.
	 */
	public int getSize() {
		return this.colors.size();
	}

	/**
	 * Mutator method designed to add a new Color
	 * reference to the instance's list of Colors.
	 * @param color Color reference to add to the
	 * instance's list of Colors
	 */
	protected void addColor(Color color) {
		this.colors.add(color);
	}

	/**
	 * Overridden equals(..) method from Object
	 * class. Used to check whether the current
	 * instance and the given reference have the
	 * same palette in the same order of colors.
	 * @param obj Object reference with actual
	 * type Palette
	 * @return boolean value determining if the
	 * current instance and the given reference
	 * have the same palette in the same order
	 * of colors.
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Palette)) {
			return false;
		}
		Palette paletteObj = (Palette) obj;

		if(paletteObj.getColors().size() != this.colors.size()) {
			return false;
		}

		for(int index = 0; index < paletteObj.getColors().size(); index++) {
			if(!(this.colors.get(index).equals(paletteObj.getColors().get(index)))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Overridden toString() method from Object class. When
	 * invoked, information about each color being used
	 * and the quantity is returned as a String.
	 * @return String object containing information about
	 * the colors being used.
	 */
	@Override
	public String toString() {
		return String.format("%s\n"
				+ "Total colors: %d.",
				this.colors,
				this.getSize());
	}

}

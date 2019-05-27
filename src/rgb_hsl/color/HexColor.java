package rgb_hsl.color;

/**
 * Subclass of abstract class RGBColor.
 * Uses integer values for red, blue and green
 * and compensates for any remainder. Hex color codes
 * are given in the following form
 * #rrggbb
 * where rr, gg, and bb represent hexadecimal values on the
 * range of [00-ff], for the amount of red, green, and blue,
 * respectively.
 * @author Noah Teshima
 */
public class HexColor extends RGBColor {
	private String hexCode;
	
	/**
	 * Default constructor. When invoked, a default
	 * hex color code of #000 is created.
	 */
	public HexColor() {
		super();
		this.updateHexCode();
	}

	/**
	 * Copy constructor used to create a HexColor with the same
	 * values as the given reference.
	 * @param color RGBColor reference containing the RGB values to
	 * copy.
	 */
	public HexColor(RGBColor color) {
		this(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	/**
	 * Constructor used to set the hex color given
	 * the amount of red, green, and blue for the
	 * current instance.
	 * @param red integer value containing the amount
	 * of red
	 * @param green integer value containing the amount
	 * of green
	 * @param blue integer value containing the amount
	 * of blue
	 */
	public HexColor(int red, int green, int blue) {
		super(red, green, blue);
		this.updateHexCode();
	}


	/**
	 * Private mutator method designed to set the
	 * instance's hexCode to its corresponding
	 * color. Uses hexadecimal format in order
	 * to properly convert the amount of red,
	 * blue, and green for the current color.
	 */
	private void updateHexCode() {
		String redHex = Integer.toHexString(this.getRed()),
				greenHex = Integer.toHexString(this.getGreen()),
				blueHex = Integer.toHexString(this.getBlue());

		//always make sure the hex code is formatted with two characters.
		//there will only be one chacter in the case that any value is 0-15
		//inclusive
		redHex = (redHex.length() == 1) ? "0" + redHex : redHex;
		greenHex = (greenHex.length() == 1) ? "0" + greenHex : greenHex;
		blueHex = (blueHex.length() == 1) ? "0" + blueHex : blueHex;

		this.hexCode = String.format("%s%s%s",
				redHex,
				greenHex,
				blueHex);
	}

	/**
	 * Accessor method used to get the hex color ocde
	 * associated with the current instance.
	 * @return String object containing the current hex color
	 * cde.
	 */
	public String getHexCode() {
		return this.hexCode;
	}

	@Override
	public void setColor(double red, double green, double blue) {
		super.setColor(red, green, blue);
		this.updateHexCode();
	}

	@Override
	public void setRed(int red) {
		super.setRed(red);
		this.updateHexCode();
	}

	@Override
	public void setGreen(int green) {
		super.setGreen(green);
		this.updateHexCode();
	}

	@Override
	public void setBlue(int blue) {
		super.setBlue(blue);
		this.updateHexCode();
	}
	
	@Override
	public String toString() {
		return String.format("#%s",
				this.hexCode);
	}
}

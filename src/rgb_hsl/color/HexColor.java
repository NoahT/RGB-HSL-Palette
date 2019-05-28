package rgb_hsl.color;

import jdk.internal.util.xml.impl.Input;

import java.util.InputMismatchException;

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
	 * Inner class designed to parse Hex Strings.
	 * @author Noah Teshima
	 */
	private static class HexParser {
		/**
		 * Regular expression pattern used to determine
		 * if a String is a valid hex color code. In general, a
		 * String is a valid hex color code iff it follows any of
		 * the following forms:
		 * #rgb, rgb, #rrggbb, rrggbb
		 * for r,g,b represent arbitrary digits or letters a-f.
		 */
		private static String PATTERN_ONE = "^[\\s]*[#]?([\\da-fA-F]{3}|[\\da-fA-F]{6})[\\s]*$";

		/**
		 * Helper method used to remove unnecessary characters from the
		 * given hex code.
		 * @param hexString String reference containing the hex color code.
		 * @return String object containing the hex color code with unnecessary
		 * characters removed.
		 * @precondition hexString is a valid hex color code.
		 */
		private String strip(String hexString) {
			hexString = hexString.replaceAll("\\s", "").toLowerCase();
			return (hexString.indexOf("#") != -1) ? hexString.substring(1) : hexString;
		}

		/**
		 * Accessor method used to determine whether the given String
		 * is a parseable hex color code.
		 * @param str String reference containing a potential hex color
		 *            code.
		 * @return boolean value determining whether the given reference is
		 * in a parseable format for a hex color code.
		 */
		public boolean isParseable(String str) {
			return str.matches(PATTERN_ONE);
		}

		/**
		 * Accessor method used to get the amount of red
		 * in the given hex color code in base ten.
		 * @param hexString String reference containing the hex color code.
		 * @return int value containing the amount of red in base ten.
		 */
		public int getRedBaseTen(String hexString) throws InputMismatchException {
			if(!this.isParseable(hexString)) {
				throw new InputMismatchException();
			}

			return this.getBaseTen(this.getRed(hexString));
		}

		/**
		 * Accessor method used to get the hex code for the
		 * amount of red.
		 * @param hexString String reference containing the hex color code.
		 * @return int value containing the amount of red in base 16.
		 * @precondition hexString is a valid hex color code.
		 */
		private String getRed(String hexString) {
			String strippedString = this.strip(hexString);
			return (strippedString.length() == 6) ? strippedString.substring(0, 2) : strippedString.substring(0, 1);
		}

		/**
		 * Accessor method used to get the hex code for the
		 * amount of green.
		 * @param hexString String reference containing the hex color code.
		 * @return String value containing the amount of green in base 16.
		 * @precondition hexString is a valid hex color code.
		 */
		private String getGreen(String hexString) {
			String strippedString = this.strip(hexString);
			return (strippedString.length() == 6) ? strippedString.substring(2, 4) : strippedString.substring(1, 2);
		}

		/**
		 * Accessor method used to get the hex code for the
		 * amount of blue.
		 * @param hexString String reference containing the hex color code.
		 * @return String value containing the amount of blue in base 16.
		 * @precondition hexString is a valid hex color code.
		 */
		private String getBlue(String hexString) {
			String strippedString = this.strip(hexString);
			return (strippedString.length() == 6) ? strippedString.substring(4) : strippedString.substring(2);
		}

		/**
		 * Accessor method used to get the base ten counterpart
		 * of the given String
		 * @param baseSixteen String reference containing a valid
		 *                    base sixteen String.
		 * @return int value containing the given reference's base ten
		 * equivalent.
		 * @precondition baseSixteen is a hex code stripped of unnecessary components.
		 */
		private int getBaseTen(String baseSixteen) {
			// one liners ftw lmaooo
			return (baseSixteen.length() > 0)
					? ("0123456789abcdef".indexOf(baseSixteen.charAt(0) * (int) Math.pow(16, baseSixteen.length() - 1)))
					+ this.getBaseTen(baseSixteen.substring(1))
					: 0;
		}
	}
	
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

	/**
	 * Mutator method designed to set the current HexColor instance
	 * with a hex String.
	 * @param hexCode String reference containing a valid hexCode.
	 * @throws InputMismatchException If the given hex String is not parseable.
	 */
	public void setColor(String hexCode) throws InputMismatchException {

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

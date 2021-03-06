package rgb_hsl.color;

import java.util.InputMismatchException;

/**
 * Subclass of class RGBColor.
 * Uses integer values for red, blue and green
 * and compensates for any remainder. Hex color codes
 * are given in the following form
 * #rrggbb
 * where rr, gg, and bb represent hexadecimal values on the
 * range of [00-ff], for the amount of red, green, and blue,
 * respectively.
 * @author Noah Teshima
 * @since 1.0.0
 */
public class HexColor extends RGBColor {
	private String hexCode;

	/**
	 * Nested class designed to parse Hex color codes.
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
		 * Remove unnecessary characters from the given hex code.
		 * @param hexString String reference containing the hex color code.
		 * @return String object containing the hex color code with unnecessary
		 * characters removed.
		 * @precondition hexString is a valid hex color code.
		 */
		private static String strip(String hexString) {
			hexString = hexString.replaceAll("\\s", "").toLowerCase();
			return (hexString.indexOf("#") != -1) ? hexString.substring(1) : hexString;
		}

		/**
		 * Determine whether the given String is a parseable hex color code.
		 * @param str String reference containing a potential hex color
		 *                  code.
		 * @return boolean value determining whether the given reference is
		 * in a parseable format for a hex color code.
		 */
		public static boolean isParseable(String str) {
			return str.matches(PATTERN_ONE);
		}

		/**
		 * Get the amount of red in the given hex color code in base ten.
		 * @param hexString String reference containing the hex color code.
		 * @return int value containing the amount of red in base ten.
		 * @throws InputMismatchException if the given String reference is not a valid hex color code.
		 */
		public static int getRedBaseTen(String hexString) throws InputMismatchException {
			if(!isParseable(hexString)) {
				throw new InputMismatchException();
			}

			return getBaseTen(getRed(hexString));
		}

		/**
		 * Get the amount of green in the given hex color code in base ten.
		 * @param hexString String reference containing the hex color code.
		 * @return int value containing the amount of green in base ten.
		 * @throws InputMismatchException if the given String reference is not a valid hex color code.
		 */
		public static int getGreenBaseTen(String hexString) throws InputMismatchException {
			if(!isParseable(hexString)) {
				throw new InputMismatchException();
			}

			return getBaseTen(getGreen(hexString));
		}

		/**
		 * Get the amount of blue in the given hex color code in base ten.
		 * @param hexString String reference containing the hex color code.
		 * @return int value containing the amount of blue in base ten.
		 * @throws InputMismatchException if the given String reference is not a valid hex color code.
		 */
		public static int getBlueBaseTen(String hexString) throws InputMismatchException {
			if(!isParseable(hexString)) {
				throw new InputMismatchException();
			}

			return getBaseTen(getBlue(hexString));
		}

		/**
		 * Get the hex code for the amount of red.
		 * @param hexString String reference containing the hex color code.
		 * @return int value containing the amount of red in base 16.
		 * @precondition hexString is a valid hex color code.
		 */
		private static String getRed(String hexString) {
			String strippedString = strip(hexString);
			return (strippedString.length() == 6) ? strippedString.substring(0, 2)
					: strippedString.substring(0, 1) + strippedString.substring(0, 1);
		}

		/**
		 * Get the hex code for the amount of green.
		 * @param hexString String reference containing the hex color code.
		 * @return String value containing the amount of green in base 16.
		 * @precondition hexString is a valid hex color code.
		 */
		private static String getGreen(String hexString) {
			String strippedString = strip(hexString);
			return (strippedString.length() == 6) ? strippedString.substring(2, 4)
					: strippedString.substring(1, 2) + strippedString.substring(1, 2);
		}

		/**
		 * Get the hex code for the amount of blue.
		 * @param hexString String reference containing the hex color code.
		 * @return String value containing the amount of blue in base 16.
		 * @precondition hexString is a valid hex color code.
		 */
		private static String getBlue(String hexString) {
			String strippedString = strip(hexString);
			return (strippedString.length() == 6) ? strippedString.substring(4)
					: strippedString.substring(2) + strippedString.substring(2);
		}

		/**
		 * Get the base ten counterpart of the given String
		 * @param baseSixteen String reference containing a valid
		 *                    base sixteen String.
		 * @return int value containing the given reference's base ten
		 * equivalent.
		 * @precondition baseSixteen is a hex code stripped of unnecessary components.
		 */
		private static int getBaseTen(String baseSixteen) {
			return (baseSixteen.length() > 0)
					? ("0123456789abcdef".indexOf(baseSixteen.charAt(0)) * ((int) Math.pow(16, baseSixteen.length() - 1)))
					+ getBaseTen(baseSixteen.substring(1))
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
	 * Create a HexColor with the same values as the given RGBColor reference.
	 * @param color RGBColor reference containing the RGB values to
	 *                 copy.
	 */
	public HexColor(RGBColor color) {
		this(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	/**
	 * Set the hex color given the amount of red, green,
	 * and blue for the current instance.
	 * @param red integer value containing the amount
	 *               of red
	 * @param green integer value containing the amount
	 *                 of green
	 * @param blue integer value containing the amount
	 *                of blue
	 */
	public HexColor(int red, int green, int blue) {
		this.setColor(red, green, blue);
	}

	/**
	 * Set the hex color given a String reference containing
	 * a hex color code.
	 * @param hexCode String reference containing a hex color code.
	 * @throws InputMismatchException If the given hex String is not parseable.
	 */
	public HexColor(String hexCode) throws InputMismatchException {
		this.setColor(hexCode);
	}

	/**
	 * Set the instance's hexCode to its corresponding
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
	 * Get the hex color code associated with the current instance.
	 * All returned hex color codes are given in the following form
	 * <b>#rrggbb</b>
	 * where <b>rr</b>, <b>gg</b>, and <b>bb</b> represent hexadecimal values on the
	 * range of [00-ff], inclusive, for the amount of red, green, and blue,
	 * respectively.
	 * @return String object containing the current hex color
	 * code.
	 */
	public String getHexCode() {
		return this.hexCode;
	}

	/**
	 * Set the current HexColor instance with a hex String.
	 * All valid hex color codes are given in either of the
	 * following forms
	 * <b>#rrggbb</b>, or <b>#rgb</b>
	 * where <b>rr</b>, <b>gg</b>, and <b>bb</b> represent hexadecimal values on the
	 * range of [00-ff], inclusive, for the amount of red, green, and blue,
	 * respectively. In the case where shorthand hex color codes is used,
	 * <b>r</b>, <b>g</b>, and <b>b</b> collectively repesent both the
	 * first and second order of magnitude in base 16.
	 * @param hexCode String reference containing a valid hexCode.
	 * @throws InputMismatchException If the given hex String is not parseable.
	 */
	public void setColor(String hexCode) throws InputMismatchException {
		this.setColor(HexParser.getRedBaseTen(hexCode), HexParser.getGreenBaseTen(hexCode), HexParser.getBlueBaseTen(hexCode));
		this.updateHexCode();
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

package rgb_hsl.color;

/**
 * Subclass of Color.
 * Uses integer values for red, blue and green
 * and compensates for any remainder. Elements in RGB
 * space are ordered triplets, where each component is
 * on the range [0, 255], inclusive.
 * @author Noah Teshima
 * @since 1.0.0
 */
public class RGBColor extends Color {
	private int red,
	green,
	blue;
	
	/**
	 * Default constructor. When invoked, the current
	 * instance is set to have a shade of black, with the
	 * RGB triplet of (0, 0, 0).
	 */
	public RGBColor() {
		this(0, 0, 0);
	}
	
	/**
	 * Create an RGB color with the same values as the given reference.
	 * Specifically, the RGB element will have the same ordered triplet
	 * as the given reference. However, a deep copy is created, so two
	 * distinct references will have the same position in RGB space.
	 * @param color RGBColor reference containing the RGB values to
	 *                 copy.
	 */
	public RGBColor(RGBColor color) {
		this(color.getRed(), color.getGreen(), color.getBlue());
	}

	/**
	 * Set the red, green, and blue values of the current
	 * color to their corresponding values. Elements in RGB
	 * space are ordered triplets, where each component is
	 * on the range [0, 255], inclusive.
	 * @param red integer value containing the amount
	 *               of red
	 * @param green integer value containing the amount
	 *                 of green
	 * @param blue integer value containing the amount
	 *                of blue
	 */
	public RGBColor(int red, int green, int blue) {
		this.setColor(red, green, blue);
	}

	/**
	 * Set the amount of red, green, and blue. This sets
	 * the position of the given instance in RGB space.
	 * @param red integer value containing the amount
	 *               of red
	 * @param green integer value containing the amount
	 *                 of green
	 * @param blue integer value containing the amount
	 *                of blue
	 */
	@Override
	public void setColor(double red, double green, double blue) {
		this.setRed((int) red);
		this.setGreen((int) green);
		this.setBlue((int) blue);
	}

	/**
	 * Get the amount of red in the current RGB color.
	 * The amount of red is guaranteed to be on the range
	 * [0, 255], inclusive.
	 * @return integer value containing the amount of
	 * red.
	 */
	public int getRed() {
		return this.red;
	}

	/**
	 * Mutator method used to set the new amount of
	 * red in the current RGB Color, on the range
	 * [0, 255], inclusive.
	 * @param red integer value containing the new
	 *               amount of red.
	 */
	public void setRed(int red) {
		//keep in range if less than 0
		//and use remainder if more than 255
		this.red = (red < 0) ? 0 : red % 256;
	}

	/**
	 * Get the amount of green in the current RGB color.
	 * The amount of green is guaranteed to be on the
	 * range [0, 255], inclusive.
	 * @return integer value containing the amount of
	 * green.
	 */
	public int getGreen() {
		return this.green;
	}

	/**
	 * Set the new amount of green in the current RGB Color,
	 * on the range [0, 255], inclusive.
	 * @param green integer value containing the new
	 *                 amount of green.
	 */
	public void setGreen(int green) {
		this.green = (green < 0) ? 0 : green % 256;
	}

	/**
	 * Get the amount of blue in the current RGB color.
	 * The amount of blue is guaranteed to be on the range
	 * [0, 255], inclusive.
	 * @return integer value containing the amount of
	 * blue.
	 */
	public int getBlue() {
		return this.blue;
	}

	/**
	 * Set the new amount of blue in the current RGB Color,
	 * on the range [0, 255], inclusive.
	 * @param blue integer value containing the new
	 *                amount of blue.
	 */
	public void setBlue(int blue) {
		this.blue = (blue < 0) ? 0 : blue % 256;
	}

	/**
	 * Get the hue corresponding to the current RGB values.
	 *
	 * Algorithm for hue is based on the Chromaticity
	 * plane, which takes the additive primary colors
	 * (red, green, and blue), and puts them all on a
	 * polar graph, where red is at 0 degrees, green
	 * is at 120 degrees, and blue is at 240 degrees.
	 * More information can be found here:
	 * <a href="https://en.wikipedia.org/wiki/HSL_and_HSV">
	 * https://en.wikipedia.org/wiki/HSL_and_HSV
	 * </a>
	 *
	 * @return double value containing the number of
	 * degrees away on a chromaticity plane.
	 */
	private int getHue() {
		//cartesian pair for chromaticity plane
		double x = (((this.green - this.blue) * .5) * Math.sqrt(3)),
				y = (((2 * this.red) - (this.green + this.blue)) * .5),
				//take arctangent for radial direction
				//in polar plane
				hue = Math.atan2(x, y);

		//radians to degree conversion
		//add 360 in order to remove negative
		//radian values from atan2, while
		//preserving value
		hue = Math.toDegrees(hue) + 360;
		//mod 360 in order to keep in range of [0, 360]
		hue %= 360;

		return (int) hue;
	}

	/**
	 * Get the saturation of the current RGB color.
	 * In a HSL color space, the saturation can be described
	 * as the quantity of hue when put on a spectrum of
	 * pure grey to the current hue. In other words,
	 * a high saturation indicates a stronger color,
	 * while a low saturation indicates a gray color.
	 *
	 * @return double value containing the
	 * amount of saturation for the current
	 * RGB color.
	 */
	private double getSaturation() {
		double saturation = 0;
		//if they are equal, saturation should be 0
		if(this.getMin() != this.getMax()) {
			if(this.getLightness() < 0.5) {
				saturation = (this.getMax() - this.getMin())
						/ (this.getMax() + this.getMin());
			}else {
				saturation = (this.getMax() - this.getMin())
						/ (2 - (this.getMax() + this.getMin()));
			}
		}
		return saturation;
	}

	/**
	 * Get the lightness of the current RGB color.
	 * The formula for lightness conversion from an RGB
	 * color space is to get the maximum and minimum
	 * interpolated values and average them. Because of
	 * this, lightness will also be interpolated in
	 * the range of 0 to 1, inclusive.
	 *
	 * @return double value containing the
	 * lightness of the RGB color.
	 */
	private double getLightness() {
		double lightness = (this.getMax() + this.getMin()) / 2;
		return lightness;
	}

	/**
	 * Get the corresponding color in an HSL color space.
	 * This is a linear transformation between RGB and HSL
	 * color spaces, and represents the equivalent form
	 * in HSL space.
	 * @return HSLColor object that corresponds to the
	 * current instance's RGB values.
	 */
	public HSLColor getHSLColor() {
		return new HSLColor(this.getHue(), this.getSaturation(),
				this.getLightness());
	}
	
	/**
	 * Get the amount of red on the range
	 * [0, 1], inclusive.
	 * @return the amount of red interpolated
	 * on range [0, 1], inclusive.
	 */
	private double getInterpolatedRed() {
		return ((double) this.getRed() / 255);
	}
	
	/**
	 * Get the amount of green on the range
	 * [0, 1], inclusive.
	 * @return the amount of green interpolated
	 * on range [0, 1], inclusive.
	 */
	private double getInterpolatedGreen() {
		return ((double) this.getGreen() / 255);
	}
	
	/**
	 * Get the amount of blue on the range of
	 * [0, 1], inclusive.
	 * @return the amount of blue interpolated
	 * on range [0, 1], inclusive.
	 */
	private double getInterpolatedBlue() {
		return ((double) this.getBlue() / 255);
	}
	
	/**
	 * Get the maximum interpolated RGB value.
	 * @return double value containing the maximum
	 * interpolated RGB value on range [0, 1],
	 * inclusive.
	 */
	private double getMax() {
		double returnValue = this.getInterpolatedRed();
		if(this.getInterpolatedGreen() > returnValue) {
			returnValue = this.getInterpolatedGreen();
		}
		if(this.getInterpolatedBlue() > returnValue) {
			returnValue = this.getInterpolatedBlue();
		}
		
		return returnValue;
	}
	
	/**
	 * Get the minimum interpolated RGB value.
	 * @return double value containing the minimum
	 * interpolated RGB value on range [0, 1], inclusive.
	 */
	private double getMin() {
		double returnValue = this.getInterpolatedRed();
		if(this.getInterpolatedGreen() < returnValue) {
			returnValue = this.getInterpolatedGreen();
		}
		if(this.getInterpolatedBlue() < returnValue) {
			returnValue = this.getInterpolatedBlue();
		}
		
		return returnValue;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof RGBColor)) {
			return false;
		}

		RGBColor colorObj = (RGBColor) obj;

		return (this.getRed() == colorObj.getRed()
				&& this.getBlue() == colorObj.getBlue()
				&& this.getGreen() == colorObj.getGreen());
	}


	@Override
	public String toString() {
		return String.format("RGB(%d, %d, %d)\n",
				this.getRed(),
				this.getGreen(),
				this.getBlue());
	}
}

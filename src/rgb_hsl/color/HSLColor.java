package rgb_hsl.color;

/**
 * Subclass of Color.
 * Used for describing a color by hue, saturation,
 * and lightness.
 * @author Noah Teshima
 * @since 1.0.0
 */
public class HSLColor extends Color {
	private int hue;
	private double saturation,
	lightness;

	/**
	 * Default constructor. When invoked, the current
	 * instance is set to have a hue of 0, a saturation
	 * of 0, and a lightness of 0, resulting in a pure
	 * black.
	 */
	public HSLColor() {
		this(0, 0, 0);
	}
	
	/**
	 * Create an HSL color with the same values as the given
	 * HSLColor reference.
	 * @param color HSLColor reference containing the HSL values to
	 *                 copy.
	 */
	public HSLColor(HSLColor color) {
		this(color.getHue(), color.getSaturation(), color.getLightness());
	}
	
	/**
	 * Set the hue, saturation, and lightness values to their
	 * corresponding parameters.
	 * @param hue integer value containing the degree of
	 *               the new hue.
	 * @param saturation double value containing the percentage
	 *                      of saturation.
	 * @param lightness double value containing the percentage
	 *                     of lightness.
	 */
	public HSLColor(int hue, double saturation, double lightness) {
		this.setHue(hue);
		this.setSaturation(saturation);
		this.setLightness(lightness);
	}

	/**
	 * Sets the amount of hue, saturation, and lightness.
	 * @param hue integer value containing the amount
	 *               of hue.
	 * @param saturation integer value containing the amount
	 *                      of saturation.
	 * @param lightness integer value containing the amount
	 *                     of lightness.
	 */
	@Override
	public void setColor(double hue, double saturation, double lightness) {
		this.setHue((int) hue);
		this.setSaturation(saturation);
		this.setLightness(lightness);
	}
	
	/**
	 * Set the hue of the current instance.
	 * Takes values in range [0, 360], inclusive.
	 * However, in the instance where the hue is not
	 * in the given range, modulus will be used in
	 * order to ensure the resulting hue will be
	 * in the range [0, 360].
	 * @param hue integer value containing the degree
	 *               of the new hue.
	 */
	public void setHue(int hue) {
		this.hue = (hue < 0) ? 360 + (hue % 360) : hue % 360;
	}
	
	/**
	 * Get the hue of the current instance. The resulting
	 * hue is guaranteed to be in the range [0, 360].
	 * @return integer value containing the degree
	 * of hue.
	 */
	public int getHue() {
		return this.hue;
	}

	/**
	 * Increment the hue of the current color
	 * by the specified amount. If a negative
	 * value is specified, the hue is decremented
	 * instead.
	 * @param degrees integer value containing the
	 *                   number of degrees to increment or decrement
	 *                   the hue by.
	 */
	public void incrementHue(int degrees) {
		this.setHue(this.getHue() + degrees);
	}
	
	/**
	 * Set the saturation of the current instance
	 * on the range [0, 1], inclusive. If the given
	 * saturation is outside of these bounds, it is
	 * set to the closest boundary on this range.
	 * @param saturation double value containing the
	 *                      new saturation.
	 */
	public void setSaturation(double saturation) {
		if(saturation < 0) {
			this.saturation = 0;
		}else if(saturation > 1) {
			this.saturation = 1;
		}else {
			this.saturation = saturation;
		}
	}
	
	/**
	 * Get the saturation of the current instance.
	 * The saturation is guaranteed to always be on
	 * the range [0, 1], inclusive.
	 * @return double value containing the current
	 * saturation
	 */
	public double getSaturation() {
		return this.saturation;
	}
	
	/**
	 * Set the lightness of the current instance
	 * on the range [0 to 1], inclusive. If the given
	 * lightness is outside of these bounds, it is
	 * set to the closest boundary on this range.
	 * @param lightness double value containing the
	 *                     new lightness.
	 */
	public void setLightness(double lightness) {
		if(lightness < 0) {
			this.lightness = 0;
		}else if(lightness > 1) {
			this.lightness = 1;
		}else {
			this.lightness = lightness;
		}
	}
	
	/**
	 * Accessor method used to get the lightness
	 * of the current instance. The saturation is
	 * guaranteed to always be on the range [0, 1],
	 * inclusive.
	 * @return double value containing the current
	 * lightness
	 */
	public double getLightness() {
		return this.lightness;
	}
	
	/**
	 * Get the amount of red in the HSL color. The amount
	 * of red is guaranteed to be on the range [0, 255] inclusive.
	 * 
	 * @return integer value containing the amount of
	 * red in the current HSL color.
	 */
	private int getRed() {
		double returnRed = 0;
		
		/*if no saturation there is a shade of gray*/
		if(this.getSaturation() == 0) {
			returnRed = 255 * this.getLightness();
		}else {
			//transformation is based around green as a starting point.
			//since red is 120 degrees away, we need to add an interpolated
			//120 degrees
			double tempColor = (((double) this.getHue() / 360) + ((double) 1 / 3));
			if(tempColor < 0) {
				tempColor += 1;
			}else if(tempColor > 1) {
				tempColor -= 1;
			}
			returnRed = this.getColorTransform(tempColor);
		}
		
		return (int) returnRed;
	}

	/**
	 * Get the amount of green in the HSL color. The amount
	 * of green is guaranteed to be on the range [0, 255] inclusive.
	 * 
	 * @return integer value containing the amount of
	 * green in the current HSL color.
	 */
	public int getGreen() {
		double returnGreen = 0;
		
		/*if no saturation there is a shade of gray*/
		if(this.getSaturation() == 0) {
			returnGreen = 255 * this.getLightness();
		}else {
			//no need to add an interpolated 120 degrees, since
			//our transformation is based with green as a starting
			//point
			double tempColor = ((double) this.getHue() / 360);
			if(tempColor < 0) {
				tempColor += 1;
			}else if(tempColor > 1) {
				tempColor -= 1;
			}
			returnGreen = this.getColorTransform(tempColor);
		}
		return (int) returnGreen;
	}

	/**
	 * Get the amount of blue in the HSL color. The amount
	 * of blue is guaranteed to be on the range [0, 255] inclusive.
	 * 
	 * @return integer value containing the amount of
	 * blue in the current HSL color.
	 */
	public int getBlue() {
		double returnBlue = 0;
		
		/*if no saturation there is a shade of gray*/
		if(this.getSaturation() == 0) {
			returnBlue = 255 * this.getLightness();
		}else {
			//transformation is based around green as a starting point.
			//since blue is 120 degrees away like red, we need to subtract
			//an interpolated 120 degrees instead of adding, since blue is
			//in the opposite direction of red in terms of rotation
			double tempColor = (((double) this.getHue() / 360) - ((double) 1 / 3));
			if(tempColor < 0) {
				tempColor += 1;
			}else if(tempColor > 1) {
				tempColor -= 1;
			}
			returnBlue = this.getColorTransform(tempColor);
		}
		
		return (int) returnBlue;
	}
	
	/**
	 * Get the corresponding color in an RGB color space.
	 * This is a linear transformation between RGB and HSL color spaces,
	 * and represents the equivalent form in RGB space.
	 * @return RGBColor object that corresponds to the
	 * current instance's HSL values.
	 */
	public RGBColor getRGBColor() {
		return new RGBColor(this.getRed(), this.getGreen(), this.getBlue());
	}
	
	/**
	 * Get the conversion for an HSL to an RGB color space.
	 * The math conversion can be done with a 3x3 matrix
	 * transformation. Information about the
	 * implemented algorithm can be found here:
	 * <a href="http://www.niwa.nu/2013/05/math-behind-colorspace-conversions-rgb-hsl/">
	 * http://www.niwa.nu/2013/05/math-behind-colorspace-conversions-rgb-hsl/
	 * </a>
	 * @param tempColor integer value containing the interpolated
	 * value to transform into RGB color space.
	 * @return double value containing the color's corresponding
	 * interpolated value in RGB color space, in range of 0 to 1,
	 * inclusive.
	 */
	private int getColorTransform(double tempColor) {
		double tempOne,
		tempTwo,
		returnColor = 0;
		
		if(this.getLightness() < .5) {
			tempOne = this.getLightness() * (1.0 + this.getSaturation());
		}else {
			tempOne = this.getLightness() + this.getSaturation()
			- (this.getLightness() * this.getSaturation());
		}
		
		tempTwo = (2 * this.getLightness()) - tempOne;
		
		/*if color value is in range of 0 to 60 degrees*/
		if(tempColor * 6 < 1) {
			returnColor = tempTwo + ((tempOne - tempTwo) * 6 * tempColor);
		/*if color value is in range of 60 to 180 degrees*/
		}else if(tempColor * 2 < 1) {
			returnColor = tempOne;
		/*if color value is in range of 180 to 240 degrees*/
		}else if(tempColor * 3 < 2) {
			returnColor = tempTwo
					+ ((tempOne - tempTwo) * (4 - (6 * tempColor)));
		/*otherwise in range of 180 to 360 degrees*/
		}else {
			returnColor = tempTwo;
		}
		//put in range of 0, 255, inclusive
		returnColor *= 255;
		//prevent any truncating to keep as accurate as
		//possible
		returnColor = Math.round(returnColor);
		
		return (int) returnColor;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof HSLColor)) {
			return false;
		}
		HSLColor colorObj = (HSLColor) obj;

		return (this.getHue() == colorObj.getHue()
		&& this.getSaturation() == colorObj.getSaturation()
		&& this.getLightness() == colorObj.getLightness());
	}
	
	@Override
	public String toString() {
		return String.format("HSL (%d, %.2f, %.2f)",
				this.hue,
				this.saturation,
				this.lightness);
	}
}

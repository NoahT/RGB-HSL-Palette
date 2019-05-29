package rgb_hsl.color;

/**
 * Subclass of Color.
 * Used for describing a color by hue, saturation,
 * and lightness.
 * @author Noah Teshima
 *
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
	 * Copy constructor used to create an HSL color with the same
	 * values as the given reference.
	 * @param color HSLColor reference containing the HSL values to
	 * copy.
	 */
	public HSLColor(HSLColor color) {
		this(color.getHue(), color.getSaturation(), color.getLightness());
	}
	
	/**
	 * Constructor used to set the hue, saturation, and
	 * lightness values to their corresponding parameters.
	 * @param hue integer value containing the degree of
	 * the new hue.
	 * @param saturation double value containing the percentage
	 * of saturation.
	 * @param lightness double value containing the percentage
	 * of lightness.
	 */
	public HSLColor(int hue, double saturation, double lightness) {
		this.setHue(hue);
		this.setSaturation(saturation);
		this.setLightness(lightness);
	}

	/**
	 * Mutator method designed to set the amount of hue, saturation,
	 * and lightness.
	 * @param hue integer value containing the amount
	 * of hue.
	 * @param saturation integer value containing the amount
	 * of saturation.
	 * @param lightness integer value containing the amount
	 * of lightness.
	 */
	@Override
	public void setColor(double hue, double saturation, double lightness) {
		this.setHue((int) hue);
		this.setSaturation(saturation);
		this.setLightness(lightness);
	}
	
	/**
	 * Mutator method used to set the hue of the
	 * current instance. Takes values in range
	 * of 0 to 360 degrees, inclusive.
	 * @param hue integer value containing the degree
	 * of the new hue.
	 */
	public void setHue(int hue) {
		this.hue = (hue < 0) ? 360 + (hue % 360) : hue % 360;
	}
	
	/**
	 * Accessor method used to get the hue of the
	 * current instance.
	 * @return integer value containing the degree
	 * of hue.
	 */
	public int getHue() {
		return this.hue;
	}

	/**
	 * Mutator method used to increment the hue
	 * of the current color by the specified amount.
	 * @param degrees integer value containing the
	 * number of degrees to increment the hue by.
	 */
	public void incrementHue(int degrees) {
		this.setHue(this.getHue() + degrees);
	}
	
	/**
	 * Mutator method used to set the saturation
	 * of the current instance on a range from
	 * 0 to 1, inclusive.
	 * @param saturation double value containing the
	 * new saturation.
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
	 * Accessor method used to get the saturation of
	 * the current instance.
	 * @return double value containing the current
	 * saturation
	 */
	public double getSaturation() {
		return this.saturation;
	}
	
	/**
	 * Mutator method used to set the lightness
	 * of the current instance on a range from
	 * 0 to 1, inclusive.
	 * @param lightness double value containing the
	 * new lightness.
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
	 * of the current instance.
	 * @return double value containing the current
	 * lightness
	 */
	public double getLightness() {
		return this.lightness;
	}
	
	/**
	 * Helper method used to get the amount of
	 * red in the HSL color.
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
	 * Helper method used to get the amount of
	 * green in the HSL color.
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
	 * Helper method used to get the amount of
	 * blue in the HSL color.
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
	 * Accessor method used to get the corresponding
	 * color in an RGB color space. This is a linear transformation
	 * between RGB and HSL color spaces.
	 * @return RGBColor object that corresponds to the
	 * current instance's HSL values.
	 */
	public RGBColor getRGBColor() {
		return new RGBColor(this.getRed(), this.getGreen(), this.getBlue());
	}
	
	/**
	 * Accessor method used to get the converison
	 * for an HSL to an RGB color space. The math
	 * conversion can be done with a 3x3 matrix
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
	
	/**
	 * Overridden toString() method from Color class.
	 * When invoked, the hue, saturation, and lightness
	 * values are returned as a String object.
	 * 
	 * @return String object containing the instance's
	 * hue, saturation, and lightness values.
	 */
	@Override
	public String toString() {
		return String.format("HSL (%d, %.2f, %.2f)",
				this.hue,
				this.saturation,
				this.lightness);
	}
}

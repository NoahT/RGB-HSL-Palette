package rgb_hsl.color;

/**
 * Color interface implemented
 * for different ways of determining Color.
 * This interface is purposely left as a marker interface
 * since HSL and RGB color spaces have no universal
 * characteristics, aside from the fact that
 * both describe color.
 * @author Noah Teshima
 *
 */
public abstract class Color {
    /**
     * Method stub designed to set the current color, given
     * three distinct elements. Both RGB and HSL spaces are isomorphic
     * to three dimensional real space, so three distinct elements are
     * needed in either cases to define a color.
     * @param elementA double value containing elementA
     * @param elementB double value containing elementB
     * @param elementC double value containing elementC
     */
    public abstract void setColor(double elementA, double elementB, double elementC);

    /**
     * Accessor method used to get the given color in HSL space.
     * @param color Color reference containing the color
     *              to get in HSL space.
     * @return HSLColor object containing the given
     * color in HSL space.
     */
    public static HSLColor getHSLColor(Color color) {
        return (color instanceof HSLColor)
                ? (HSLColor) color
                : ((RGBColor) color).getHSLColor();
    }

    /**
     * Accessor method used to get the given color in RGB space.
     * @param color Color reference containing the color
     *              to get in RGB space.
     * @return RGBColor object containing the given
     * color in RGB space.
     */
    public static RGBColor getRGBColor(Color color) {
        return (color instanceof RGBColor)
                ? (RGBColor) color
                : ((HSLColor) color).getRGBColor();
    }
}

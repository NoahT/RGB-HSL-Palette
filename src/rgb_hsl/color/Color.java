package rgb_hsl.color;

/**
 * Class Color represents the root class for different
 * color spaces. Color spaces represent different ways
 * of describing Color. HSL and RGB color spaces are
 * isomorphic to three dimensional real space. As such,
 * this class should only serve as a root for color spaces
 * that are also isomorphic to three dimensional space.
 * @author Noah Teshima
 * @since 1.0.0
 */
public abstract class Color {
    /**
     * Set the current color, given three distinct elements.
     * Both RGB and HSL spaces are isomorphic to three dimensional
     * real space, so three distinct elements are needed in either
     * cases to define a color.
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

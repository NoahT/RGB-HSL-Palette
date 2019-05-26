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
public interface Color {
    /**
     * Mutator method designed to set the current color, given
     * three distinct elements. Both RGB and HSL spaces are isomorphic
     * to three dimensional real space, so three distinct elements are
     * needed in either cases to define a color.
     * @param elementA double value containing elementA
     * @param elementB double value containing elementB
     * @param elementC double value containing elementC
     */
    public void setColor(double elementA, double elementB, double elementC);
}

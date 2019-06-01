# Contributions
Contributions are encouraged in all manners. Beforehand, please read the most relevant section in order to understand
our project flow.

## Bugs
This project recently came into fruition, so finding bugs is still a common occurrence. Before proposing a new issue,
please follow the steps outlined below:

1. Check to see whether the same issue was already raised. Raising a new issue that is already being worked on partitions
everyone's effort into multiple areas, promoting some degree of redundancy and miscommunication. In some instances, a similar
issue may already exist. If possible, provide any information that provides further insight into previous issues, and raise a
separate issue with information that is unique to your problem.
2. Once certain that the bug was not already reported, include a **title** and **description** that clearly addresses the problem.
The description should include an understanding of what the intended functionality should be, how the bug is hindering this functionality,
and any associated code snippets that reproduce the problem. Optionally, a proposed solution may also be included. 

### Example

**Title**: Loss of precision for colors between HSL and RGB spaces

**Description**: The transformations for colors between HSL and RGB space should be invertible. Having some color in HSL space,
after transforming into RGB space and back to HSL space, should yield the initial color in HSL space. However, the use of integers
for storing ordered triplets in *RGBColor.java* causes a loss of precision, as these values are ultimately truncated. For instance,
the following code includes an HSL -> RGB -> HSL transformation that yields a color with different saturation:

    Color colorOne = new HSLColor(20, 1, .50), // Primary red in HSL space
    colorTwo = ((HSLColor) colorOne).getRGBColor(), // Get primary red in RGB space
    colorThree = ((RGBColor) colorTwo).getHSLColor(); // Back to primary red in HSL space
    System.out.printf("%s\t%s\t%s", colorOne, colorTwo, colorThree);

    "HSL(20, 1.00, 0.50) RGB(255, 85, 0) HSL(19, 1.00, 0.50)"

In order to prevent this from happening, I suggest changing the instance variables containing red, green, and blue in RGBColor to
use doubles instead. While not completely fixing the problem, the loss of precision that is being caused by truncated values will be
prevented.

# Contributions
Contributions are encouraged in all manners. Beforehand, please read the most relevant section in order to understand
our project flow.

- Bugs
    - [Proposals](#proposing-bugs)
    - [Patches](#patches-for-bugs)
- Features
    - [Proposals](#adding-new-features)


## Proposing bugs
This project recently came into fruition, so finding bugs is still a common occurrence. Before proposing a new issue,
please follow the steps outlined below:

1. Check to see whether the same issue was already raised. Raising a new issue that is already being worked on partitions
everyone's effort into multiple areas, promoting some degree of redundancy and miscommunication. In some instances, a similar
issue may already exist. If possible, provide any information that provides further insight into previous issues, and raise a
separate issue with information that is unique to your problem.
2. Once certain that the bug was not already reported, include an issue with the **bug** label, as well as a **title** and **description** that clearly addresses the problem. The description should include an understanding of what the intended functionality should be, how the bug is hindering this functionality, and any associated code snippets that reproduce the problem. Optionally, a proposed solution may also be included. 

### Example

**Title**: *Loss of precision for colors between HSL and RGB spaces*

**Description**: *The transformations for colors between HSL and RGB space should be invertible. Having some color in HSL space,
after transforming into RGB space and back to HSL space, should yield the initial color in HSL space. However, the use of integers
for storing ordered triplets in RGBColor.java causes a loss of precision, as these values are ultimately truncated. For instance,
the following code includes an HSL -> RGB -> HSL transformation that yields a color with different hue:*

    Color colorOne = new HSLColor(20, 1, .50), // Primary red in HSL space
    colorTwo = ((HSLColor) colorOne).getRGBColor(), // Get primary red in RGB space
    colorThree = ((RGBColor) colorTwo).getHSLColor(); // Back to primary red in HSL space
    System.out.printf("%s\t%s\t%s", colorOne, colorTwo, colorThree);

    "HSL(20, 1.00, 0.50) RGB(255, 85, 0) HSL(19, 1.00, 0.50)"

*In order to prevent this from happening, I suggest changing the instance variables containing red, green, and blue in RGBColor to
use doubles instead. While not completely fixing the problem, the loss of precision that is being caused by truncated values will be
prevented.*


## Patches for bugs
Having a resolution for a proposed problem is great. The following steps outline the process that must be undergone in order to ensure
your pull request is merged successfully:

1.  Your resolution for the proposed problem must be directly addressed and accepted on the associated issue. This is done in order to
avoid numerous people working on the same issue, when a single person will suffice.
2.  After forking this repository, you should place all of your work under a branch labeled *username-rgb-hsl*, where username represents your Github username.
3.  Your pull request, when completed with the necessary unit tests, should be merged into the branch labeled *experimental*. Include a **title** and **description** that clearly addresses the resolution (as well as any unit tests added) of the associated problem. Any pull requests into *master* will be rejected. Pull requests into *master* are only accepted once a new release is ready to be made.
4.  When writing the proposed patch, ensure that appropriate unit tests are written beforehand. This falls back onto the [test-driven development](https://en.wikipedia.org/wiki/Test-driven_development) work cycle, and ensures greater code coverage as well as improving the credibility of any proposed solution.

### Example

**Title**: *Improved precision for colors between HSL and RGB spaces*

**Description**: *The use of integers for storing ordered triplets in RGBColor.java caused colors from HSL space to lose important precision, since these values were being truncated. To fix this, I changed the instance variables for storing red, green, and blue to use doubles instead. Two additional unit tests were added to RGBColorTest.java. The first unit test was added in order to check whether HSL -> RGB -> HSL transformations yield the starting color in HSL space. Similarly, another unit test was included in order to check whether RGB -> HSL -> RGB transformations yield the starting color in RGB space.*


## Adding new features
New features for promoting this library are always encouraged. Given the scope of this project, it remains important to obey the guidelines listed below:

1.  Check to see whether a similar feature was already proposed. Just like with proposals for bugs, it is possible that another contributor is already working on developing the same feature. Instances in which there exists a notable degree of distinction between your proposal and another individual's proposal will be handled on a case-by-case basis.
2.  If your intended feature has not yet been worked on, include an issue with the **enhancement** label, as well as a **title** and **description** that clearly addresses the intended feature. The description should include an explanation of what the intended feature should be, as well as how this feature holds significance with respect to this library's initial intentions. Any features that persist beyond this library's purpose will be denied.
3.  Your proposed feature must be directly addressed and accepted on the associated issue. This is done in order to avoid numerous people working on the same issue, while also adhering to this library's purpose.
4.  After forking this repository, you should place all of your work under a branch labeled *username-rgb-hsl*, where username represents your Github username.
5.  Your pull request, when completed with the necessary unit tests, should be merged into the branch labeled *experimental*. Include a **title** and **description** that clearly addresses the feature (as well as any unit tests added) of the associated problem. Any pull requests into *master* will be rejected. Pull requests into *master* are only accepted once a new release is ready to be made.
6.  Similarly to patches, when writing the proposed feature, ensure that appropriate unit tests are written beforehand. This falls back onto the [test-driven development](https://en.wikipedia.org/wiki/Test-driven_development) work cycle, and ensures a high degree of code coverage is maintained as this project scales with contributions.

### Example

**Title**: *Add support for alpha-transparency*

**Description**: *Color provided in this library currently have support for HSL and RGB. Given that transparency also plays some degree in how overlapping colors behave, it is important to extend HSLColor.java and RGBColor.java in order to include instances for preserving transparency.*


/*
### Objective

Imagine you work in Thoughtful’s robotic automation factory, and your objective is to write a function for one of its robotic arms that will dispatch the packages to the correct stack according to their volume and mass.

### Rules

Sort the packages using the following criteria:
A package is bulky if its volume (Width x Height x Length) is greater than or equal to 1,000,000 cm³ or
when one of its dimensions is greater or equal to 150 cm.

A package is heavy when its mass is greater or equal to 20 kg.

You must dispatch the packages in the following stacks:
STANDARD: standard packages (those that are not bulky or heavy) can be handled normally.
SPECIAL: packages that are either heavy or bulky can't be handled automatically.
REJECTED: packages that are both heavy and bulky are rejected.

Implement the function sort(width, height, length, mass) (units are centimeters for the dimensions and kilogram for the mass).
This function must return a string: the name of the stack where the package should go.


Some assumptions / clarifications or undefined behavior:

The input type is not specifically mentioned.  I've gone with Int here but it could be changed to a more precise type if necessary
Exactly what validation should be done on the input is also unclear.  Negative dimensions/mass are clearly invalid,
zero is probably also invalid, but could also be a valid input depending on how we solve the above (e.g. if we just round down for small dimensions)

 */
package org.example

object ThoughtfulAutomation {
    private const val MAXIMUM_VOLUME = 1_000_000 // 1 million kg
    private const val MAXIMUM_DIMENSION = 150 // 150 cm
    private const val MAXIMUM_MASS = 20
    private const val REJECTED = "REJECTED"
    private const val SPECIAL = "SPECIAL"
    private const val STANDARD = "STANDARD"

    /**
     * Validates that the input dimensions ([width], [height], [length], and [mass]) are positive integers.
     *
     */
    fun validateInput(width: Int, height: Int, length: Int, mass: Int): Boolean {
        return listOf(width, height, length, mass).all { it > 0 }
    }

    /**
     * Returns true if the package is both heavy and bulky
     */
    fun isRejected(width: Int, height: Int, length: Int, mass: Int): Boolean =
        isHeavy(mass) && isBulky(width, height, length)

    /**
     * Returns true if the package [mass] exceeds the maximum allowable mass
     */
    fun isHeavy(mass: Int): Boolean = mass >= MAXIMUM_MASS

    /**
     * Returns true if any of the package dimensions ([width], [height], [length]) exceed the maximum allowable
     * dimension OR the total volume exceeds the maximum allowable volume.
     */
    fun isBulky(width: Int, height: Int, length: Int): Boolean =
        listOf(width, height, length).any { it >= MAXIMUM_DIMENSION } || width * height * length >= MAXIMUM_VOLUME

    /**
     * Determines the correct stack for a package based on its dimensions and mass.
     * The package is classified according to the following rules:
     * 1. A "STANDARD" package is neither bulky nor heavy.
     * 2. A "SPECIAL" package is either bulky or heavy but not both.
     * 3. A "REJECTED" package is both bulky and heavy, or has invalid dimensions/mass.
     *
     * @param width The width of the package in centimeters.
     * @param height The height of the package in centimeters.
     * @param length The length of the package in centimeters.
     * @param mass The mass of the package in kilograms.
     * @return A string indicating the stack for the package: one of "STANDARD", "SPECIAL", or "REJECTED".
     */
    fun sort(width: Int, height: Int, length: Int, mass: Int): String = when {
        !validateInput(width, height, length, mass) -> REJECTED
        isRejected(width, height, length, mass) -> REJECTED
        isBulky(width, height, length) -> SPECIAL
        isHeavy(mass) -> SPECIAL
        else -> STANDARD
    }
}
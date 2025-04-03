package org.example

private const val NUM_PACKAGES = 20

/*
Generates some random packages and sorts them, then prints the details.
 */
fun main() {
    for (i in 0..NUM_PACKAGES) {
        val (width, height, length, mass) = PackageGenerator.getRandomPackage()
        println(
            "Package $width cm x $height cm by $length cm, Mass $mass kg: ${
                ThoughtfulAutomation.sort(
                    width, height, length, mass
                )
            }"
        )
    }
}
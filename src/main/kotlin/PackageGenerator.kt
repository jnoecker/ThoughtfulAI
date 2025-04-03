package org.example

import kotlin.random.Random

/*
Utility class to generate a random function for the purposes of demonstrating the function.
 */
object PackageGenerator {
    private const val MAXIMUM_DIMENSION = 150 // 150 cm
    private const val MAXIMUM_MASS = 20

    fun getRandomPackage(): List<Int> {
        return listOf(
            Random.nextInt(MAXIMUM_DIMENSION + 25),
            Random.nextInt(MAXIMUM_DIMENSION + 25),
            Random.nextInt(MAXIMUM_DIMENSION + 25),
            Random.nextInt(MAXIMUM_MASS + 5)
        )
    }
}
import org.example.ThoughtfulAutomation.isBulky
import org.example.ThoughtfulAutomation.isHeavy
import org.example.ThoughtfulAutomation.isRejected
import org.example.ThoughtfulAutomation.sort
import org.example.ThoughtfulAutomation.validateInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested


const val NORMAL_MASS = 10
const val HEAVY_MASS = 25
const val BORDERLINE_HEAVY_MASS = 20
const val BORDERLINE_LIGHT_MASS = 19
const val SMALL_DIMENSION = 50
const val LONG_DIMENSION = 300
const val BORDERLINE_LONG_DIMENSION = 150
const val BORDERLINE_SHORT_DIMENSION = 149
const val VOLUME_HIGH_DIMENSION = 110
const val BORDERLINE_VOLUME_HIGH_DIMENSION = 100
const val BORDERLINE_VOLUME_LOW_DIMENSION = 99
const val REJECTED = "REJECTED"
const val SPECIAL = "SPECIAL"
const val STANDARD = "STANDARD"

class ThoughtfulAutomationTest {


    @Nested
    inner class TestValidateInput {
        @org.junit.jupiter.api.Test
        fun testInputOk() {
            assertEquals(validateInput(LONG_DIMENSION, SMALL_DIMENSION, VOLUME_HIGH_DIMENSION, HEAVY_MASS), true)
        }

        @org.junit.jupiter.api.Test
        fun testInputInvalid() {
            assertEquals(validateInput(SMALL_DIMENSION, SMALL_DIMENSION, SMALL_DIMENSION, -1), false)
            assertEquals(validateInput(SMALL_DIMENSION, SMALL_DIMENSION, -1, NORMAL_MASS), false)
            assertEquals(validateInput(SMALL_DIMENSION, -1, SMALL_DIMENSION, NORMAL_MASS), false)
            assertEquals(validateInput(-1, SMALL_DIMENSION, SMALL_DIMENSION, NORMAL_MASS), false)
        }
    }

    @Nested
    inner class TestIsBulky {
        @org.junit.jupiter.api.Test
        fun testNotBulky() {
            assertEquals(isBulky(SMALL_DIMENSION, SMALL_DIMENSION, SMALL_DIMENSION), false)
        }

        @org.junit.jupiter.api.Test
        fun testHighVolume() {
            assertEquals(isBulky(VOLUME_HIGH_DIMENSION, VOLUME_HIGH_DIMENSION, VOLUME_HIGH_DIMENSION), true)
        }

        @org.junit.jupiter.api.Test
        fun testBorderlineHighVolume() {
            assertEquals(
                isBulky(
                    BORDERLINE_VOLUME_HIGH_DIMENSION, BORDERLINE_VOLUME_HIGH_DIMENSION, BORDERLINE_VOLUME_HIGH_DIMENSION
                ), true
            )
        }

        @org.junit.jupiter.api.Test
        fun testBorderlineLowVolume() {
            assertEquals(
                isBulky(
                    BORDERLINE_VOLUME_LOW_DIMENSION, BORDERLINE_VOLUME_LOW_DIMENSION, BORDERLINE_VOLUME_LOW_DIMENSION
                ), false
            )
        }

        @org.junit.jupiter.api.Test
        fun testTooLong() {
            assertEquals(isBulky(LONG_DIMENSION, 1, 1), true)
            assertEquals(isBulky(1, LONG_DIMENSION, 1), true)
            assertEquals(isBulky(1, 1, LONG_DIMENSION), true)
        }

        @org.junit.jupiter.api.Test
        fun testBorderlineTooLong() {
            assertEquals(isBulky(BORDERLINE_LONG_DIMENSION, 1, 1), true)
        }

        @org.junit.jupiter.api.Test
        fun testBorderlineNotLong() {
            assertEquals(isBulky(BORDERLINE_SHORT_DIMENSION, 1, 1), false)
        }

    }

    @Nested
    inner class TestIsHeavy {
        @org.junit.jupiter.api.Test
        fun testNotHeavy() {
            assertEquals(isHeavy(NORMAL_MASS), false)
        }

        @org.junit.jupiter.api.Test
        fun testJustLightEnough() {
            assertEquals(isHeavy(BORDERLINE_LIGHT_MASS), false)
        }

        @org.junit.jupiter.api.Test
        fun testTooHeavy() {
            assertEquals(isHeavy(HEAVY_MASS), true)
        }

        @org.junit.jupiter.api.Test
        fun testSlightlyTooHeavy() {
            assertEquals(isHeavy(BORDERLINE_HEAVY_MASS), true)
        }
    }

    @Nested
    inner class TestIsRejected {
        @org.junit.jupiter.api.Test
        fun testOK() {
            assertEquals(isRejected(SMALL_DIMENSION, SMALL_DIMENSION, SMALL_DIMENSION, NORMAL_MASS), false)
        }

        @org.junit.jupiter.api.Test
        fun testOnlyBulky() {
            assertEquals(isRejected(LONG_DIMENSION, LONG_DIMENSION, LONG_DIMENSION, NORMAL_MASS), false)
        }

        @org.junit.jupiter.api.Test
        fun testOnlyHeavy() {
            assertEquals(isRejected(SMALL_DIMENSION, SMALL_DIMENSION, SMALL_DIMENSION, HEAVY_MASS), false)
        }

        @org.junit.jupiter.api.Test
        fun testHeavyAndBulky() {
            assertEquals(
                isRejected(VOLUME_HIGH_DIMENSION, VOLUME_HIGH_DIMENSION, VOLUME_HIGH_DIMENSION, HEAVY_MASS), true
            )
        }
    }

    @Nested
    inner class TestSort {
        @org.junit.jupiter.api.Test
        fun testOK() {
            assertEquals(sort(SMALL_DIMENSION, SMALL_DIMENSION, SMALL_DIMENSION, NORMAL_MASS), STANDARD)
        }

        @org.junit.jupiter.api.Test
        fun testHeavy() {
            assertEquals(sort(SMALL_DIMENSION, SMALL_DIMENSION, SMALL_DIMENSION, HEAVY_MASS), SPECIAL)
        }

        @org.junit.jupiter.api.Test
        fun testBulky() {
            assertEquals(sort(LONG_DIMENSION, LONG_DIMENSION, LONG_DIMENSION, NORMAL_MASS), SPECIAL)
        }

        @org.junit.jupiter.api.Test
        fun testRejected() {
            assertEquals(sort(LONG_DIMENSION, LONG_DIMENSION, LONG_DIMENSION, HEAVY_MASS), REJECTED)
        }

        @org.junit.jupiter.api.Test
        fun testRejectedInvalidInput() {
            assertEquals(sort(-1, SMALL_DIMENSION, SMALL_DIMENSION, NORMAL_MASS), REJECTED)
        }
    }
}
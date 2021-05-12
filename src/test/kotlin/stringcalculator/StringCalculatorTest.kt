package stringcalculator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StringCalculatorTest {

    lateinit var stringCalculator: StringCalculator

    @BeforeAll
    internal fun setUp() {
        stringCalculator = StringCalculator()
    }

    @Test
    fun `should return 0 when the string to add is empty`() {
        assertEquals("0", stringCalculator.add(""))
    }

    @Test
    fun `should return same number`() {
        assertEquals("1", stringCalculator.add("1"))
        assertEquals("5", stringCalculator.add("5"))
        assertEquals("10", stringCalculator.add("10"))
        assertEquals("33", stringCalculator.add("33"))
        assertEquals("33.9999", stringCalculator.add("33.9999"))
        assertEquals("47.34", stringCalculator.add("47.34"))
    }

    @Test
    fun `should sum 0,0 and return 0`() {
        assertEquals("0", stringCalculator.add("0,0"))
    }

    @Test
    fun `should sum arbitrary two numbers integer or float`() {
        assertEquals("2", stringCalculator.add("1,1"))
        assertEquals("1", stringCalculator.add("1,0"))
        assertEquals("1", stringCalculator.add("0,1"))
        assertEquals("7", stringCalculator.add("2,5"))
        assertEquals("15", stringCalculator.add("10,5"))
        assertEquals("14", stringCalculator.add("11.5, 2.5"))
        assertEquals("3.1", stringCalculator.add("1.5,1.6"))
    }

    @Test
    fun `should sum arbitrary three numbers integer or floating`(){
        assertEquals("6", stringCalculator.add("1,2,3"))
        assertEquals("12", stringCalculator.add("2,3,7"))
        assertEquals("8", stringCalculator.add("2,2,2,2"))
        assertEquals("4.5", stringCalculator.add("1.5,1.5,1.5"))
    }

    @Test
    fun `should sum using (,) or (new line) as separator`() {
        assertEquals("6", stringCalculator.add("1\n2,3"))
        assertEquals("6", stringCalculator.add("1,2\n3"))
        assertEquals("10", stringCalculator.add("1,2,3\n4"))
    }

    @Test
    fun `should return error when string contains two separators in sequence`() {
        assertEquals("Number expected but '\\n' found at position 6", stringCalculator.add("175.2,\n35") )
        assertEquals("Number expected but '\\n' found at position 5", stringCalculator.add("175.2\n,35") )
    }

    @Test
    fun `should return error with position`() {
        assertEquals("Number expected but '\\n' found at position 6", stringCalculator.add("175.2,\n35") )
        assertEquals("Number expected but '\\n' found at position 5", stringCalculator.add("175.2\n,35") )
    }

//    @Test
//    fun `sss`() {
//        assertEquals("Number expected but '\\n' found at position 5", stringCalculator.add("175.2,,35") )
//    }

    @Test
    fun `should return error when string ends with (,)`() {
        assertEquals("Number expected but EOF found", stringCalculator.add("1,2,"))
        assertEquals("Number expected but EOF found", stringCalculator.add(",1,2"))
        assertEquals("Number expected but EOF found", stringCalculator.add("1,2\n"))
        assertEquals("Number expected but EOF found", stringCalculator.add("\n1,2"))
    }

    @Nested
    @DisplayName("Custom delimiter")
    inner class CustomDelimiter {
        @Test
        fun `should sum with the selected separator`() {
            assertEquals("3", stringCalculator.add("//;\n1;2"))
            assertEquals("6", stringCalculator.add("//|\n1|2|3"))
            assertEquals("5", stringCalculator.add("//sep\n2sep3"))
        }

        @Test
        fun `should return error when using invalid delimiter`() {
            assertEquals("'|' expected but ',' found at position 3.", stringCalculator.add("//|\n1|2,3"))
            assertEquals("',' expected but '|' found at position 3.", stringCalculator.add("//,\n1|2,3"))
            assertEquals("'#' expected but '|' found at position 3.", stringCalculator.add("//#\n1|2,3"))
        }
    }
}
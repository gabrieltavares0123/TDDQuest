import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class StringCalculatorTest {
    lateinit var stringCalculator: StringCalculator

    @BeforeEach
    fun setUp() {
        stringCalculator = StringCalculator()
    }

    @Test
    fun `should return 0 when the string to add is null`() {
        var result = stringCalculator.add("")

        assertEquals(result, "0")
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
    fun `should sum arbitrary two numbers`() {
        assertEquals("2", stringCalculator.add("1,1"))
        assertEquals("1", stringCalculator.add("1,0"))
        assertEquals("1", stringCalculator.add("0,1"))
        assertEquals("7", stringCalculator.add("2,5"))
        assertEquals("15", stringCalculator.add("10,5"))
        assertEquals("14", stringCalculator.add("11.5, 2.5"))
        assertEquals("3.1", stringCalculator.add("1.5,1.6"))
    }

    @Test
    fun `should sum numbers 1,2,3`(){
        assertEquals("6", stringCalculator.add("1,2,3"))
        assertEquals("12", stringCalculator.add("2,3,7"))
        assertEquals("8", stringCalculator.add("2,2,2,2"))
        assertEquals("4.5", stringCalculator.add("1.5,1.5,1.5"))
    }

    @Test
    fun `should sum '1(new line)2,3' and return 6`() {
        assertEquals("6", stringCalculator.add("1\n2,3"))
        assertEquals("6", stringCalculator.add("1,2\n3"))
        assertEquals("10", stringCalculator.add("1,2,3\n4"))
    }

    @Test
    fun `should return error when two string contains ,(new line))`() {
        assertEquals("Number expected but '\\n' found at position 6", stringCalculator.add("175.2,\n35") )
        assertEquals("Number expected but '\\n' found at position 6", stringCalculator.add("175.2\n,35") )
    }
}
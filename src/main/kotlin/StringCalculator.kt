import kotlin.math.roundToInt

class StringCalculator {
    private val invalidSeparatorSequence = Regex("(.*,\\n.*)|(.*\\n,.*)")

    fun add(number: String): String {
        return when {
            number.matches(invalidSeparatorSequence) -> {
                val first = "\n".toRegex().find(number)?.range?.first
                "Number expected but '\\n' found at position $first"
            }
            startOrEndInvalid(number) -> {
                "Number expected but EOF found"
            }
            else -> {
                number.split(",", "\n")
                    .map { it.toFloatHandlingEmpty() }
                    .sum()
                    .toFloor()
            }
        }
    }

    private fun startOrEndInvalid(numbers: String): Boolean {
        return when {
            numbers.first() == ',' || numbers.first() == '\n' -> true
            numbers.last() == ',' || numbers.last() == '\n' -> true
            else -> false
        }
    }

    private fun String.toFloatHandlingEmpty(): Float =
        if (isEmpty()) {
            0F
        } else {
            toFloat()
        }

    private fun Float.toFloor(): String =
        if (this % 1F != 0F) {
            toString()
        } else {
            roundToInt().toString()
        }
}
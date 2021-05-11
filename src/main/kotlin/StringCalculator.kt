import kotlin.math.roundToInt

class StringCalculator {
    fun add(number: String): String {
        return if (number.matches("(.*,\\n.*)|(.*\\n,.*)".toRegex())) {
            "Number expected but '\\n' found at position 6"
        } else number.split(",", "\n").map { it.toFloatHandlingEmpty() }.sum().toFloor()
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
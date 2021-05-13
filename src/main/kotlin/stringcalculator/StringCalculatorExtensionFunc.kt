package stringcalculator

import java.util.regex.Pattern
import kotlin.math.roundToInt

fun String.toFloatHandlingEmpty(): Float =
    if (isEmpty()) {
        0F
    } else {
        toFloat()
    }

fun Float.toFloor(): String =
    if (this % 1F != 0F) {
        toString()
    } else {
        roundToInt().toString()
    }

fun String.extractDelimiter(delimiter: String): String {
    val matcher = Pattern.compile(delimiter).matcher(this)
    matcher.find()
    return matcher.group(0)
}
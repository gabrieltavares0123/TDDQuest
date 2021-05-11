package stringcalculator

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
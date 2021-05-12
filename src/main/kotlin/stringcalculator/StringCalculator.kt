package stringcalculator

class StringCalculator {
    private val invalidSeparatorSequence = Regex("(.*,\\n.*)|(.*\\n,.*)")

    fun add(number: String): String {
        var definedNumber = ""
        var definedDelimiter = ","

        if (number.contains("//")) {
            var inputs = number.split("\n")
            definedDelimiter = inputs[0].substring(2)
            definedNumber = inputs[1]
        } else {
            definedNumber = number
        }

        val del = ".*[^?$definedDelimiter\\d?$definedDelimiter].*"

        return when {
            definedNumber.matches(del.toRegex()) -> {
                val wrongDelimiters = "[^\\d$definedDelimiter]"
                val invalid = definedNumber.extractDelimiter(wrongDelimiters)
                "'$definedDelimiter' expected but '$invalid' found at position 3."
            }

            definedNumber.matches(invalidSeparatorSequence) -> {
                val first = "\n".toRegex().find(definedNumber)?.range?.first
                "Number expected but '\\n' found at position $first"
            }
            startOrEndInvalid(definedNumber) -> {
                "Number expected but EOF found"
            }
            else -> {
                definedNumber.split(definedDelimiter, "\n")
                    .map { it.toFloatHandlingEmpty() }
                    .sum()
                    .toFloor()
            }
        }
    }

    private fun startOrEndInvalid(numbers: String): Boolean {
        return when {
                numbers.isNotEmpty() && (numbers.first() == ',' || numbers.first() == '\n')-> true
                numbers.isNotEmpty() && (numbers.last() == ',' || numbers.last() == '\n') -> true
            else -> false
        }
    }
}
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class SortedListAlphabeticallyAscendingMatcher<T>(
    private val comparator: Comparator<T>
): TypeSafeMatcher<List<T>>() {

    private var expectedList: List<T>? = null

    override fun describeTo(description: Description?) {
        expectedList?.let {
            description?.appendText(it.toString())
        }
    }

    override fun matchesSafely(list: List<T>?): Boolean {
        expectedList = list?.sortedWith(comparator)

        return list?.asSequence()?.zipWithNext {
                a, b ->  comparator.compare(a,b) < 0
        }?.all {
            it
        }?: true
    }
}
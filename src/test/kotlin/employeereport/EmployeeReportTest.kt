package employeereport

import SortedListAlphabeticallyAscendingMatcher
import SortedListAlphabeticallyDescendingMatcher
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.beans.HasPropertyWithValue.hasProperty
import org.hamcrest.core.Every.everyItem
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class EmployeeReportTest {

    private lateinit var report: EmployeeReport
    private var repository = mockk<EmployeeRepository>()

    @BeforeAll
    fun setUp() {
        report = EmployeeReport(repository)
    }

    @Nested
    @DisplayName("All employees tests")
    inner class AllEmployees {
        @Test
        fun `should return an empty list if there is no employee`() {
            every { repository.getAllEmployees() } returns noOneEmployee

            val emptyEmployeesList = report.getAllEmployees()

            assertThat(emptyEmployeesList, `is`(empty()))
        }

        @Test
        fun `should return a not empty list if there is at last one employee`() {
            every { repository.getAllEmployees() } returns atLastOneEmployee

            val notEmptyEmployeeList = report.getAllEmployees()

            assertThat(notEmptyEmployeeList, not(empty()))
        }
    }

    @Nested
    @DisplayName("Older employees")
    inner class OlderEmployees {
        @Test
        fun `should return an empty list when there is no employee older than 18`() {
            every { repository.getAllEmployees() } returns noOneOlderEmployee

            val emptyOlderEmployees = report.getOlderEmployees()

            assertThat(emptyOlderEmployees, `is`(empty()))
        }

        @Test
        fun `should return all employees with age greater or equal than 18`() {
            every { repository.getAllEmployees() } returns allEmployees

            val olderEmployees = report.getOlderEmployees()

            assertThat(olderEmployees, not(empty()))
            assertThat(
                olderEmployees,
                everyItem(hasProperty("age", greaterThanOrEqualTo(18)))
            )
        }
    }

    @Nested
    @DisplayName("Employees sorted by name ascending")
    inner class EmployeesSortedByNameAscending() {
        @Test
        fun `should return all employees sorted by name ascending`() {
            every { repository.getAllEmployees() } returns allEmployees

            val comparator = compareBy<Employee> { it.name }
            val resultList = report.getAllEmployeesSortedByNameAscending()

            assertThat(resultList, `is`(SortedListAlphabeticallyAscendingMatcher(comparator)))
        }
    }

    @Nested
    @DisplayName("Employees capitalized")
    inner class EmployeesCapitalized {
        @Test
        fun `should return all employees capitalized`() {
            every { repository.getAllEmployees() } returns allEmployees

            val resultList = report.getAllEmployeesCapitalized()

            assertThat(resultList,
                everyItem(hasProperty("name", matchesPattern("[A-Z]*"))))
        }
    }

    @Nested
    @DisplayName("Employees sorted by name descending")
    inner class EmployeesSortedByNameDescending {
        @Test
        fun `should return all employees sorted by name descending`() {
            every { repository.getAllEmployees() } returns allEmployees

            val comparator = compareBy<Employee> { it.name }
            val resultList = report.getAllEmployeesSortedByNameDescending()

            assertThat(resultList, `is`(SortedListAlphabeticallyDescendingMatcher(comparator)))
        }
    }
}
package employeereport

internal class EmployeeReport (
    private val repository: EmployeeRepository) {

    fun getAllEmployees(): List<Employee> {
        return repository.getAllEmployees()
    }

    fun getOlderEmployees(): List<Employee> {
        val allEmployees = repository.getAllEmployees()
        return allEmployees.filter { it.age >= 18 }
    }
}
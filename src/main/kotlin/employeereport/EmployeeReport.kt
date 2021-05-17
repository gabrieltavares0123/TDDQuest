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

    fun getAllEmployeesSortedByNameAscending(): List<Employee> {
        return getAllEmployees().sortedBy { it.name }
    }

    fun getAllEmployeesCapitalized(): List<Employee> {
        return repository.getAllEmployees()
            .map { it.copy(name = it.name.toUpperCase()) }
    }

    fun getAllEmployeesSortedByNameDescending(): List<Employee> {
        return repository.getAllEmployees()
            .sortedByDescending { it.name }
    }
}
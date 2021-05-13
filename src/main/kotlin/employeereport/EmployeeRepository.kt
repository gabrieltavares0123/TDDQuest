package employeereport

internal interface EmployeeRepository {
    fun getAllEmployees(): List<Employee>
}
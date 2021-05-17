package employeereport

internal val allEmployees = listOf(
    Employee(name = "Sepp", age = 18),
    Employee(name = "Mike", age = 51),
    Employee(name = "Max", age = 17),
    Employee(name = "Nina", age = 15),
)

internal val atLastOneEmployee = listOf(
    Employee(name = "Sepp", age = 18),
)

internal val noOneEmployee = emptyList<Employee>()

internal val noOneOlderEmployee = listOf(
    Employee(name = "Max", age = 17),
    Employee(name = "Nina", age = 15),
)
// File: src/main/kotlin/seg3x02/employeeGql/resolvers/types/CreateEmployeeInput.kt

package seg3x02.employeeGql.resolvers.types

data class CreateEmployeeInput(
    val employeeNumber: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val employeeRole: String = "",
    val department: String = ""
)
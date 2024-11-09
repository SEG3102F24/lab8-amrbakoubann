
package seg3x02.employeeGql.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "employee")
data class Employee(
    var employeeNumber: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var employeeRole: String,
    var department: String
) {
    @Id
    var employeeId: String = ""
}
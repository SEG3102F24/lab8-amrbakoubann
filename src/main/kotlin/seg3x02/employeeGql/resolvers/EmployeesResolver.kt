
package seg3x02.employeeGql.resolvers
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeeRepository
import seg3x02.employeeGql.resolvers.types.CreateEmployeeInput
import java.util.*
@Controller
class EmployeesResolver(
    private val employeeRepository: EmployeeRepository,
    private val mongoOperations: MongoOperations
) {
    @QueryMapping
    fun employeeByNumber(@Argument employeeNumber: String): Employee? {
        val query = Query()
        query.addCriteria(Criteria.where("employeeNumber").`is`(employeeNumber))
        return mongoOperations.findOne(query, Employee::class.java)
    }
    @MutationMapping
    fun newEmployee(@Argument createEmployeeInput: CreateEmployeeInput): Employee {
        val employee = Employee(
            createEmployeeInput.employeeNumber,
            createEmployeeInput.firstName,
            createEmployeeInput.lastName,
            createEmployeeInput.email,
            createEmployeeInput.employeeRole,
            createEmployeeInput.department
        )
        employee.employeeId = UUID.randomUUID().toString()
        return employeeRepository.save(employee)
    }
    @MutationMapping
    fun deleteEmployee(@Argument employeeId: String): Boolean {
        return try {
            employeeRepository.deleteById(employeeId)
            true
        } catch (e: Exception) {
            false
        }
    }
    @MutationMapping
    fun updateEmployee(
        @Argument employeeId: String,
        @Argument createEmployeeInput: CreateEmployeeInput
    ): Employee {
        val employee = employeeRepository.findById(employeeId).orElseThrow()
        employee.apply {
            employeeNumber = createEmployeeInput.employeeNumber
            firstName = createEmployeeInput.firstName
            lastName = createEmployeeInput.lastName
            email = createEmployeeInput.email
            employeeRole = createEmployeeInput.employeeRole
            department = createEmployeeInput.department
        }
        return employeeRepository.save(employee)
    }
}
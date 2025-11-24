package HR_project.services;

import HR_project.dtos.FilterDTO;
import HR_project.dtos.employee.EmployeeDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.entities.Employee;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    /**
     * Creates a new employee.
     *
     * @param createDto request body
     * @return created employee response
     */
    EmployeeResponseDTO create(@Valid EmployeeDTO createDto);

    /**
     * Updates existing employee.
     *
     * @param id employee id
     * @param updateDto new data
     * @return updated employee response
     */
    EmployeeResponseDTO update(String id, @Valid EmployeeDTO updateDto);

    /**
     * Soft deletes employee.
     *
     * @param id employee id
     */
    boolean delete(String id);

    /**
     * Gets employee by ID.
     *
     * @param id employee id
     * @return employee response dto
     */
    EmployeeResponseDTO get(String id);

    /**
     * Gets all employees by pagination.
     *
     * @return page of employee responses
     */
    Page<EmployeeResponseDTO> getAll(int page, int size);

    /**
     * Searches employees by filter.
     *
     * @return filtered page of employees
     */
    Page<EmployeeResponseDTO> search(int page, int size, @Valid FilterDTO filterQuery);

    /**
     * Internal method for getting employee entity.
     */
    Employee getEmployee(String employeeId);
}

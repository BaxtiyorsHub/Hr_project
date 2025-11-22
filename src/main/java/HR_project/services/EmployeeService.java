package HR_project.services;

import HR_project.dtos.employee.EmployeeDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    /**
     * Method uses for creation
     *
     * @return Employee's created DTO
     *
     */
    EmployeeResponseDTO create(EmployeeDTO createDto);

    /**
     * Method uses for update
     *
     * @return Employee's updated dto
     *
     */
    EmployeeResponseDTO update(String id, EmployeeDTO updateDto);

    /**
     * Method uses for deletion
     *
     * @return true/false
     *
     */
    boolean delete(String id);

    /**
     * Method uses for get employee by id
     *
     * @return EmployeeResponseDTO
     *
     */
    EmployeeResponseDTO get(String id);

    /**
     * Method uses for get all employees by pagination
     *
     * @return List of EmployeeResponseDTO
     *
     */
    List<EmployeeResponseDTO> getAll();
}

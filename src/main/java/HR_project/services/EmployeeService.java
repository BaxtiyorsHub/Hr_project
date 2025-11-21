package HR_project.services;

import HR_project.dtos.employee.EmployeeCreateDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.dtos.employee.EmployeeUpdateDTO;

import java.util.List;

public interface EmployeeService {
    /**
     * Method uses for creation
     * @return Employee's created DTO
     * */
    EmployeeResponseDTO create(EmployeeCreateDTO createDto);
    /**Method uses for update
     * @return Employee's updated dto
     * */
    EmployeeResponseDTO update(String id,EmployeeUpdateDTO updateDto);
    /**Method uses for deletion
     * @return true/false
     * */
    boolean delete(String id);
    /**Method uses for get employee by id
     * @return EmployeeResponseDTO
     * */
    EmployeeResponseDTO get(String id);
    /**Method uses for get all employees by pagination
     * @return List of EmployeeResponseDTO
     * */
    List<EmployeeResponseDTO> getAll();
}

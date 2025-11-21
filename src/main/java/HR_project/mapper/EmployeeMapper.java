package HR_project.mapper;

import HR_project.dtos.employee.EmployeeCreateDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.entities.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee fromCreateDTO(EmployeeCreateDTO dto);
    EmployeeResponseDTO toDTO(Employee employee);
}

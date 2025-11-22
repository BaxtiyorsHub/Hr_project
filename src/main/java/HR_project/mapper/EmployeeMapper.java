package HR_project.mapper;

import HR_project.dtos.employee.EmployeeDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.entities.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeDTO dto);
    EmployeeResponseDTO toDTO(Employee employee);
}

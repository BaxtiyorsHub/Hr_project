package HR_project.mapper;

import HR_project.dtos.employee.EmployeeDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.entities.Employee;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    /**Converts a {@link EmployeeDTO} dto to {@link Employee} entity
     * @param dto Employee DTO which comes API
     * @return Employee Entity
     * */
    Employee toEntity(EmployeeDTO dto);

    /**Converts {@link Employee} entity to {@link EmployeeResponseDTO} DTO
     * @param employee Employee entity
     * @return EmployeeResponseDTO
     * */
    EmployeeResponseDTO toDTO(Employee employee);

    /**Method makes update
     * @param updateDto Dto for update
     * @param byId Saved Entity from repository */
    void updateEntity(@Valid EmployeeDTO updateDto, @MappingTarget Employee byId);
}

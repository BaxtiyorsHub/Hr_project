package HR_project.mapper;

import HR_project.dtos.employee.EmployeeDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.entities.Employee;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    /**Method castes to Entity
     * @param dto Employee DTO which comes API
     * @return Created Employee Entity
     * */
    Employee toEntity(EmployeeDTO dto);

    /**Method castes to DTO for response
     * @param employee Employee entity
     * @return EmployeeResponseDTO
     * */
    EmployeeResponseDTO toDTO(Employee employee);

    /**Method makes update
     * @param updateDto Dto for update
     * @param byId Saved Entity from repository
     * @return Updated Employee entity
     * */
    void updateEntity(@Valid EmployeeDTO updateDto, @MappingTarget Employee byId);
}

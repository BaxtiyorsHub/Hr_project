package HR_project.mapper;

import HR_project.dtos.leave.LeaveApplyDTO;
import HR_project.dtos.leave.LeaveResponseDTO;
import HR_project.entities.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeaveMapper {

    /**
     * Converts a {@link LeaveApplyDTO} to a {@link Leave} entity
     * @param dto DTO which comes from API
     * @return Leave entity
     */
    @Mapping(target = "employee", ignore = true)
    Leave toEntity(LeaveApplyDTO dto);

    /**
     * Converts a {@link Leave} entity to a {@link LeaveResponseDTO}.
     * @param entity Leave entity
     * @return LeaveResponseDTO
     */
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "employee", source = "employee")
    LeaveResponseDTO toDTO(Leave entity);
}

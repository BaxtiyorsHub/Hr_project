package HR_project.mapper;

import HR_project.dtos.leave.LeaveApplyDTO;
import HR_project.dtos.leave.LeaveResponseDTO;
import HR_project.entities.Leave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LeaveMapper {

    @Mapping(target = "employee" , ignore = true)
    Leave toEntity(LeaveApplyDTO dto);

    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "employee", source = "employee")
    LeaveResponseDTO toDTO(Leave entity);
}

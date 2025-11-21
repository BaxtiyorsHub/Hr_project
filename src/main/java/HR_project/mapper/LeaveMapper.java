package HR_project.mapper;

import HR_project.dtos.leave.LeaveApplyDTO;
import HR_project.dtos.leave.LeaveResponseDTO;
import HR_project.entities.Leave;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaveMapper {

    Leave toEntity(LeaveApplyDTO dto);

    LeaveResponseDTO toDTO(Leave entity);
}

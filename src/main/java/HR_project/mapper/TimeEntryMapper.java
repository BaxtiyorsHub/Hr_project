package HR_project.mapper;

import HR_project.dtos.time.TimeDTO;
import HR_project.dtos.time.TimeEntryResponse;
import HR_project.entities.TimeEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimeEntryMapper {

    @Mapping(target = "employee", ignore = true)
    TimeEntry toEntity(TimeDTO dto);

    @Mapping(target = "employee", source = "employee")
    @Mapping(target = "employeeId", source = "employeeId")
    TimeEntryResponse toDTO(TimeEntry timeEntry);
}

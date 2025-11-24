package HR_project.mapper;

import HR_project.dtos.time.TimeDTO;
import HR_project.dtos.time.TimeEntryResponse;
import HR_project.entities.TimeEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimeEntryMapper {

    /**
     * Converts a {@link TimeDTO} to a {@link TimeEntry} entity.
     * Ignores the 'employee' field because it will be set manually in service.
     *
     * @param dto TimeDTO object containing employeeId and times
     * @return TimeEntry entity ready to be persisted
     */
    @Mapping(target = "employee", ignore = true)
    TimeEntry toEntity(TimeDTO dto);

    /**
     * Converts a {@link TimeEntry} entity to a {@link TimeEntryResponse} DTO.
     * Maps the employee entity to the DTO if needed.
     *
     * @param timeEntry TimeEntry entity from the database
     * @return TimeEntryResponse DTO
     */
    @Mapping(target = "employee", source = "employee")
    @Mapping(target = "employeeId", source = "employeeId")
    TimeEntryResponse toDTO(TimeEntry timeEntry);
}

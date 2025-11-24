package HR_project.dtos.time;

import HR_project.dtos.employee.EmployeeResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeEntryResponse {

    private String id;

    private String employeeId;

    private EmployeeResponseDTO employee;

    private LocalDate date;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private boolean lateStatus;

    private boolean visible;

    private LocalDateTime createdDate;
}

package HR_project.dtos.leave;

import HR_project.dtos.employee.EmployeeDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.enums.LeaveStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeaveResponseDTO {

    private String id;
    private String startDate;
    private String endDate;
    private String reason;
    private LeaveStatus status;

    private String employeeId;
    private EmployeeResponseDTO employee;
}

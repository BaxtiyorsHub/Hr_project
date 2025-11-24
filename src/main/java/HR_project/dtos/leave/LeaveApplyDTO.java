package HR_project.dtos.leave;

import HR_project.enums.LeaveType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class LeaveApplyDTO {

    @NotBlank(message = "Employee id is required")
    private String employeeId;
    @NotNull(message = "Leave type mustn't be empty")
    private LeaveType leaveType;
    @NotBlank(message = "Reason is required")
    private String reason;
    @NotNull(message = "Starting date musn't be empty")
    private LocalDate startDate;
    @NotNull(message = "Ending date musn't be empty")
    private LocalDate endDate;

}

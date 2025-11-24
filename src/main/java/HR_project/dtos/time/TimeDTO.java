package HR_project.dtos.time;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class TimeDTO {

    @NotBlank(message = "Employee id must not be empty")
    private String employeeId;

    @NotNull(message = "Condition must not be null")
    private boolean lateStatus;

}

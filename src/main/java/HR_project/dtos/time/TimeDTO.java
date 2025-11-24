package HR_project.dtos.time;

import HR_project.enums.TimeStat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TimeDTO {

    private String employeeId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private boolean lateStatus;

}

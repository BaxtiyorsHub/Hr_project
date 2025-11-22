package HR_project.dtos.employee;

import HR_project.enums.EmployeeStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponseDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private EmployeeStatus status;
    private boolean visible;
    private LocalDateTime createdDate;

}

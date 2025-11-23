package HR_project.dtos.employee;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDTO {

    @NotBlank(message = "Name is required")
    private String firstName;

    @NotBlank(message = "Surname is required")
    private String lastName;

    @NotBlank(message = "Phone number required")
    private String phoneNumber;

    @NotBlank(message = "Department required")
    private String departmentName;

    @NotBlank(message = "Position required")
    private String position;
}

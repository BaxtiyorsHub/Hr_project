package HR_project.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FilterDTO {

    @NotBlank(message = "Query filed can't be null")
    private String query;
    private LocalDateTime startingDate;
    private LocalDateTime endingDate;

    private String departmentName;
    private String position;

}

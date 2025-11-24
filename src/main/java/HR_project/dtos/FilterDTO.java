package HR_project.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class FilterDTO {

    private String query;
    private LocalDateTime startingDate;
    private LocalDateTime endingDate;

    private String departmentName;
    private String position;

}

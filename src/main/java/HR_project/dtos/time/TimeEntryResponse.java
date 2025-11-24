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

    private String employeeId;          // employeeId field

    private EmployeeResponseDTO employee;    // agar Employee fullName ko‘rsatmoqchi bo‘lsangiz

    private LocalDate date;             // qaysi kun

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private boolean lateStatus;         // kechikkan yoki yo‘q

    private boolean visible;            // ko‘rinadiganligi

    private LocalDateTime createdDate;  // yozilgan vaqt
}

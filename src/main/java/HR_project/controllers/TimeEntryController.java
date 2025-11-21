package HR_project.controllers;

import HR_project.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/time-entry")
@RequiredArgsConstructor
public class TimeEntryController {

    private final EmployeeService employeeService;

}

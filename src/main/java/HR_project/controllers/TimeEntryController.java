package HR_project.controllers;

import HR_project.services.EmployeeService;
import HR_project.services.TimeEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/time-entry")
@SuppressWarnings("NullableProblems")
@RequiredArgsConstructor
public class TimeEntryController {

    private final TimeEntryService service;




}

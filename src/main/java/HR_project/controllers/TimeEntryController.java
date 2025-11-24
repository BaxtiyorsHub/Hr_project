package HR_project.controllers;

import HR_project.dtos.time.TimeDTO;
import HR_project.dtos.time.TimeEntryResponse;
import HR_project.services.TimeEntryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/time-entry")
@SuppressWarnings("NullableProblems")
@RequiredArgsConstructor
public class TimeEntryController {

    private final TimeEntryService service;

    @PostMapping()
    public ResponseEntity<TimeEntryResponse> entry(@RequestBody @Valid TimeDTO dto) {
        return ResponseEntity.ok(service.entry(dto));
    }

    @PutMapping("/leave")
    public ResponseEntity<TimeEntryResponse> leave(@RequestBody @NotBlank String employeeID) {
        return ResponseEntity.ok(service.leave(employeeID));
    }

    @GetMapping("/weekly-report")
    public ResponseEntity<Page<TimeEntryResponse>> entryReport(@RequestParam(defaultValue = "0") int page,
                                                               @RequestParam(defaultValue = "5") int size) {
        if (page < 0) page = 0;
        if (size < 0) size = 5;
        return ResponseEntity.ok(service.reports(page, size));
    }

}

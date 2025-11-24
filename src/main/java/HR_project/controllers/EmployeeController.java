package HR_project.controllers;

import HR_project.dtos.FilterDTO;
import HR_project.dtos.employee.EmployeeDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.services.EmployeeService;
import HR_project.utils.PhoneChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@SuppressWarnings("NullableProblems")
@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Create new employee", description = "Create a new employee with all required data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDTO> create(@Valid @RequestBody EmployeeDTO data) {
        if (data != null && data.getPhoneNumber() !=null) {
            String normalizePhone = PhoneChecker.normalizePhone(data.getPhoneNumber());
            data.setPhoneNumber(normalizePhone);
            return ResponseEntity.ok(employeeService.create(data));
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Update employee", description = "Update existing employee by ID")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> update(@PathVariable @NotBlank String id,
                                                      @Valid @RequestBody EmployeeDTO data) {
        if (data != null && data.getPhoneNumber() !=null) {
            String normalizePhone = PhoneChecker.normalizePhone(data.getPhoneNumber());
            data.setPhoneNumber(normalizePhone);
            return ResponseEntity.ok(employeeService.update(id, data));
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Get employee by ID", description = "Retrieve employee details by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable @NotBlank String id) {
        return ResponseEntity.ok(employeeService.get(id));
    }

    @Operation(summary = "Delete employee", description = "Delete employee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable @NotBlank String id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }

    @Operation(summary = "Get all employees", description = "Retrieve list of all employees")
    @GetMapping("/all")
    public ResponseEntity<Page<EmployeeResponseDTO>> findAll(@RequestParam(defaultValue = "1") int page,
                                                             @RequestParam(defaultValue = "5") int size) {
        if (page < 1) page = 1;
        if (size < 1) size = 5;
        return ResponseEntity.ok(employeeService.getAll(page-1, size));
    }

    @Operation(summary = "Search by query", description = "Retrieve list of all employees")
    @GetMapping("/search")
    public ResponseEntity<Page<EmployeeResponseDTO>> search(@RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "5") int size,
                                                            @RequestBody @Valid FilterDTO filterQuery) {
        if (page < 1) page = 1;
        if (size < 1) size = 5;
        return ResponseEntity.ok(employeeService.search(page-1, size, filterQuery));
    }
}

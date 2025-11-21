package HR_project.controllers;

import HR_project.dtos.employee.EmployeeCreateDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.dtos.employee.EmployeeUpdateDTO;
import HR_project.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Create new employee", description = "Create a new employee with all required data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> create(@Valid @RequestBody EmployeeCreateDTO data) {
        return ResponseEntity.ok(employeeService.create(data));
    }

    @Operation(summary = "Update employee", description = "Update existing employee by ID")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> update(@PathVariable String id,
                                                      @Valid @RequestBody EmployeeUpdateDTO data) {
        return ResponseEntity.ok(employeeService.update(id, data));
    }

    @Operation(summary = "Get employee by ID", description = "Retrieve employee details by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(employeeService.get(id));
    }

    @Operation(summary = "Delete employee", description = "Delete employee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        return ResponseEntity.ok(employeeService.delete(id));
    }

    @Operation(summary = "Get all employees", description = "Retrieve list of all employees")
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> findAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }
}

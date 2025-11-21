package HR_project.controllers;

import HR_project.dtos.employee.EmployeeCreateDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.dtos.employee.EmployeeUpdateDTO;
import HR_project.services.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("v1/profile")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService profileService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponseDTO> create(@RequestBody @Valid EmployeeCreateDTO data) {
        return ResponseEntity.ok(profileService.create(data));
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeResponseDTO> update(@RequestBody @Valid EmployeeUpdateDTO data) {
        return ResponseEntity.ok(profileService.update(data));
    }

    @GetMapping("/user{id}")
    public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable @NotBlank String id) {
        return ResponseEntity.ok(profileService.get(id));
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<Boolean> delete(@PathVariable @NotBlank String id) {
        return ResponseEntity.ok(profileService.delete(id));
    }

    @GetMapping("/all-employees")
    public ResponseEntity<List<EmployeeResponseDTO>> findAll() {
        return ResponseEntity.ok(profileService.getAll());
    }
}

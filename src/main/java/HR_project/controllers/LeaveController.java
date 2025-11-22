package HR_project.controllers;

import HR_project.dtos.leave.LeaveApplyDTO;
import HR_project.dtos.leave.LeaveResponseDTO;
import HR_project.services.LeaveService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leaves")
@RequiredArgsConstructor
@SuppressWarnings("NullableProblems")
public class LeaveController {

    private final LeaveService leaveService;

    @Operation(summary = "Apply for leave", description = "Submit a leave request for a specific employee")
    @PostMapping("/apply")
    public ResponseEntity<LeaveResponseDTO> applyLeave(@Valid @RequestBody LeaveApplyDTO dto) {
        return ResponseEntity.ok(leaveService.applyLeave(dto));
    }

    @Operation(summary = "Get all leave requests", description = "Retrieve all leave requests in the system")
    @GetMapping
    public ResponseEntity<List<LeaveResponseDTO>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }

    @Operation(summary = "Get leave request by ID", description = "Retrieve a leave request by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponseDTO> getLeaveById(@PathVariable String id) {
        return ResponseEntity.ok(leaveService.getLeaveById(id));
    }

    @Operation(summary = "Approve leave request", description = "Approve a pending leave request")
    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveResponseDTO> approveLeave(@PathVariable String id) {
        return ResponseEntity.ok(leaveService.approveLeave(id));
    }

    @Operation(summary = "Reject leave request", description = "Reject a pending leave request")
    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveResponseDTO> rejectLeave(@PathVariable String id) {
        return ResponseEntity.ok(leaveService.rejectLeave(id));
    }
}

package HR_project.controllers;

import HR_project.dtos.leave.LeaveApplyDTO;
import HR_project.dtos.leave.LeaveResponseDTO;
import HR_project.services.LeaveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/leaves")
@RequiredArgsConstructor
@SuppressWarnings("NullableProblems")
public class LeaveController {

    private final LeaveService leaveService;

    @Operation(
            summary = "Approve leave request",
            description = "Approve a pending leave request",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Leave approved successfully"),
                    @ApiResponse(responseCode = "404", description = "Leave not found")
            }
    )
    @PostMapping("/apply")
    public ResponseEntity<LeaveResponseDTO> applyLeave(@Valid @RequestBody LeaveApplyDTO dto) {
        return ResponseEntity.ok(leaveService.applyLeave(dto));
    }

    @Operation(summary = "Get all leave requests", description = "Retrieve all leave requests in the system")
    @GetMapping("/all")
    public ResponseEntity<Page<LeaveResponseDTO>> getAllLeaves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        if (page < 0) page = 0;
        if (size < 0) size = 5;
        return ResponseEntity.ok(leaveService.getAllLeaves(page, size));
    }

    @Operation(summary = "Get leave request by ID", description = "Retrieve a leave request by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponseDTO> getLeaveById(@PathVariable @NotBlank String id) {
        return ResponseEntity.ok(leaveService.getLeaveById(id));
    }

    @Operation(summary = "Approve leave request", description = "Approve a pending leave request")
    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveResponseDTO> approveLeave(@PathVariable @NotBlank String id) {
        return ResponseEntity.ok(leaveService.approveLeave(id));
    }

    @Operation(summary = "Reject leave request", description = "Reject a pending leave request")
    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveResponseDTO> rejectLeave(@PathVariable @NotBlank String id) {
        return ResponseEntity.ok(leaveService.rejectLeave(id));
    }

    @Operation(summary = "Delete leave request", description = "Reject a pending leave request")
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> softDeleteLeave(@PathVariable @NotBlank String id) {
        return ResponseEntity.ok(leaveService.softDeleteLeave(id));
    }
}
